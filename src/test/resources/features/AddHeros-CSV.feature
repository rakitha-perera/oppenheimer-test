Feature: Add Hero via CSV

  Scenario: Clerk adds a heroes by uploading a CSV
    Given user opens the login page
    And user login with username "clerk" and password "pwd"
    When user uploads a csv file with values
      | natid     | name       | gender | birth_date          | death_date | salary  | tax_paid | brownie_points |
      | natid-007 | James Bond | MALE   | 1972-01-01T00:00:00 |            | 1000.00 | 50       | 10             |
    Then verify system displays the created successfully message
    And verify all the heroes are created in the database
      | natid-007 |

  Scenario: Clerk is not able to add heroes by uploading a CSV with incorrect data
    Given user opens the login page
    And user login with username "clerk" and password "pwd"
    When user uploads a csv file with values
      | natid     | name       | gender | birth_date          | death_date | salary  | tax_paid | brownie_points |
      | 007       | James Bond | MALE   | 1972-01-01T00:00:00 |            | 1000.00 | 50       |                |
      | natid-007 |            | MALE   | 1972-01-01T00:00:00 |            | 1000.00 | 50       |                |
      | natid-007 |            | MALE   | 1972-01-01T00:00:00 |            | 1000.00 | 50       | 100            |
    Then verify an error message is shown on dashboard


