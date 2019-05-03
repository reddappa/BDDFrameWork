Feature: Conducting Data Driven Testing on Automation Practice website

  Scenario Outline: Verify the Automation Login Functionality with DDT
    Given i am on the Home page of "http://practice.automationtesting.in/" Automation Practice website
    When  click "My Account" link on Automation Practice website
    Then  verify Page title
    And   enter valid or invalid username "<username>" and password "<password>"
    And   click "LOGIN" Button
    Then  verify the Login homepage

    Examples: 
      | username                 | password    |
      | info.praveen77@gmail.com | *********** |
      | info.praveen77@gmail.com | *********** |
      | info.praveen77@gmail.com | *********** |
