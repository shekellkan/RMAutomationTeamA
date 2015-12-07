@Rooms
Feature: Rooms
  Background:
    Given I'm logged in with the user "" and password ""
    And I navigate to Conference Rooms page

  Scenario Outline: Edit a room
    When I open to "Floor1Room1" room for edit
      And I edit its displayName "<displayName>" ,code "<code>" and capacity "<capacity>"
    Then a information message should be displayed
      And the room data should be the edited
      And the room edited should be obtained using the API

    Examples:
      | displayName | code | capacity |
      | Room1       | R1   | 100      |
      | RoomNew     | NR   | 300      |

  Scenario Outline: filter the rooms for display name, code and capacity
    Given I open to "Floor1Room10" room
    And I edit the displayName "Custom", code "om11" and capacity "400"
    When I search a room by "<Criteria>"
    Then the room "" should be listed

    Examples:
      |   Criteria   |
      | Room1        |
      | Custom       |
      | om11         |
      | 400          |

  Scenario Outline: Place a room to out of order
    Given I open to room "Floor1Room10" for edit
    When I go to the Out of Order Planning Tab
    And I configure the room with the option out of order "<Out Of Order>" at the time "8:00" to "10:00"
    Then a information message should be displayed
    And should display an icon on the Out Of Order column
    And the out of order state should be obtained using the API

    Examples:
      | Out Of Order |
      | Closed for maintenance |
      | Closed for reparations |

  Scenario Outline: Place a room to out of order with invalid date
    Given I open to room "Floor1Room10" for edit
    When I go to the Out of Order Planning Tab
    And I configure the room with the option out of order "<Out Of Order>" at the time "10:00" to "8:00"
    Then a error message should be displayed
    And the out of order state should not be obtained using the API

    Examples:
      | Out Of Order |
      | Closed for maintenance |
      | Closed for reparations |