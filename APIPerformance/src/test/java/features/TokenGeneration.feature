#Author: R.Subhash Chellam
#Keywords Summary : Token Generation API

@ignore  
Feature: API Authentication demo

Background:
* url AuthURL
* header Authorization = call read('jsRepo/basic-auth.js') { username: 'atoms-client', password: 'Welcome123#' }

Scenario: Generate Auth token for given scenario
And form field grant_type = 'password'
And form field client_id = 'security_testing'
And form field client_secret = 'tLHEXUEuLclJHSGtDRdkaMYgSMdjs0kr5Uoov7r4'	
When method POST
Then status 200
* def accessToken = response.data.access_token
Then print 'access -- token',accessToken

