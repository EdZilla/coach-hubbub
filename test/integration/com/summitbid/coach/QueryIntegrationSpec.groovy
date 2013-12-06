package com.summitbid.coach


import grails.test.spock.*

/**
 *
 */
class QueryIntegrationSpec extends IntegrationSpec {

	void "Simple property comparison"() {
		when: "Users are selected by a simple password match"
		def users = User.where { password == "testing" }.list(sort: "loginId")
		
		then: "The users with that password are returned"
		users*.loginId == ["frankie"]
	}

	void "Multiple criteria"() {
		when: "A user is selected by loginId or password"
		def users = User.where {
			loginId == "frankie" || password == "crikey"
		}.list(sort: "loginId")

		then: "The matching loginIds are returned"
		users*.loginId == ["dillon", "frankie", "sara"]
	}

	void "Query on association"() {
		when: "The 'following' collection is queried"
		def users = User.where {
			following.loginId == "sara"
		}.list(sort: "loginId")

		then: "A list of the followers of the given user is returned"
		users*.loginId == ["phil"]
	}

	void "Query against a range value"() {
		given: "The current date & time"
		def now = new Date()
		println "the date is ${now}"
		println "the previous date is ${now - 1}"

		when: "The 'dateCreated' property is queried"
		def users = User.where {
			dateCreated in (now - 1)..now
		}.list(sort: "loginId", order: "desc")

		then: "The users created within the specified date range are returned"
		users*.loginId == ["phil", "jeff", "graeme", "frankie", "burt", "admin"]
		//users*.loginId == ["phil", "jeff", "graeme", "frankie", "burt"]
	}

	void "Retrieve a single instance"() {
		when: "A specific user is queried with get()"
		def user = User.where {
			loginId == "phil"
		}.get()

		then: "A single instance is returned"
		user.password == "thomas"
	}
	
	
//	void "Test a criteriaQuery"() {
//		when: "We define a criteria query for users"
//		def users = User.createCriteria().list { 
//			and {
//				ilike "loginId", "%${query}%" 
//				if (params.fromDate) {
//					ge "dateCreated", parseDate(params.fromDate)
//				}
//			}
//		}
//		then: "we get a list of users that fit the criteria"
//		println "users: "
//		
//	}
	
	
	void "Test a criteria query"() {
		when: "define a cq"
		def tagList = Post.withCriteria {
			createAlias "tags", "t" 
			user { eq "loginId", "phil" }
			projections {
				groupProperty "t.name" 
				count "t.id" 
			}
		}
		then: "check it out"
		println "tagList: ${tagList}"
		println "done with cq"
	}
	
	
	void "HQL query"() {
		when: "define a HQL query"
		def result = Post.executeQuery("select t.name, count(t.id) from Post p " +
			"join p.tags as t where p.user.loginId = ? group by t.name", ['phil'])
		then: "done"
		println "done. result is: ${result}"
	}
}
