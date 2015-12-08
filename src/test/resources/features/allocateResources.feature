@AllocateResource
  Feature: allocate resource
    allocate resource to room, remove resources of room

  Background:
    Given I'm logged in with the user "test"
      And I navigate to Conference Rooms page


  Scenario: Allocate resource to room
    Given I create a Resource with values: "Mac","Mac computer" and "desktop_icon"
      And I select the Resource "Mac" button in the Conference Room page header
    When I open the Room Info page for "Floor1Room1"
      And I select Resource associations tab
      And I associate the Resource "data" with quantity "5"
    Then the Resource should be displayed with the assigned quantity in the list
    When I go to Resources page
    Then the Resource should be displayed with the Room associated in Resources page
      And the Resource assigned to the Room should be obtained using the API


  Scenario: remove resource association from room
    Given I have a Resource "" associated with the Conference Rooms ""
      And I select the Resource "Mac" button in the Conference Room page header
    When I open the Room Info page for "Floor1Room1"
      And I select Resource associations tab
      And I remove the association the Resource "data" with quantity "5"
    Then the Resource should not be displayed in the list
    When I go to Resources page
    Then the Resource should not be displayed with the room associated in Resources page
      And no Resource associated to the Room should be obtained using the API