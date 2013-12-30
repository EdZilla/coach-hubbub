package com.summitbid.coach

class UtilTagLib {
	
	static namespace = "hub"
	
	static defaultEncodeAs = 'html'
	//static encodeAsForTags = [tagName: 'raw']
	
		def certainBrowser = { attrs, body ->
			if(request.getHeader('User-Agent')
			[CA] =~ attrs.userAgent ) {
				out << body()
			}
		}
		
		def tinyThumbnail = { attrs ->
			def userId = attrs.loginId
			out << "<img src='"
			out << g.createLink(action: "tiny", controller: "image", id: loginId)
			out << "' alt='${loginId}'"
		}
}
