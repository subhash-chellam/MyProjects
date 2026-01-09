#Author: R.Subhash Chellam
#Keywords Summary : Testing users API
@UserProgressReportUpdate

Feature: Testing All Progress Report update API operations
  This feature files tests all the possible API operations on Progress Report API

Background:
* def TokenGenrate = call read('TokenGeneration.feature')
* def Access_Token = 'Bearer '+ TokenGenrate.accessToken
* headers {Content-Type : 'application/json',Accept: 'application/json'}
* url testEndPoint

  @GetUserProgressReportupdate
  Scenario: Get the progress report update for the given user
    Given path 'learner-progress'
		And header Authorization = Access_Token
		And print 'Method invoked'
		*	def payload = read('resources/ProgressUpdate.json')
		* set payload.user_license_id = user_license_id
		* set payload.action_type = 'RECALCULATE'
		And print 'payload' + payload
		And request payload
		When method PUT
		Then status 200
		Then print response
   
  @GetUserProgressReportupdateOrchestrate
  Scenario Outline: Get the progress report update for the given user orchestrator
   	Given path '/users/' + <UserId> + '/courses'
		And header Authorization = Access_Token
		* def TokenGenrate1 = call read('UserAuthGeneration.feature@GetUserAuthToken'){ UserId : '#( <UserId> )' }
  	* def user_token = 'Bearer '+ TokenGenrate1.user_token1
    * param filter[status][] = ['active']
    And print 'user_token', user_token
		And header Authorization = user_token
		When method GET
		Then status 200
		Then print response
		* def LicenseLength = response.data.length
		* def CourseData = response.data
		And print 'LicenseLength = ',LicenseLength
		And print 'CourseData = ',CourseData
		* def Orchestrate = 
      """
     function(arg1, CourseData){
     		console.log("arg1 = ", arg1);
     		if (arg1 > 0) 
     		{
     				for(i = 0; i < arg1 ; i++)
     				{
     						console.log("Inside the calling function");
     						karate.call("ProgressReportUpdate.feature@GetUserProgressReportupdate", CourseData[i]);
     				}
     		}
			}
      """  
		* def limitOps = Orchestrate(LicenseLength, CourseData)
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
