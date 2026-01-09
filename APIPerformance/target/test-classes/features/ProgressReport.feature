#Author: R.Subhash Chellam
#Keywords Summary : Testing users API
@UserProgressReport

Feature: Testing All Progress Report API operations
  This feature files tests all the possible API operations on Progress Report API

Background:
* def TokenGenrate = call read('TokenGeneration.feature')
* def Access_Token = 'Bearer '+ TokenGenrate.accessToken
* headers {Content-Type : 'application/json',Accept: 'application/json'}
* url testEndPoint

	   
  @GetUserProgressReport
  Scenario: Get the progress report for the given user
    Given path 'progress-report'
		And header Authorization = Access_Token
		When method POST
		Then status 200
		And assert responseTime < 1000
		Then print response
		Then print response.data.length


