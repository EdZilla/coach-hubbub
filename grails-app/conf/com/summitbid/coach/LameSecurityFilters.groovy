package com.summitbid.coach

class LameSecurityFilters {

     def filters = {
		 log.trace "lameSecurityFilter filter call"
		 
        secureActions(controller: 'post', action: '(addPost|deletePost)') {
            before = {
                if (params.impersonateId) {
                    session.user = User.findByLoginId(params.impersonateId)
                }

                if (!session.user) {
                    redirect controller: 'login', action: 'form'
                    return false
                }
            }

            after = { model->
            }

            afterView = {
                log.debug "Finished running ${controllerName} - [CA]${actionName}"
            }
        }
    }
}
