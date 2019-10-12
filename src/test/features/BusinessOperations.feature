Feature: Business Operations

  Background:
    Given a "BusinessName" for operation
    And a cash float
    And an ingredients "inventory" to hold ingredients
    And a database of sales "orders" to hold all orders made


  Scenario: The business starts the day with a cash float
    Given The business adds $200.00 to the starting float
    When a order takes place
    Then the cash floats reflects the correct total

  Scenario: The business ends the day and closes the float
    Given The business opens the day with a $1000.00 float
    When several orders are taken during the day
    Then the business closes the float and a earnings value can be calculated

  Scenario: The business wants to change the price of a hot chocolate
    Given The business has a hot chocolate on the menu
    When the price gets amended to $5.00
    Then the new cost of a hot chocolate is $5.00