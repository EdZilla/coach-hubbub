package com.summitbid.coach

class UserController {
    static scaffold = true

    def search() {}

    def results(String loginId) {
        def users = User.where { loginId =~ "${loginId}" }.list()
        return [ users: users,
                 term: params.loginId,
                 totalUsers: User.count() ]
    }

    def advSearch() {}

    def advResults() {
		log.trace "Executing action: '$actionName' with params '$params'"

        def profileProps = Profile.metaClass.properties*.name
		log.debug "profileProps: ${profileProps}"
        def profiles = Profile.withCriteria {
            "${params.queryType}" {

                params.each { field, value ->

                    if (profileProps.grep(field) && value) {
                        ilike(field, value)
                    }
                }
            }
        }
		log.debug "profiles: ${profiles}"
        [ profiles : profiles ]
    }
    
}
