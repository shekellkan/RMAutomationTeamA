@FilterRoom
  Feature: Filter Room
    filter the rooms by name, code and capacity

    Background:
      Given I navigate to Login page
        And I login as "test" with password "Client123"
        And I navigate to Conference Rooms page

    Scenario Outline: filter the rooms for display name,code and capacity
      Given I open to "Floor1Room10" room
        And I edit the displayName "Custom", code "om11" and capacity "400"
      When I search a room by "<Criteria>"
        Then the room "Floor1Room10" should be listed

      Examples:
      |   Criteria   |
      | Room1        |
      | Custom       |
      | om11         |
      | 400          |
