@Resource
Feature: resource
  Description: create, remove, filter

  Background:
    Given Im logged in with the user "" and password ""

  Scenario: create a resource
    Given I go to Resources page
    When I create a Resource with values: "Mac","Mac computer" and "desktop_icon"
    Then the Resource is displayed in the list of Resources
    And the Resource should be displayed as a button in the Conference Room page header
    And the Resource should be obtained using the API

  Scenario: remove a resource
    Given I go to Resources page
    And I create a Resource with the values "Mac","Mac computer" and "desktop_icon"
    When I remove the Resource
    Then the Resource is not longer displayed in the Resource list
    And the resource should be removed as a button in the CR page header
    And the resource should not be obtained using the API

Scenario: Filter a resource
  Given I create a resource with the values "Mac","Mac computer" and "desktop_icon"
    And I create a resource with the values "Telephone","Inter Communicator" and "Phone_icon"
  When I filter the resources with the criteria "Criteria"
  Then the result should be the same resources for the UI and the DB