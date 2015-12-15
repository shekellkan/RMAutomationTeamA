@AllocateResource
Feature: allocate resource
  allocate resource to room, remove resources of room

  Background:
    Given I'm logged in with the user "admin" in the admin page

  @createResource
  Scenario: Allocate resource to room
    Given I go to Resources page
      And I create a Resource for API with values: "Data_For_Allocate","Data_For_Allocate","Description for the data for allocate resource" and "fa-crosshairs"
      And I go to Conference Rooms page
      And I select the Resource in the Conference Room page header
    When I search a Room by "Floor1Room17"
      And I open "Floor1Room17" Room for edit
      And I go to Resource Association tab
      And I associate the Resource with quantity "5"
    Then the Resource should be displayed with the assigned quantity in the list
    When I go to Resources page
    Then the Resource should be displayed with the Room Associated in Resources Association tab
      And the Resource assigned to the Room should be obtained using the API

  @createResource
  Scenario: remove resource association from room
    Given I go to Resources page
      And I create a Resource for API with values: "For remove association","For remove association","For remove association description" and "fa-crosshairs"
      And I go to Conference Rooms page
      And I associate the Resource to the Room "Floor1Room18" with quantity "15"
    When I search a Room by "Floor1Room18"
      And I open "Floor1Room18" Room for edit
      And I go to Resource Association tab
      And I remove the association with the Resource
    Then the Resource should be not associated to the Room in the list
    When I go to Resources page
    Then the Resource should not be displayed with the Room Associated in Resources Association tab
      And no Resource associated to the Room should be obtained using the API