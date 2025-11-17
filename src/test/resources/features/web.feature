Feature: Some feature we want to test

  Background: open web site
    Given I open web site

  @severity=blocker
  Scenario: Test W3Schools Iframe Content
    Given I load W3Shcools Page
    When I accept cookies if present
    Then I can see content of iFrame

  @severity=trivial
  Scenario Outline: Test W3Schools Iframe Content
    And I press register new user
    And I set username to <userName>
    And I set password to <pwd>
    Examples:
      | pwd             | userName    |
      | "badPassword"   | "testUser1" |
      | "a123"          | "testUser2" |
      | "badpassword1"  | "testUser3" |
      | "ASDASDASDASD1" | "testUser4" |

  Scenario: demo data table
    Given Demo data table list
      | test1 |
      | test2 |
      | test3 |
      | test4 |
      | test5 |