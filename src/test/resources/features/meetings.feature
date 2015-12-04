@Meetings
Feature: Meetings
  Background:
    Given Im logged in with the user "" and password "" in the tablet main page

  Scenario Outline: Create meeting
    Given I select the room "FloorRoom10"
    When I navigate to available section for create a meeting
      And I create a meeting with organizer "<Organizer>", subject "<Subject>" and attendees "<Attendee>"
    Then a information message should be displayed
      And the meeting information should be displayed in the next section of main page
      And the meeting should be obtained using the API
    Examples:
    | Organizer   | Subject |           Attendee        |
    | Ariel Rojas | Java    | ariel.rojas@Forest1.local |