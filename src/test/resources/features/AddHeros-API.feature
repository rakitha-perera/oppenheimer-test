Feature: Add Hero via API

  Scenario: User adds a new hero via API
    Given The hero "natid-007" does not exist in the database
    When user calls the API to create a hero with values
      | natid     | name       | gender | birth_date | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | MALE   | 1972-01-01 |            | 1000.00 | 50       |                |
    Then verify all the heroes are created in the database
      | natid-007 |

  Scenario: The user is not able to create a new hero if the id already exist
    Given The hero "natid-007" does not exist in the database
    When user calls the API to create a hero with values
      | natid     | name       | gender | birth_date | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | MALE   | 1972-01-01 |            | 1000.00 | 50       |                |
    When user calls the API to create a existing hero with values
      | natid     | name       | gender | birth_date | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | FEMALE | 1972-01-01 |            | 1000.00 | 50       |                |
    Then verify only 1 user exist with id "natid-007"

