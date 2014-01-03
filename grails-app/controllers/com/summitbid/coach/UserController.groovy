package com.summitbid.coach

class UserController {
    static scaffold = true
	

    def search() {}

    def results(String query) {
		log.trace "Executing action: results with params '$params'"
        def users = User.where { loginId =~ "%${query}%" }.list()
        return [ users: users,
                 term: params.loginId,
                 totalUsers: User.count() ]
    }

    def advSearch() {
		log.trace "Executing action: advSearch with params '$params'"
	}

    def advResults() {
		log.trace "Executing action: advResults with params '$params'"

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
    
    /**
     * NOTE this is different from the chapter 08 version so be advised. 
     */
	def register() {
		log.trace "Executing action: register with params '$params'"
		//log.trace "Executing action: register with params '$params'"
		if (request.method == "POST") {
		   def user = new User(params)
		   if (user.validate()) {
			   log.debug "user validated"
			   user.save()
			   flash.message = "Successfully Created User"
			   redirect uri: '/'
		   } else {
		   	   log.error "usr not valid"
			   flash.message = "Error Registering User"
			   return [ user: user ]
		   }
		}
   }
	
	/**
	 * Note this is different from the chapter 08 version. It has the assignment of 
	 * urc.properties = params
	 * and it printlns the urc errors. 
	 */
	def register2(UserRegistrationCommand urc) {
		log.trace "Executing action: register2 with params '$params' and command object '$urc.properties'"
		
		urc.properties  = params
		
		if (urc.hasErrors()) {
			log.debug "urc properties: ${urc.properties}"
			urc.errors.each {
				println it
			}
			return [ user : urc ]
		} else {
			log.debug "urc does not have errors. ${urc.properties}"
			def user = new User(urc.properties)
			user.profile = new Profile(urc.properties)
			if (user.validate() && user.save()) {
				flash.message = "Welcome aboard, ${urc.fullName ?: urc.loginId}"
				redirect uri: '/'
			} else {
			log.debug "user object is not valid: ${user}, password ${user.password}"
				// May not be a unique loginId
				redirect controller: 'user'			
				//return [ user : urc ]
			}
		}
	}
	
	def profile(String id) {
		log.trace "Executing action: profile with params '$params' and id '$id'"
		def user = User.findByLoginId(id, [fetch: [profile: "eager"]])
		if (!user) {
			response.sendError(404)
		}
		else {
			[ profile: user.profile ]
		}
	}
	
	/**
	 * From graina2 chapter 10
	 * @param email
	 */
	def welcomeEmail(String email) {
		if (email) {
			sendMail {
				to email
				subject "Welcome to Coach Hubbub!"
				html view: "welcomeEmail", model: [ email: email ]
			}
			flash.message = "Welcome aboard"
		}
		redirect(uri: "/")
	}
}


class UserRegistrationCommand {
	String loginId
	String password
	String passwordRepeat
	//byte[] photo
	String fullName
	String bio
	String homepage
	String email
	String timezone
	String country
	String jabberAddress

	static constraints = {
		importFrom Profile
		importFrom User
		password(size: 6..8, blank: false,
				 validator: { passwd, urc ->
					return passwd != urc.loginId
				})
		passwordRepeat(nullable: false,
				validator: { passwd2, urc ->
					return passwd2 == urc.password
				})
	}
	
	String toString() {
		println "command: ${this.loginId}, ${this.password}, ${this.passwordRepeat}, ${this.fullName}, ${this.bio}, ${this.homepage},${this.email}, ${this.timezone},${this.country}, ${this.jabberAddress} "
	}
}
