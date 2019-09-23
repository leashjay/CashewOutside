Feature: Loading Data Features
  Acceptance tests for manual loading of data and loading of xml data files

  Background:
    Given an "inventory" to hold ingredients
    And an "menu" to collect menu items
    And an "contact list" to suppliers details

  Scenario: Manually Load an ingredient
    Given an Ingredient has a "code" "name" a "quantity" a "unit type" and costs $100.0
    When the Ingredient is added
    Then the Ingredient is in the program

  Scenario: Manually Load a menu
    Given an Menu has a "id", "name", "quantity", "Unit Type" costs $100.00 and has a list of ingredients:
      | code | name     | quantity | unitType | cost |
      | llc  | lettuce  | 12       | count    | 0.90 |
      | slt  | salt     | 3        | gram     | 0.10 |
      | jlp  | jalapeno | 6        | unknown  |      |

    When a menu is added
    Then the menu is in the program

  Scenario: Manually Load a supplier
    Given an supplier has a "sid", "name", "address", "phoneType", "phoneNumber", sometimes an "email" and/or a"url"
    When a supplier is added
    Then the supplier is in the program

  Scenario: Load an XML Ingredient file
    Given a "filepath" for an XML file containing ingredients
    When the "filepath" is used in the application
    Then the inventory map is not Null

  Scenario: Load an XML Menu Items file
    Given a "filepath" for an XML file containing menu items
    When the "filepath" is used in the application
    Then the menu items map is not Null

  Scenario: Load an XML Supplier file
    Given a "filepath" for an XML file containing suppliers
    When the "filepath" is used in the application
    Then the supplier map is not Null

  Scenario: An XML Ingredient file with Errors is Handled
    Given a "filepath" for an XML file containing ingredients
    When the "filepath" is used and an integrity error is found
    Then an error message is returned to the user

  Scenario: An XML Menu Items file with Errors is Handled
    Given a "filepath" for an XML file containing menu items
    When the "filepath" is used and an integrity error is found
    Then an error message is returned to the user

  Scenario: An XML Supplier file with Errors is Handled
    Given a "filepath" for an XML file containing suppliers
    When the "filepath" is used and an integrity error is found
    Then an error message is returned to the user