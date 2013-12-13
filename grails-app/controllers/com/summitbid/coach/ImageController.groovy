package com.summitbid.coach

class PhotoUploadCommand {
    byte[] photo
    String loginId
}

class ImageController {

    static defaultAction = "form"

    def upload(PhotoUploadCommand puc) {
		log.trace "Executing action: '$actionName' with params '$params'"
        def user = User.findByLoginId(puc.loginId)
        user.profile.photo = puc.photo
        redirect controller: "user", action: 'profile', id: puc.loginId
    }

    def form() {
		log.trace "Executing action: '$actionName' with params '$params'"
        // pass through to upload form
        [ userList : User.list() ]
    }

    def renderImage(String id) {
		log.trace "Executing action: '$actionName' with params '$params'"
        def user = User.findByLoginId(id)
        if (user?.profile?.photo) {
            response.setContentLength(user.profile.photo.size())
            response.outputStream.write(user.profile.photo)
        } else {
            response.sendError(404)
        }
    }

}
