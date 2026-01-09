#Author: R.Subhash Chellam
#Keywords Summary : Testing User Level Auth Generation
@UserAuthGeneration

Feature: Testing All users API operations
  This feature files tests all the possible API operations on users API

Background:
* def TokenGenrate = call read('TokenGeneration.feature')
* def Access_Token = 'Bearer '+ TokenGenrate.accessToken
* headers {Content-Type : 'application/json',Accept: 'application/json'}
* url testEndPoint

  @GetUserToken
  Scenario Outline: Get all the list of topics based on License ID
    Given path '/users/', <UserId>,'/tokens' 
		And header Authorization = Access_Token
		When method POST
		Then status <status>
		Then print 'Print the response' , response
		* def user_token1 = response.data.access_token
		Then print user_token1

	 Examples: 
	   | UserId  | status  |
	   | 901050538 |  200  |


  @GetUserAuthToken
  Scenario: Get all the list of topics based on License ID
    Given path '/users/'+ UserId + '/tokens' 
		And header Authorization = Access_Token
		When method POST
		Then status 200
		Then print 'Print the response' , response
		* def user_token1 = response.data.access_token
		Then print user_token1

	   
