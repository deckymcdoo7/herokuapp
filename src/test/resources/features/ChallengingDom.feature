Feature: Herokuapp Application

  Scenario: Navigating to github from banner
    Given I navigate to Herokuapp challenge dom page
    When I click fork me on github banner
    Then I am taking to the github page

  Scenario: Navigating to Elemental Selenium
    Given I navigate to Herokuapp challenge dom page
    And I scroll to Elemental Selenium link
    When I click Elemental Selenium link
    Then I am taking to the selenium page

  Scenario Outline: Changing and verifying the canvas answer
    Given I navigate to Herokuapp challenge dom page
    And I store the answer value
    When I click the "<keyword>" button
    Then The answer value should change

        Examples:
      | keyword       |
      | mainButton    |
      | alertButton   |
      | successButton |

    Scenario Outline: Checking the Table is order
      Given I navigate to Herokuapp challenge dom page
      Then The column "<keyword>" in the table should be ordered

      Examples:
      | keyword  |
      | Lorem    |
      | Ipsum    |
      | Dolor    |
      | Sit      |
      | Amet     |
      | Diceret  |

  Scenario Outline: Testing the edit button on each row of the table
    Given I navigate to Herokuapp challenge dom page
    When I click the "<keyword>" button for each row
    Then The URL is appended with "<keyword>"

    Examples:
      | keyword  |
      | edit     |
      | delete   |








