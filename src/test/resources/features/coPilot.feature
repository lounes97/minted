@smoke @ui
Feature: User Authentication
  User Story:
  As a user, I should be able to login with correct credentials via Salesforce platform.

  Background: Login a user
    When user enters the username
    And user enters the password
    Then user clicks on login button
    And when the user clicks on the Kodif icon, the coPilot window pops up

  @max&min @ui
  Scenario: Maximize and minimize coPilot Window
    When the user selects the maximize option the coPilot window should expand
    And the user selects the minimize option the coPilot window should minimize to the taskbar


  @optionButton @ui
  Scenario: Searching in the coPilot Window
    When user clicks on command button he should see at least one elements

  @searchBar @ui
  Scenario: User clicks on command button and select one of the options
    When user search something on the searching bar

  @ticketShift @ui
  Scenario: User on the main page and shift the ticket
    When user clicks on different ticket the default message should appear on the coPilot window

  @insertSuggestion @ui
  Scenario: User clicks on Suggestion option to insert the suggestion to the Post section
    When user clicks on Suggestion option the insert button will appear under the suggestion text
    And user clicks on insert button which will copy and paste the text to the post section


  @insertDisposition @ui
  Scenario: User clicks on Disposition option to insert the suggestion to the Case Notes section
    When user clicks on Disposition option the insert button will appear under the disposition info
    And user clicks on insert button which will copy and paste the info to Case Notes section