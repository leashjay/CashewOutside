Feature: Menu Management

  Scenario: Serving size can be calculated
    Given ingredients in inventory
    When a menu item is created
    Then correct serving size is calculated

  Scenario: When stock is low
    Given a menu item
    When there is no stock and a menu item is added to an order
    Then the menu item shows no servings and the menu is not added to the order

