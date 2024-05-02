Feature: verify get API



Scenario: user is able to get successful response
Given user has endpoint "legislation-lists/find"
Then user get successful response
When status code is "200 Ok"
When response time must be less as expected

Scenario: user enter wrong endpoint
Given user has wrong endpoint "legislation-lists"
Then user get bad request
When status new code is "404 Not Found"
 
Scenario: user wants to post the api
Given user has post endpoint "legislation-lists/findByKeys"
Then user has successful body response
When status code for post is "200 Ok"
When response time less then expected

Scenario Outline: user wants to give params
Given user give get endpoint "legislation-lists/find" and give params "<value>" and "<key>"
Then user get successful params response
When user get status code is "200 Ok"
When user get response in limited time

Examples:

|key|value|
|resourceStatus|UPDATED|

Scenario: User enter invalid endpoin
Given user enter wrong post method endpoint "legislation-lists/find"
Then user not get any response body
When user received a status code "404 Not Found"
When response time is less

Scenario: User not enter request body in post
Given user enter the endpoint "legislation-lists/findByKeys"
Then user not received any response
When user get the response code with "400 bad Request"
When the response time is lessthen expected




