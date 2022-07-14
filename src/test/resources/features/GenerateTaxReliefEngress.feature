Feature: Generate Tax Relief Egress File

  Scenario: Book Keeper generates tax relief egress file
    Given user opens the login page
    And   user login with username "bk" and password "pwd"
    When  user clicks on Generate Tax Relief File button
    Then  tax relief file is downloaded and has correct data
    And   a new record is added to the database with complete status

