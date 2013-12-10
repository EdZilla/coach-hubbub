package com.summitbid.coach

import grails.transaction.Transactional


class PostException extends RuntimeException {
	String message
	Post post
}

@Transactional
class PostService {
//	static transactional = true

	/**
	 * add a post to user 
	 * @param loginId login id of user
	 * @param content post content
	 * @return post or throw PostException
	 */
	Post createPost(String loginId, String content) {
		def user = User.findByLoginId(loginId)
		if (user) {
			def post = new Post(content: content)
			user.addToPosts(post)

			if (post.validate() && user.save()) {
				return post
			}
			else {
				throw new PostException(message: "Invalid or empty post", post: post)
			}
		}

		// No user 
		throw new PostException(message: "Invalid login ID")
	}
}

