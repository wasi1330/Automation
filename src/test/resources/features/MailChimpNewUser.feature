Feature: MailChimpNewUser
  Scenario: Create a new user
    Given I have entered a correct email "hello1@hello.com"
    And I have entered a valid username "ffpmwg1234445 "
    And I have entered a valid password "Sweden123."
    When I pressed the sign up button
    Then an account is created "Check your email"


  Scenario: Create a new user with long username
    Given I have entered a correct email "hello@hello.com"
    And I have entered a valid username "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff123"
    And I have entered a valid password "Sweden123."
    When I pressed the sign up button
    Then an account is not created for long username is given "Enter a value less than 100 characters long"


  Scenario: Create a new user when user already exists
    Given I have entered a correct email "hello@hello.com"
    And I have entered a valid username "fpmwg123"
    And I have entered a valid password "Sweden123."
    When I pressed the sign up button
    Then an account is not created for user already exists "Great minds think alike"


  Scenario: Create a new user without email
    Given I have entered a correct email ""
    And I have entered a valid username "fpmwk123"
    And I have entered a valid password "Sweden123."
    When I pressed the sign up button
    Then an account is not created as email is required "An email address must contain a single @."