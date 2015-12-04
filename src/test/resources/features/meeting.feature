@Meetings
Feature: Meetings
  Background:
    Given Im logged in with the user "" and password "" in the tablet main page

  Scenario: Create meeting
    Given I select the room "FloorRoom10"
    When I navigate to available section for create a meeting
    Given I create a meeting with the user "" password "" with the subject "" at the time "","" with the attenders ""
    Then a information message should be displayed
    And the meeting information should be displayed in the next section of main page
    And the meeting should be obtained using the API

  Scenario: Create meetings at the same time
    Given I select the room "FloorRoom10"
    And I navigate to available section for create a meeting
    Given I create a meeting with the user "" password "" with the subject "" at the time "","" with the attenders ""
    When I create a second meeting in the same time with the user "" password "" with the subject "" at the time "","" with the attenders ""
    Then a error message is displayed
    And the error should be displayed using the API

  Scenario: remove a meeting
    Given I select the room "FloorRoom13"
    And I create a meeting with the user "" password "" with the subject "" at the time "","" with the attenders ""
    When I remove the meeting
    Then a message confirming the remove is displayed
    And the meeting is not in the schedule meetings for the room

  Scenario: Create a meeting room with state out of order
    Given I have a room "" with a state Out Of Order between the hours "" to ""
    And I select the same room ""
    And I navigate to available section for create a meeting
    Given I create a meeting with the user "" password "" with the subject "" at the time "","" with the attenders ""
    Then a error message is displayed

