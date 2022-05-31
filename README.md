# MegaIO Web Automation

#Required Tools
  1. java 
  2. Maven
  3. IDE (intelliJ)
  4. Cucumber and Gherkins plugins for the IDE 

#How to execute the tests
1. Install the above required tools.
2. Its a maven project, so use the POM file to load the dependencies
3. Then use TestRunner class to run all the tests or use feature files to run individual tests

#Project Structure
  1. Feature files are in - /src/main/resources
  2. StepDefinitions & Page Objects - /src/main/java
  3. TestRunner class to run tests and create reports - /src/main/java
  4. Required drivers to run the tests - /src/test/java/requiredTools
  5. Properties files - /src/test/java/configDetails
  6. Download location for linux versions - /src/test/java/linuxDownloads


#AUTOMATION TEST 1- IMPLEMENTATION
  1. Go to the website
  2. Navigate to Loginpage, enter details and login
  3. Right-click on select 'New File'
  4. On the dialog box enter file name 'a.txt' and click create (Will also check for duplicate files)
  6. Enter the content 'megaTesting' and save. 
  7. Close the file editor
  8. Validate file is created
  9. Then right-click on the file 'a.txt' and Click Remove
  10. Go to Rubbish bin tab
  11. Select file 'a.txt' and right-click and select restore
  12. Go to File manager and validate file is restored 

#AUTOMATION TEST 2- IMPLEMENTATION
  1. Use Chromeoptions to change the download directory to "linuxDownloads"
  2. Navigate to the https://mega.nz/desktop and select linux platform
  3. Before downloading the versions, empty the download directory "linuxDownloads"
  4. Select each version and download
  5. Go to the directory "directory" and check file exists

#Reports - HTML,XML,JSON
  1. HTML,XML and JSON reports are generated using TestRunner class and can be found at - /target/cucumber-reports

#HTML REPORT

![image](https://user-images.githubusercontent.com/15187298/171117343-42dbee61-8505-4347-b002-d8bfd24bf0a5.png)

