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