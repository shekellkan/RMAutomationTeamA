@resource
Feature: resource
  Description: create, remove, filter

  Background:
    Given I'm logged in the admin page
      And I go to Resources page

  @createResource
  Scenario Outline: create a Resource
    When I create a Resource with values: "<Name>","<Display_Name>","<Description>" and "<Icon>"
    Then the Resource is displayed in the list of Resources
      When I go to Conference Rooms page
      Then the Resource should be displayed as a button in the Conference Room page header
    And the Resource should be obtained using the API
  Examples:
  | Name        |Display_Name |Description                                   | Icon          |
  |Data_display |Data_display |the name and display name do not have spaces  | fa-eye        |
  |Board_Display|Board display|the display name have spaces                  | fa-eye-slash  |
  |Board1       |Board        |the name contains a number                    | fa-edit       |

  Scenario Outline: : Filter a Resource
    When I filter the Resources with the criteria "<Criteria>"
    Then the result of filter should be the same Resources for the UI and the DB
  Examples:
  |Criteria |
  |Mac      |
  |ac       |
  |Pro      |
  |Pc       |

  Scenario: remove a Resource
    Given I create a Resource for API with values: "Mac1","Mac computer1","My mac computer1" and "fa-desktop"
    When I remove the Resource
    Then the Resource is not longer displayed in the Resource list
      When I go to Conference Rooms page
    And the Resource should be not displayed as a button in the Conference Room page header
    And the resource should not be obtained using the API