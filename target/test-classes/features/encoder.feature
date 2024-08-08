@tag
Feature: Validating the swagger API for Video Booking-Vendor Phenix
  I want to use this template for my feature file
  
  @CreateEncoder
  Scenario: Verify whether Video Encoder is being successfully created
    Given Create VideoEncoder with "<Parent>", "<DisplayName>", "<EncoderIdentifier>", "<Vendor>"
    When User calls "AddEncoderAPI" with "POST" API request
    And Extract the encoder name
    Then the API calls got success with 200
    Then "state" in response body as "STATE_ACTIVE"
    And verify name created maps to "name" using "GetEncoderAPI"
    #And the API calls got success with 200
    #And "state" in response body as "STATE_ACTIVE"
    And User calls "DisableEncoderAPI" with "POST" API request
    And User calls "ReenableEncoderAPI" with "POST" API request
    And User calls "UpdateEncoderAPI" with "PATCH" API request
    #And the API calls got success with 200
    #And "state" in response body as "STATE_ACTIVE"
    #And User calls "ListEncoderAPI" with "POST" API request
    #And User calls "DeleteEncoderAPI" with "DELETE" API request
   
    
    Examples:
  | Parent                       | DisplayName | EncoderIdentifier | Vendor |
  | org/Test_Org/orgUnits/Unit-01| Test     | EncoderID       | LTN    |
  