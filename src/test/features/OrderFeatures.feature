Feature: Placing an order features
  Acceptance tests for actions involved in placing orders

  Background:
    Given a "BusinessName" for operation
    And a cash float
    And an ingredients "inventory" to hold ingredients
    And a database of sales "orders" to hold all orders made

  Scenario: Order totals correctly
    Given an imaginary burger is added to an order
    When the order is created
    Then the correct order total is calculated as $18.70

  Scenario: Correct change can be given for an order of coq au van
    Given the customer places an order for coq au van
    When the customer pays $40.00 and is given correct change
    Then the correct change of $18.70 is calculated and the inventory stock levels are reduced accordingly

  Scenario: Correct change can be given for an baby face
    Given the customer places an order for baby face
    When the customer pays $40.00 for baby face
    Then the baby face sale amount is added to the float

  Scenario: Correct change can be given for an order of hot chocolate
    Given the customer places an order for hot chocolate
    When the customer pays $12.00 for hot chocolate
    Then the inventory stock levels are reduced accordingly

  Scenario: Sales update stock level and float
    Given an order is placed for 3 LemCans
    When $5 cash is handed over and a sale made
    Then the float shows the new correct total and the inventory stock levels are reduced accordingly

  Scenario: Cancelled orders only change float
    Given an order is cancelled by customer
    When the refund is made
    Then the cash float reflects the transaction and the inventory stock levels remains unchanged

  Scenario: A customer makes more than one order
    Given an order is made
    When  another order is made
    Then there are two orders queued

  Scenario: A menuitem cannot be added if it has no ingredients
    Given a menu item
    When there is no inventory
    Then the order cannot be added