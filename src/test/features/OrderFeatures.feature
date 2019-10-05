Feature: Placing an order features
  Acceptance tests for actions involved in placing orders

  Background:
    Given a "BusinessName" for operation
    And a cash float
    And an ingredients "inventory" to hold ingredients
    And a database of sales "orders" to hold all orders made

  Scenario: Order totals correctly
    Given menu items are selected in an order
    When the order is created
    Then the correct order total is calculated

  Scenario: Correct change can be given
    Given the customer places an order
    When the customer pays
    Then the correct change is calculated and the inventory stock levels are reduced

  Scenario: Sales update stock level and float
    Given an order is placed
    When cash is handed over and a sale made
    Then the float shows the correct total and the inventory stock levels are reduced

  Scenario: Cancelled orders only change float
    Given an order is cancelled
    When the refund is made
    Then the cash float reflects the transaction and the inventory stock levels remains unchanged