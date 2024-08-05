Feature: Get

  Scenario Outline: get bill
    Given get base URI "<uri>"
    When set header
    Then set parameter
    Then send request "<endpoint>"
    Then verify status code 200
    Examples:
      | uri                                | endpoint  |
      | https://testdemo-c6ux.onrender.com | /bill/{id} |
