
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
   Given I landed on Ecommerce page
   When Logged in with <name> and <password>
   Then "Incorrect email or password." message is displayed
    Examples: 
      | name  							 | password  |
      | shubhanshu@gmail.com | India@12  |
