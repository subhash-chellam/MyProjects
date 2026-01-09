#Author: R.Subhash Chellam
#Keywords Summary : Testing users API get Active sessions
@UserOpsActiveSessions

Feature: Testing All users Active sessions 
  This feature files tests all the possible API Users active sessions

Background:
* def TokenGenrate = call read('TokenGeneration.feature')
* def Access_Token = 'Bearer '+ TokenGenrate.accessToken
* headers {Content-Type : 'application/json',Accept: 'application/json'}
* url testEndPoint

	   
  @GetUserSharedCourses
  Scenario Outline: Get all the active sessions based on userId
  	* def TokenGenrate1 = call read('UserAuthGeneration.feature@GetUserAuthToken'){ UserId : '#( <UserId> )' }
  	* def user_token = 'Bearer '+ TokenGenrate1.user_token1
    Given path '/users/' + <UserId> + '/sessions'
    And print 'user_token', user_token
		And header Authorization = user_token
		When method GET
		Then status 200
		Then print response
		Then print response.data.length
		
	Examples:
	|UserId|
|901050985|
|901050672|
|901050669|
|901050668|
|901050667|
|901050666|
|901050665|
|901050664|
|901050984|
|901050538|