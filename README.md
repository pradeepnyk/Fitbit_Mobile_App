# Content

## Test Execution in a glance:
### This is a Mobile Application Automation framework which supports both Android & iOS platform. This is just a initial work on a scenario to demonstrate the Automation testing of a Login feature. We can test this feature on the local machine and in cloud as well, which can then be further configured to run in CI tool for regression coverage

## Below are some guidelines how to use this repository and few details about the work

- Folder structure

![Image of Yaktocat](https://github.com/pradeepnyk/Fitbit_Mobile_App/blob/master/folder_structure.jpg)


- Dependencies
    - Java (JDK 8)
    - Selenium
    - Maven
    - TestNG
    - Cucumber
    - Cucumber reporting, Extent reporting
    - Log4J

- Assumption
    - At this stage, this supports only Android application
    - xml file is setup according to my own setup, user can change the values as per their need
    - Remote server settings values are only for reference purpose, this can also be updated to support execution in cloud

- Device coverage
    - Real physical device
    - Emulator in local machine
    - Real device in cloud

- Execution report
    - Cucumber report
    - Extent report
    - HTML report
    - Surefire report

Execution command prompt:
`mvn clean verify -DsuiteXmlFile=(required test file name)`

Notes:
- Test case re-usablity is possible which will drive the further execution steps
- Maintainability - is possible by adding few more help methods like tap, e.g. - ScrollByDimension, LongTap
- Emulator support, real device support
