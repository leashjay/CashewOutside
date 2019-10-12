Feature: Supplier management

  Background:
    Given a "BusinessName" for operation
    And a cash float
    And an ingredients "inventory" to hold ingredients
    And a database of sales "orders" to hold all orders made

    Given a supplier is known
    When the supplier is added to the contacts list
    Then supp