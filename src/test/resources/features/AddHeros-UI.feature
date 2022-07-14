Feature: Add Hero via UI

  Scenario Outline: Clerk adds a new hero via UI
    Given user opens the login page
    And   user login with username "clerk" and password "pwd"
    And   The hero "<natid>" does not exist in the database
    When  user creates the hero via UI with values "<natid>" "<name>" "<gender>" "<birth_date>" "<death_date>" "<salary>" "<tax_paid>" "<brownie_points>"
    Then  verify the hero "<natid>" is exist in the database

    Examples:
      | natid     | name       | gender | birth_date | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | MALE   | 1972-01-01 |            | 1000.00 | 50       |                |

  Scenario Outline: Clerk is not able to add a new hero with incorrect or missing values
    Given user opens the login page
    And   user login with username "clerk" and password "pwd"
    And   The hero "<natid>" does not exist in the database
    When  user creates the hero via UI with values "<natid>" "<name>" "<gender>" "<birth_date>" "<death_date>" "<salary>" "<tax_paid>" "<brownie_points>"
    Then  verify the error message "<error msg>" is shown

    Examples:
      | natid     | name       | gender | birth_date          | death_date | salary  | tax_paid | brownie_points | error msg              |
      | 007       | James Bond | MALE   | 1972-01-01          |            | 1000.00 | 50       |                | natid => Invalid natid |
      | natid-007 |            | MALE   | 2072-01-01T00:00:00 |            | 1000.00 | 50       | 100            | Invalid name           |



