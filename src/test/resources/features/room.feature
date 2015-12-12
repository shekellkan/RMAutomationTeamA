@Rooms
Feature: Rooms
  Background:
    Given I'm logged in with the user "admin" in the admin page
      And I go to Conference Rooms page

  @EditRoom
  Scenario Outline: Edit a Room
    When I open "Floor1Room11" Room for edit
      And I edit the displayName "<displayName>" ,code "<code>" and capacity "<capacity>"
    Then a information message should be displayed
      And the Room data should be the edited
      And the Room edited should be obtained using the API

     Examples:
      | displayName | code | capacity |
      | Room11      | R11  | 100      |
      | RoomNew     | NR   | 300      |

  @FilterRoom
  Scenario Outline: Filter the Rooms for display name, code and capacity
    Given I open "Floor1Room12" Room for edit
    And I edit the displayName "Custom" ,code "om11" and capacity "400"
    When I search a Room by "<Criteria>"
    Then the Room or Rooms "" should be listed
    Examples:
      |   Criteria   |
      | Room1        |
      | Custom       |
      | om11         |
      | 400          |


  Scenario Outline: Place a room to out of order
    Given I open "Floor1Room12" Room
    When I go to the Out of Order Planning Tab
    And I configure the Room with the option out of order "<Out Of Order>" at the time "8:00" to "10:00"
    Then a information message should be displayed
    And should display an icon on the Out Of Order column
    And the Out Of Order state should be obtained using the API

    Examples:
      | Out Of Order |
      | Closed for maintenance |
      | Closed for reparations |


  Scenario Outline: Place a room to out of order with invalid date
    Given I open to Room "Floor1Room10" for edit
    When I go to the Out of Order Planning Tab
    And I configure the Room with the option Out Of Order "<Out Of Order>" at the time "10:00" to "8:00"
    Then a error message should be displayed
    And the Out Of Order state should not be obtained using the API

    Examples:
      | Out Of Order |
      | Closed for maintenance |
      | Closed for reparations |


