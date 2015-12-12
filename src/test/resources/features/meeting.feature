@Meetings
Feature: Meetings
  Background:
    Given I'm logged with the user "" in the tablet main page
      And I select the room "Floor1Room33"

#  Scenario Outline: Create a meeting
#    Given I navigate to Available section
#    When I create a Meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
#    Then an information message should be displayed "Meeting successfully created"
#    And the Meeting should be displayed in the Schedule bar
#    And the Meeting information should be displayed in the Next section of Main page
#    And the Meeting should be listed in the Meetings of Room using the API
#    Examples:
#      | Organizer   | Subject | From  | To    | Attendees         | Body      |
#      | Ariel.Rojas | meeting | 17:00 | 17:10 | test@exchange.com | Important |
#      | Ariel Rojas | meeting 2 | 17:15 | 17:25 |                   |           |
#      | Ariel Rojas | .-.-./  | 17:30 | 17:40 | test@exchange.com |           |

#  Scenario: Try to create a meeting at the same time than other meeting
#    Given I navigate to Available section
#      And I create a Meeting with the following information: "Ariel Rojas", "meeting", "18:20", "19:00", "test@exchange.com", "Important"
#    When I create other Meeting with the following information: "Carla Flores", "new meeting", "18:20", "19:00", "test@exchange.com", "Important"
#    Then an error message should be displayed
#      And the second Meeting should not be displayed in the Schedule bar
#      And the second Meeting information should not be displayed in the Next section
#      And the second Meeting should not be listed in the Meetings of Room using the API

#  Scenario: Remove a meeting
#    Given I navigate to Available section
#      And I create a Meeting with the following information: "Ariel Rojas", "meeting", "15:00", "16:00", "test@exchange.com", "Important"
#    When I remove the Meeting
#    Then an information message should be displayed "Meeting successfully removed"
#      And the meeting should not be displayed in the Schedule bar
#      And the meeting information should not be displayed in the Next section of Main page
#      And the Meeting should be listed in the Meetings of Room using the API

#  Scenario: Try to create a meeting in the room out of order
#    Given I have a room "FloorRoom10" with a state Out Of Order between the hours "16:00" to "17:00"
#      And I navigate to Available section of the Tablet main page
#    When I create a Meeting with the following information: "Ariel Rojas", "meeting", "16:05", "16:55", "test@exchange.com", "Important"
#    Then an error "<Error>" message should be displayed
#      And the Meeting should not be displayed in the Schedule bar
#      And the Meeting information should not be displayed in the Next section
#      And the Meeting should not be listed in the Meetings of Room using the API

#  Scenario: Update a meeting
#    Given I navigate to Available section
#      And I create a Meeting with the following information: "JeanCarlo.Rodriguez", "meeting", "16:00", "17:00", "test@exchange.com", "this meeting is update"
#    When I update the meeting information: "Miguel.Terceros", "meeting update", "16:10", "17:30", "test@exchange.com", "meeting updated"
#    Then an information message should be displayed "Meeting successfully updated"
#      And the Meeting should be displayed in the Schedule bar
#      And the Meeting information should be displayed in the Next section of Main page
#      And the Meeting should be listed in the Meetings of Room using the API

  Scenario Outline: Try to create a meeting with missing information
    Given I navigate to Available section
    When I create unsuccessfully a meeting with the following information: "<Organizer>", "<Subject>", "<From>", "<To>", "<Attendees>", "<Body>"
    Then an error "<Error>" message should be displayed
    And the meeting should not be displayed in the Schedule bar
    And the meeting information should not be displayed in the Next section of Main page
    And the Meeting should not be listed in the meetings of Room using the API

    Examples:
      |Organizer |Subject  |From |To   |Attendees         |Body       |Error                   |
      |          |meeting  |14:00|14:30|ronald.salvatierra|Be on Time |Organizer is required   |
      |jose      |         |15:00|15:30|                  |Bring Paper|Subject is required     |
      |jorge     |planning |16:00|15:30|                  |Bring Paper|Start time must be smaller than end time|
