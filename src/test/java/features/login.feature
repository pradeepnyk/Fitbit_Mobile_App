Feature: Login to Fitbit Mobile App 

@PositiveLogin 
Scenario: User logging is successful 
	Given User is on landing page 
	When user clicks on login button 
	And user enters credentials and hit submit button 
		|Username|Password|
		|pradeepnyk464@gmail.com|Testing@123|
	Then verify user logged in successfully 
