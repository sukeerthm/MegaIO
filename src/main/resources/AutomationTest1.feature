#REQUIREMENT - Create a text file a.txt with content megatesting in it, Delete the File and then Restore from Rubbish Bin


Feature: Create file, Delete File and Restore file from rubbish bin
  Background: Login to mega website
    Given I login to mega website using email "sukeerthkumar@gmail.com" and password "sukeerth"

  Scenario Outline: Create text file (a.txt), Delete it and verify it can be restored from Rubbish Bin
    Then I create new text file "<filename>" with content "megatesting"
    And I verify file "<filename>" is created
    Then I Delete the file "<filename>"
    And I verify File "<filename>" is deleted
    And I navigate to rubbish bin folder
    And I restore the deleted file "<filename>" from rubbish bin folder
    And I verify file "<filename>" is restored successfully

    Examples:
      |filename |
      |a |


