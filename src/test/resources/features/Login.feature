Feature: Login

  Scenario Outline: Users can successfully login to the system
    Given user opens the login page
    When  user login with username "<user name>" and password "<password>"
    Then  verify dashboard title is "<dashboard title>"

    Examples:
      | user name | password | dashboard title       |
      | clerk     | pwd      | Clerk Dashboard       |
      | bk        | pwd      | Book Keeper Dashboard |
      | gov       | pwd      | Governor Dashboard    |

  Scenario Outline: User is not able to login to the system if the credentials are incorrect
    Given user opens the login page
    When  user login with username "<user name>" and password "<password>"
    Then  verify an error message is shown

    Examples:
      | user name         | password      |
      | clerk             | incorrect_pwd |
      | non_existing_user | pwd           |
