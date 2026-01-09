#Author: R.Subhash Chellam
#Keywords Summary : Testing users API
@UserOps

Feature: Testing All users API operations
  This feature files tests all the possible API operations on users API

  Background:
* headers {Content-Type : 'application/json',Accept: 'application/json'}
* url testEndPoint
	   
  @GetUserCourses
  Scenario: Get all the list of courses based on userId	 taken
  	* def user_token = 'Bearer '+ '50287|kwbCO6dpMozJ7uT0DUEefdTiEL0eyzktCPz3skB1'
    Given path '/users/901050538/courses'
    * param filter[status][] = ['active']
    And print 'user_token', user_token
		And header Authorization = user_token
		When method GET
		Then status 200
		Then print response
		Then print response.data.length

