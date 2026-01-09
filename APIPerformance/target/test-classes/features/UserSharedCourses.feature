#Author: R.Subhash Chellam
#Keywords Summary : Testing users API shared courses list
@UserOpsSharedCourse

Feature: Testing All users API shared courses operations
  This feature files tests all the possible API shared courses operations on users API

Background:
* def TokenGenrate = call read('TokenGeneration.feature')
* def Access_Token = 'Bearer '+ TokenGenrate.accessToken
* headers {Content-Type : 'application/json',Accept: 'application/json'}
* url testEndPoint

	   
  @GetUserSharedCourses
  Scenario Outline: Get all the list of shared courses based on userId
  	* def TokenGenrate1 = call read('UserAuthGeneration.feature@GetUserAuthToken'){ UserId : '#( <UserId> )' }
  	* def user_token = 'Bearer '+ TokenGenrate1.user_token1
    Given path '/users/' + <UserId> + '/courses/shared'
    And print 'user_token', user_token
		And header Authorization = user_token
		When method GET
		Then status 200
		Then print response
		Then print response.data.length
		
	Examples:
	|UserId|
|6756121|
