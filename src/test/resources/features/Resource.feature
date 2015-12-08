@resource
Feature: resource
  Description: create, remove, filter

  Background:
    Given I'm logged in with the user "test" and password "Client123"


  Scenario Outline: create a Resource
    Given I go to Resources page
    When I create a Resource with values: "<Name>","<Display_Name>","<Description>" and "<Icon>"
    Then the Resource is displayed in the list of Resources
    And the Resource should be displayed as a button in the Conference Room page header
    And the Resource should be obtained using the API
  Examples:
  | Name        |Display_Name|Description                             | Icon          |
  |Data_display |Data_display|the name and display name are the same  | fa-eye        |
  |Data_display |Data display|the name and display name are different | fa-eye-slash  |
  |Board1       |Board       |the name contains a number              | fa-edit       |


  Scenario: remove a Resource
    Given I go to Resources page
    And I create a Resource with the values "Mac","Mac computer" and "desktop_icon"
    When I remove the Resource
    Then the Resource is not longer displayed in the Resource list
    And the Resource should be removed as a button in the Conference Room page header
    And the resource should not be obtained using the API


  Scenario Outline: : Filter a Resource
    Given I create a Resource with the values "A04C5","Mac computer" and "desktop_icon"
      And I create a Resource with the values "Mac_Pro","Mac pro computer" and "desktop_icon"
      And I create a Resource with the values "Telephone","Inter Communicator" and "Phone_icon"
    When I filter the Resources with the criteria "<Criteria>"
    Then the result should be the same Resources for the UI and the DB
  Examples:
    |Criteria |
    |Mac      |
    |Com      |
    |Inter    |
    |data     |