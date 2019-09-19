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
    Given an Menu has a "id" "name" a "quantity"  a "Unit Type" costs $100.00 and has a list of ingredients:
      | code | name     | quantity | unitType | cost |
      | llc  | lettuce  | 12       | count    | 0.90 |
      | slt  | salt     | 3        | gram     | 0.10 |
      | jlp  | jalapeno | 6        | unknown  |      |

    When a menu is added
    Then the menu is in the program

  Scenario: Manually Load a supplier
    Given an Menu has a "id" "name" a list of ingredients and a menu type
    When a menu is added
    Then the menu is in the program

  Scenario: Load an XML Ingredient file
    Given a "filepath"
    When a user and ingredient "filepath"
    Then the inventory map is not Null

  Scenario: An XML with Errors is Handled
    Given a "filepath"
    When a user and ingredient "filepath"
    Then the inventory map is not Null