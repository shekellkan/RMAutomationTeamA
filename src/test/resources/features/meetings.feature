@Meetings
Feature: Rooms
  Background:
    Given I navigate to Tablet page
    And I log on as "awagner" with password "Client123"

  Scenario Outline: Create meeting
    Given I select the room "FloorRoom10"
    When I navigate to available section for create a meeting
    And I enter the organizer "<Organizer>", subject "<Subject>" and the attendees "<Attendee>"
    Then a information message should be displayed
      And the meeting information should be displayed in the next section of main page
      And the meeting should be obtained using the API
    Examples:
    | Organizer   | Subject |           Attendee        |
    | Ariel Rojas | Java    | ariel.rojas@Forest1.local |