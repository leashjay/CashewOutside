Feature: Placing an order features
  Acceptance tests for actions involved in placing orders

  Background:
    Given a "BusinessName" for operation
    And a cash float
    And an ingredients "inventory" to hold ingredients
    And a database of sales "orders" to hold all orders made

  Scenario: a customer places an order for multiple items
    Given menu items are selected in an order
    When the order is created
    Then the correct order total is calculated

  Scenario: A customer places and order and pays
    Given the customer places an order
    When the customer pays
    Then the correct change is calculated and the inventory stock levels are reduced

  Scenario: The business makes a sale
    Given an order is placed
    When cash is handed over and a sale made
    Then the float shows the correct total and the inventory stock levels are reduced

  Scenario: A customer cancels their order
    Given an order is cancelled
    When the refund is made
    Then the cash float reflects the transaction and the inventory stock levels remains unchanged