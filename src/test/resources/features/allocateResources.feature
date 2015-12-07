@AllocateResource
  Feature: allocate resource
    allocate resource to room, remove resources of room

  Background:
    Given I'm logged in with the user "test"
      And I navigate to Conference Rooms page

  Scenario: Allocate resource to room
    Given I create a Resource with values: "Mac","Mac computer" and "desktop_icon"
      And I select the resource "Mac" button in the Conference Room page header
    When I open the Room Info page for "Floor1Room1"
      And I select Resource Associations tab
      And I associate the resource "data" with quantity "5"
    Then the resource should be displayed with the assigned quantity in the list
    When I go to Resources page
    Then the resource should be displayed with the room associated in Resources page
      And the Resource assigned to the room should be obtained using the API

  Scenario: remove resource association from room
    Given I navigate to "Floor1Room10" room page that have allocate a resource "film"
      And I navigate to Resource Associations tab
    When I remove the resource with the icon "film"
      And I select the resource "film" button in the Conference Room page header
    Then the Resource "film" is remove of the "Floor1Room10" Room
      And the Resource "film" not should be displayed with the room associated in the Resources page

    Given I have a Resource "" associated with the Conference Rooms ""
      And I select the resource "Mac" button in the Conference Room page header
    When I open the Room Info page for "Floor1Room1"
      And I select Resource Associations tab
      And I remove the association the resource "data" with quantity "5"
    Then the resource should not be displayed in the list
    When I go to Resources page
    Then the resource should not be displayed with the room associated in Resources page
      And no Resource associated to the room should be obtained using the API