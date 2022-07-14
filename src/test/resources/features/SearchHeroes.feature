Feature: List and search users

  Background: Governor login to the system
    Given user opens the login page
    And user login with username "gov" and password "pwd"

  Scenario: Governor lists all the heroes
    When user lists all the heroes
    Then heroes table shows the correct records

  Scenario: Governor search for hero by natid
    Given user calls the API to create a hero with values
      | natid     | name       | gender | birth_date | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | MALE   | 1972-01-01 |            | 1000.00 | 50       |                |
    When user search for hero by "natid-007"
    Then heroes table shows the filtered records by "natid-007"

  Scenario: Governor search for hero by name
    Given user calls the API to create a hero with values
      | natid     | name       | gender | birth_date | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | MALE   | 1972-01-01 |            | 1000.00 | 50       |                |
    When user search for hero by "Bond"
    Then heroes table shows the filtered records by "Bond"

