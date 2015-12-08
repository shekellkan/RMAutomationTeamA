@Meetings
Feature: Meetings
  Background:
    Given I'm logged in with the user "" in the tablet main page
      And I select the room "FloorRoom10"


  Scenario Outline: Create a meeting
    Given I navigate to Available section
    When I create a Meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an information message should be displayed
      And the Meeting should be displayed in the Schedule bar
      And the Meeting information should be displayed in the Next section
      And the Meeting should be listed in the Meetings of Room using the API
  Examples:
    | Organizer   | Subject | From  | To    | Attendees         | Body      |
    | Ariel.Rojas | meeting | 21:00 | 22:00 | test@exchange.com | Important |
    | Ariel Rojas | meeting | 21:00 | 22:00 |                   |           |
    | Ariel Rojas | .-.-./  | 21:00 | 22:00 | test@exchange.com |           |


  Scenario: Try to create a meeting at the same time than other meeting
    Given I navigate to Available section
      And I create a Meeting with the following information: "Ariel Rojas", "meeting", "22:00", "23:00", "test@exchange.com", "Important"
    When I create a Meeting with the following information: "Carla Flores", "new meeting", "22:00", "23:00", "test@exchange.com", "Important"
    Then an error "<Error>" message should be displayed
      And the second Meeting should not be displayed in the Schedule bar
      And the second Meeting information should not be displayed in the Next section
      And the second Meeting should not be listed in the Meetings of Room using the API


  Scenario: Remove a meeting
    Given I navigate to Available section
      And I create a Meeting with the following information: "Ariel Rojas", "meeting", "22:00", "23:00", "test@exchange.com", "Important"
    When I remove the Meeting
    Then an information message should be displayed
      And the Meeting should be removed from the the Schedule bar
      And the Meeting information should be removed from the Next section
      And the Meeting should be listed in the Meetings of Room using the API


  Scenario: Try to create a meeting in the room out of order
    Given I have a room "FloorRoom10" with a state Out Of Order between the hours "" to ""
      And I navigate to Available section of the Tablet main page
    When I create a Meeting with the following information: "Ariel Rojas", "meeting", "22:00", "23:00", "test@exchange.com", "Important"
    Then an error "<Error>" message should be displayed
      And the Meeting should not be displayed in the Schedule bar
      And the Meeting information should not be displayed in the Next section
      And the Meeting should not be listed in the Meetings of Room using the API
