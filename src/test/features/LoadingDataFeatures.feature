Feature: Loading Data Features
  Acceptance tests for manual loading of data and loading of xml data files

  Background:
    Given a "BusinessName" for operation
    And an "inventory" to hold ingredients
    And an "menu" to collect menu items
    And an "contact list" to suppliers details

  Scenario: Manually Load an ingredient
    Given an Ingredient is known
    When the Ingredient is added
    Then the Ingredient is in the program

  Scenario: Manually Load a menu item
    Given a Menu item is known
    When a menu item is added
    Then the menu item is in the program

  Scenario: Manually Load a supplier
    Given an supplier is known
    When a supplier is added
    Then the supplier is in the program

  Scenario: Load an XML Ingredient file
    Given an ingredient "filepath" for an XML file containing ingredients
    When the ingredient "filepath" is loaded
    Then the inventory map is not Null

  Scenario: Load an XML Menu Items file
    Given a menu "filepath" for an XML file containing menu items
    When the menu "filepath" is loaded
    Then the menu items map is not empty

  Scenario: Load an XML Supplier file
    Given a supplier "filepath" for an XML file containing suppliers
    When the supplier "filepath" is loaded
    Then the supplier map is not empty

  Scenario: An XML Ingredient file with Errors is Handled
    Given a ingredient "filepath" for an XML file containing ingredients
    When the "filepath" is used and an integrity error is found
    Then an error message is returned to the user

  Scenario: An XML Menu Items file with Errors is Handled
    Given a menu item "filepath" for an XML file containing menu items
    When the "filepath" is used and an integrity error is found
    Then an error message is returned to the user

  Scenario: An XML Supplier file with Errors is Handled
    Given a supplier "filepath" for an XML file containing suppliers
    When the "filepath" is used and an integrity error is found
    Then an error message is returned to the user