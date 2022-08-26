#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Lead
Feature: Managing Lead

	Background:
	Given I login into application using 'Chrome'

  @CreateLead
  Scenario: Creation of Lead
    When I go to 'create' lead page
    And I create following lead
    |Ashish|Thakur|123455|
    Then Lead should be created

  @DeleteLead
		Scenario: Deletion of Lead  
      When I go to 'view' lead page
      Then Lead 'Dora Nelms' should be present
  		When I delete the lead
  		Then Lead should be deleted successfully
  		