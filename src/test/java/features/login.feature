Feature: Login to Fitbit Mobile App

@PositiveLogin
Scenario Outline: User logging in
Given User is on landing page
When user clicks on login button
And user enters credentials and hit submit button as "<username>" and "<password>"
Then verify user is on homepage

Examples:
|username|password|
|pradeep.rinku3@gmail.com|Rinku13464|

@NegativeLogin
Scenario Outline: User logging in
Given User is on landing page
When user clicks on login button
And user enters credentials and hit submit button as "<user>" and "<pass>"
Then verify user gets error message

Examples:
|user|pass|
|abc@gmail.com|test123|