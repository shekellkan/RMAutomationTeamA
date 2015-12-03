@Rooms
Feature: Rooms
  Background:
    Given I navigate to login page
    And I login as "awagner" with password "Client123"

  Scenario Outline: Edit a room
    Given I navigate to Conference Rooms tab
    When I navigate to "Floor1Room1" room for edit
      And I edit its displayName "<displayName>" ,code "<code>" and capacity "<capacity>"
    Then a information message should be displayed
      And the room data should be the edited
      And the room edited should be obtained using the API

    Examples:
      | displayName | code | capacity |
      | Room1       | R1   | 100      |
      | RoomNew     | NR   | 300      |