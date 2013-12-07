package com.summitbid.coach

class PostController {
    static scaffold = true
	// def defaultAction = 'timeline'
	
	def index() {
		if (!params.id) { 
			redirect controller: 'user', action: 'index'
			//params.id = "chuck_norris"
		}
		else {
			redirect action: 'timeline', params: params
		}
	}
	
	def timeline() {
		def user = User.findByLoginId(params.id)
		if (!user) {
			response.sendError(404)
		} else {
			[ user : user ] 
		}
	}
	
	def addPost() {
		def user = User.findByLoginId(params.id)
		if (user) {
			def post = new Post(params)
			user.addToPosts(post)
			if (user.save()) {
				flash.message = "Successfully created Post"
			} else {
				flash.message = "Invalid or empty post"
			}
		} else {
			flash.message = "Invalid User Id"
		}
		redirect(action: 'timeline', id: params.id)
	}
	
}
