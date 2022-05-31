# MegaIO Web Automation

#Required Tools
  1. java 
  2. Maven
  3. IDE (intelliJ)

#Project Structure
  1. Its a maven project, Use the POM file to load the dependencies  
  1. Feature files are in - /src/main/resources
  2. StepDefinitions & Page Objects - /src/main/java
  3. TestRunner class to run tests and create reports - /src/main/java
  4. Required drivers to run the tests - /src/test/java/requiredTools
  5. Properties files - /src/test/java/configDetails
  6. Download location for linux versions - /src/test/java/linuxDownloads

#Reports - HTML,XML,JSON
  1. HTML,XML and JSON reports are generated using TestRunner class and can be found at - /target/cucumber-reports
