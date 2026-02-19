Feature: Open Library Author API

  Scenario: Verify author personal name and alternate names
    Given I call the Open Library author API
    Then the response status should be 200
    And the personal_name should be "Sachi Rautroy"
    And the alternate_names should include "Yugashrashta Sachi Routray"