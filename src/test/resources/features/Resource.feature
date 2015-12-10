@resource
Feature: resource
  Description: create, remove, filter

  Background:
    Given I'm logged in with the user "admin" in the admin page


  Scenario Outline: create a Resource
    Given I go to Resources page
    When I create a Resource with values: "<Name>","<Display_Name>","<Description>" and "<Icon>"
    Then the Resource is displayed in the list of Resources
      When I go to Conference Room page
      Then the Resource should be displayed as a button in the Conference Room page header
    #And the Resource should be obtained using the API
  Examples:
  | Name        |Display_Name |Description                                   | Icon          |
  |Data_display |Data_display |the name and display name do not have spaces  | fa-eye        |
  |Board_Display|Board display|the display name have spaces                  | fa-eye-slash  |
  |Board1       |Board        |the name contains a number                    | fa-edit       |



