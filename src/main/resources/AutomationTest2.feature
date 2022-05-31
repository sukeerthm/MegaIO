#AUTOMATION TEST 2- REQUIREMENT
#Go to this link https://mega.io/desktop, click the "Linux" button, you'll see a list of MEGA Desktop Apps for Linux platform.
#Write a script (program) to validate all the Linux Distros in the list are downloadable (when you click download, the links for downloading should be working properly)

#IMPLEMENTATION
  #Use Chromeoptions to change the download directory to "linuxDownloads"
  #Navigate to the https://mega.nz/desktop and select linux platform
  #Before downloading the versions, empty the download directory "linuxDownloads"
  #Select each version and download
  #Go to the directory "directory" and check file exists

Feature: Validate all the Linux Distros in the list are downloadable
  Scenario: validate all the Linux Distros in the list are downloadable
    Given I navigate to "https://mega.nz/desktop"
    Then I Click on button "Linux"
    And validate all the Linux versions in the list are downloadable


