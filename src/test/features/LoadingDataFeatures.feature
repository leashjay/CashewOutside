Feature: Loading Data Features
  Acceptance tests for manual loading of data and loading of xml data files

  Scenario: Manually Load an ingredient
    Given an Ingredient has a "code" "name" and a default type with quantity 1
    When the Ingredient is added
    Then the Ingredient is in the system

  Scenario: Manually Load a menu
    Given an Ingredient has a "code" "name" and a default type with quantity 1
    When the Ingredient is added
    Then the Ingredient is in the system

  Scenario: Load an XML Ingredient file
    Given a "filepath"
    When a user and ingredient "filepath"
    Then the inventory map is not Null

  Scenario: An XML with Errors is Handled
    Given a "filepath"
    When a user and ingredient "filepath"
    Then the inventory map is not Null