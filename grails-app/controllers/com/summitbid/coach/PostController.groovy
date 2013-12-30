package com.summitbid.coach

class PostController {
    static scaffold = true
	
	def postService
	// def defaultAction = 'timeline'
	
	def index() {
		log.trace "Executing action: index with params '$params'"
		if (!params.id) {
			params.id = "admin"
			//redirect controller: 'user', action: 'index'
		}
		redirect action: 'timeline', params: params
	}
	
	def timeline() {
		log.trace "Executing action: timeline with params '$params'"
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
		log.trace "Executing action: addPost with params '$params'"
        try {
            def newPost = postService.createPost(id, content)
            flash.message = "Added new post: ${newPost.content}"
        } catch (PostException pe) {
            flash.message = pe.message
        }
        redirect action: 'timeline', id: id
    }
	
	/**
	 * Add a post using AJAX
	 * @param content
	 * @return render to the postentries template
	 */
	def addPostAjax(String content) {
		log.trace "Executing action addPostAjax with content '${content}' and params $params"
		log.debug "session: $session"
		try {
			def newPost = postService.createPost(session.user.loginId, content)
			def recentPosts = Post.findAllByUser(session.user, [sort: "dateCreated", order: "desc", max: 20])
			render template: "postentries", collection: recentPosts, var: "post"
		} catch (PostException pe) {
			render {
				div(class:"errors", pe.message)
			}
		}
	}
	
	/**
	 * Make the url tiny. 
	 * @param fullUrl
	 * @return 
	 */
	def tinyUrl(String fullUrl) {
		log.trace "Executing action tinyUrl with content '${fullUrl}' and params $params"
		def origUrl = fullUrl?.encodeAsURL()
		def tinyUrl = new URL("http://tinyurl.com/api-create.php?url=${origUrl}").text
		log.debug "tiny url: ${tinyUrl}"
		render(contentType:"application/json") {
			urls(small: tinyUrl, full: fullUrl)
		}
	}
	
}
