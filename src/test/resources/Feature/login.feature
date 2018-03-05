@login
Feature: Test Login Page with its functionality

  Scenario Outline: Open Login page and check login

    Given I am on Login page
    When I enter "<UserName>" and "<Password>"
    Then I should be able to Landing page of Mosaic

    Examples:
      | UserName                       | Password       |
      | amol.adkitte@lntinfotech.com   | SwamiOM$2018 |
