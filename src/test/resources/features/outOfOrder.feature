@Rooms
Feature: Rooms
  Background:
    Given I navigate to login page
    And I login as "awagner" with password "Client123"


  Scenario Outline: Place a room to out of order
    When I navigate to "Floor1Room10" room for edit
      And I navigate the Out of Order Planning Tab
      And I select a option for out of order "<Out Of Order>"
    Then a information message should be displayed
    And should display an icon on the Out Of Order column
    And the out of order state should be obtained using the API

    Examples:
      |       Out Of Order     |
      | Closed for maintenance |
      | Closed for reparations |

  Scenario Outline: Place a room to out of order with invalid date
    When I navigate to "Floor1Room10" room for edit
      And I navigate the Out of Order Planning Tab
      And I select a option for out of order "<Out Of Order>"
      And I place a date with invalid hour range
    Then a error message should be displayed
      And the out of order state should not be obtained using the API

    Examples:
      |       Out Of Order     |
      | Closed for maintenance |
      | Closed for reparations |