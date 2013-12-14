package com.summitbid.coach

class UtilTagLib {
	static defaultEncodeAs = 'html'
	//static encodeAsForTags = [tagName: 'raw']

		static namespace = "hub"
		def certainBrowser = { attrs, body ->
			if(request.getHeader('User-Agent')
			[CA] =~ attrs.userAgent ) {
				out << body()
			}
		}
}
