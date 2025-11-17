Feature: Test interaction between REST API and SQL

  @severity=critical
  Scenario: Retrieve people from API and store them to DB
    Given I write down the amount of people in DB as "starting people count"
    Given I request 3 people from random user API as "new users"
    When I store "new users" in Database
    Then I have 3 more people in DB then "starting people count"