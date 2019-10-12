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
    Then check menu item attribute by filter

  Scenario: A customer is a vegetarian
    Given When an order is made for a vegetarian item
    When when they order a vegetarian meal
    Then a search of all ingredients show only yes on vege flags

  Scenario: A menu item is no longer required
    Given a menu item
    When the user wants to delete it
    Then the menu item can be deleted and is no longer present AND exceptions are handled

  Scenario: Ingredients can be added to a recipe
    Given a recipe
    When an ingredient is added
    Then the ingredient is found in the recipe

  Scenario: A new menu is added
    Given A business
    When a new menu item is added
    Then it is available in the application

  Scenario:
    Given
    When
    Then