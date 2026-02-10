Feature: Test Calculator
  Scenario: Add tow numbers
    Given I have a Calculator
    When I add 2 and 3
    Then the result should be 5