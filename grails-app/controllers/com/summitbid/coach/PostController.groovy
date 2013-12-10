package com.summitbid.coach

class PostController {
    static scaffold = true
	
	def postService
	// def defaultAction = 'timeline'
	
	def index() {
		if (!params.id) {
			params.id = "chuck_norris"
			//redirect controller: 'user', action: 'index'
		}
		redirect action: 'timeline', params: params
	}
	
	def timeline() {
		def user = User.findByLoginId(params.id)
		if (!user) {
			response.sendError(404)
		} else {
			[ user : user ] 
		}
	}
	
	/**
	 * add a post to a particular user
	 * @param id id of user
	 * @param content post content
	 * @return redirect to timeline of user
	 */
	def addPost(String id, String content) {
        try {
            def newPost = postService.createPost(id, content)
            flash.message = "Added new post: ${newPost.content}"
        } catch (PostException pe) {
            flash.message = pe.message
        }
        redirect action: 'timeline', id: id
    }
	
}
