@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with <name> and <password>
    When I add product <productName> to Cart
    And I checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on the confirmationPage
    

    Examples: 
      | name  							 | password  | productName |
      | shubhanshu@gmail.com | India@123 | IPHONE 13 PRO |
