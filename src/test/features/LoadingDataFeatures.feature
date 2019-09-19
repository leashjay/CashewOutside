Feature: Loading Data Features
  Acceptance tests for manual loading of data and loading of xml data files

  Scenario: Loading a single ingredient into the system
    Given an Ingredient has a "code" "name" and a default type with quantity 1
    When the Ingredient is added
    Then the Ingredient is in the system


  Scenario: Loading an XML file and checking it conforms to the DTD
    Given a "filepath"
    When a user and ingredient "filepath"
    Then the inventory map is not Null