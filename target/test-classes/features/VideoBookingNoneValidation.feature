@tag
Feature: Validating the swagger API for Video Booking-Vendor None
  I want to use this template for my feature file
  
  @CreateEncoder
  Scenario: Verify whether Video Encoder is being successfully created
    Given Create VideoEncoder with "<Parent>", "<DisplayName>", "<EncoderIdentifier>", "<Vendor>"
    When User calls "AddEncoderAPI" with "POST" API request
    And Extract the encoder name
    Then the API calls got success with 200
    Then "state" in response body as "STATE_ACTIVE"
    And verify name created maps to "name" using "GetEncoderAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    And User calls "DisableEncoderAPI" with "POST" API request
    And User calls "ReenableEncoderAPI" with "POST" API request
    And User calls "UpdateEncoderAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    #And User calls "ListEncoderAPI" with "POST" API request
    #And User calls "DeleteEncoderAPI" with "DELETE" API request
    #And the API calls got success with 200
    #Then "state" in response body as "STATE_ACTIVE"
    
    Examples:
  | Parent                       | DisplayName | EncoderIdentifier | Vendor |
  | org/test/orgUnits/test1| Test     | EncoderID       | LTN    |
  
  
  @VideoDecoderNone
  Scenario: Verify whether Video Decoder NoneAPI is working properly
    Given Create DolbyVideoDecoder with "<Parent>", "<DisplayName>", "<Vendor>", "<DecoderIdentifier>", "<SupportedChannelVendor>"
    When User calls "AddDecoderAPI" with "POST" API request
    And Extract the decoder name
    Then "state" in response body as "STATE_IDLE"
    And verify name created maps to "name" using "GetDecoderAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_IDLE"
    And User calls "DisableDecoderAPI" with "POST" API request
    And User calls "ReenableDecoderAPI" with "POST" API request
    And User calls "UpdateDecoderAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_IDLE"
    #And User calls "ListDecoderAPI" with "POST" API request
   
    
    Examples:
    | Parent                       | DisplayName | Vendor | DecoderIdentifier | SupportedChannelVendor |
    | org/test/orgUnits/test1| DolbyDecoder | VENDOR_LTN    | DolbyID           | VENDOR_NONE    |
   
    
    
    
  @VideoSource  
  Scenario: Verify whether Video Source API is working properly
    Given Create VideoSource with "<Parent>", "<DisplayName>", "<AngleName>", "<ForeignIDType>", "<ForeignIDValue>", "<AutomatedStartMargin>", "<AutomatedEndMargin>", <AutomateBooking>
    When User calls "AddSourceAPI" with "POST" API request
    And Extract the source name
    Then "state" in response body as "STATE_ACTIVE"
    And verify name created maps to "name" using "GetSourceAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    And User calls "DisableSourceAPI" with "POST" API request
    And User calls "ReenableSourceAPI" with "POST" API request
    And User calls "UpdateSourceAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    #And User calls "ListSourceAPI" with "POST" API request
    
  Examples:
    | Parent                       | DisplayName| AngleName 																| ForeignIDType | ForeignIDValue | AutomatedStartMargin | AutomatedEndMargin | AutomateBooking |
    | org/test/orgUnits/test1| Source     |platforms/video/cameraAngles/jockey_cam_10 | CHRIMSEventCode| test 					| 10s      							| 10s 							| true					 |
    
    
   @VideoChannel-None
  Scenario: Verify whether Video Channel None API is working properly
    Given Create NoneChannel with "<Parent>", "<DisplayName>", "<Vendor>",  "<AutoAngle>","<decoder>", "<description>",  "<ForeignIDType>", "<ForeignIDValue>"
    When User calls "AddChannelAPI" with "POST" API request
    And Extract the channel name
    Then "state" in response body as "STATE_ACTIVE"
    And verify name created maps to "name" using "GetChannelAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    And User calls "DisableChannelAPI" with "POST" API request
    And User calls "ReenableChannelAPI" with "POST" API request
    And User calls "UpdateNoneChannelAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    And User calls "ListChannelAPI" with "POST" API request
    #When User calls "DeleteChannelAPI" with "DELETE" API request
    #Then the API calls got success with 200
    #Then "state" in response body as "STATE_ACTIVE"

 Examples:
    | Parent                       | DisplayName | Vendor				|  AutoAngle 															| decoder																											|description 		|  ForeignIDType | ForeignIDValue |
    | org/test/orgUnits/test1| NoneChannel | VENDOR_NONE  | platforms/video/cameraAngles/backside |org/TEST12/videoDecoders/01906dea-09f0-71b9-9c52-8f156d021aa7 |test description|  CHRIMSEventCode	| 123			 |
     
   
  @VideoBookings-None
  Scenario: Verify whether Video Booking None API is working properly
   Given Create PhenixBooking with "<Parent>", "<SourceName>", "<phenixChannelName>",  "<StartTime>", "<EndTime>", "<ForeignIDType>", "<ForeignIDValue>"
    When User calls "AddBookingAPI" with "POST" API request
    And Extract the phenix booking name 
    Then "state" in response body as "STATE_PENDING"
    And verify name created maps to "name" using "GetBookingAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_PENDING"
    And User calls "DisableBookingAPI" with "POST" API request
    And User calls "ReenableBookingAPI" with "POST" API request
    And User calls "StartBookingAPI" with "POST" API request
    And User calls "StopBookingAPI" with "POST" API request
    And User calls "ListBookingAPI" with "POST" API request
    When User calls "DeleteBookingAPI" with "DELETE" API request
    Then the API calls got success with 200
    Then "state" in response body as "STATE_PENDING"
   Examples:
    | Parent                       |   ForeignIDType   | ForeignIDValue |
    | org/test/orgUnits/test1| CHRIMSEventCode | 123            |
   
   