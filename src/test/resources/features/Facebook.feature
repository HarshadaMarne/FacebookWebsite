Feature: User should able to search and select a desired person and people should be displayed based on the search text.

  Background: User is logged in to facebook website
    Given user open facebook login page
    When the user enter login credentials
    Then the user logged in to facebook website successfully


    Scenario: Search bar should be display and visible to user
      Given the user is on home page
      When user navigate to search bar
      Then the search bar should be displayed to user


    Scenario: Search input should check spell of text entered by user
      Given the user move to search bar
      When user enter a text in search field
      Then spelling of text should be checked


    Scenario: Recent searches should be displayed to user while entering text to search bar
      Given the user navigate to home page
      And move to search bar
      When user click to search bar
      Then recent searches should be displayed to user

    Scenario: User should able to enter a text and search for a person
      Given the user click to search bar
      When user enter a text and press ENTER key
      Then search results should be displayed

    Scenario: Search results should be displayed relevant to search text to user
      Given the user move and click to search bar
      When user enter and search for a person
      Then relevant search results should be displayed


    Scenario: Filters option should be displayed to user
      Given the user enter and search for person
      When search result page displayed to user
      Then filters options should be displayed


    Scenario: Results should be displayed according to selected filters
      Given user enter and search for person
      When user click to people filter
      Then search result page displayed according to people filter

    @smoke
    Scenario: Message button should be displayed to people who are friend of user
      Given user search for person
      When user navigates to search results
      Then message button should be displayed for people who are friend of user


    Scenario: Add friend button should be displayed to people who are not a friend of user
      Given user search for desire person
      When search results is displayed
      Then add friend button should be displayed for people who are not a friend of user

    @fast
    Scenario Outline: Profile page of selected person should be displayed to user
      Given user search for a desire person by "<SearchText>"
      When user click and select the a desire person name
      Then "<PersonName>" should be displayed on selected person's profile page
      Examples:
        |SearchText       | PersonName       |
        |omkar naik       | omkar naik       |
        |shweta kalshetty | shweta kalshetty |











