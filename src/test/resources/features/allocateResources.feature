@AllocateResource
  Feature: allocate resource
    allocate resource to room, remove resources of room

  Background:
    Given I login as "test" with password "Client123"
      And I navigate to Conference Rooms page

  Scenario: allocate resource to room
    Given I create a Resource with values: "Mac","Mac computer" and "desktop_icon"
    When I select the resource "Mac" button in the Conference Room page header
      And I open the Room Info page for "Floor1Room1"
      And I select Resource Associations tab
      And I associate the resource "data" with quantity "5"
    Then the resource should be displayed with the assigned quantity in the list
      And the resource should be displayed with the room associated in Resources page
      And the resource assigned to the room should be obtained using the API

  Scenario: remove resource of room
    Given I navigate to "Floor1Room10" room page that have allocate a resource "film"
      And I navigate to Resource Associations tab
    When I remove the resource with the icon "film"
      And I select the resource "film" button in the Conference Room page header
    Then The resource "film" is remove of the "Floor1Room10" room
      And the resource "film" not should be displayed with the room associated in the Resources page
