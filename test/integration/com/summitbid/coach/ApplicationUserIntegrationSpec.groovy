package com.summitbid.coach

import grails.test.spock.*

class ApplicationUserIntegrationSpec extends IntegrationSpec  {
    

    def "Recovering from a failed save by fixing invalid properties"() {

        given: "A user that has invalid properties"
        def chuck = new ApplicationUser(applicationName: 'chuck_bot', password: 'tiny', apiKey: 'cafebeef00')
        assert chuck.save()  == null
        assert chuck.hasErrors()

        when: "We fix the invalid properties"
        chuck.password = "fistfist"
        chuck.save(failOnError: true)

        then: "The user saves and validates fine"
        chuck.validate()
        !chuck.hasErrors()
        chuck.save()
    
    }



}
