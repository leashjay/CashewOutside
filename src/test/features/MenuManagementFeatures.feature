Feature: Menu Management

  Background:
    Given a "BusinessName" for operation
    And a cash float
    And an ingredients "inventory" to hold ingredients
    And a database of sales "orders" to hold all orders made

  Scenario: Serving size can be calculated
    Given choc and regMilk in inventory
    When a menu item is created for chocolate milk
    Then correct serving size is calculated

  Scenario: When stock is low
    Given choc milk and no stock
    When an order is placed for chocolate milk
    Then the menu item serving count is 0

  Scenario: A customer has an intolerance
    Given When an order is made for a menu item
    When an intolerance for gluten is flagged
    Then an order exceptions are handled

  Scenario: A customer is a vegetarian
    Given When an order is made for a vegetarian item
    When when they order a vegetarian meal
    Then a search of all ingredients show only yes on vege flags