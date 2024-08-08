@tag
Feature: Validating the swagger API for Video Booking-Vendor Dolby
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
  | org/Test_Org/orgUnits/Unit-01| Test     | EncoderID       | LTN    |
  
  
  @VideoDecoderDolby
  Scenario: Verify whether Video Decoder Dolby API is working properly
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
    And User calls "ListDecoderAPI" with "POST" API request
    When User calls "DeleteDecoderAPI" with "DELETE" API request
    Then the API calls got success with 200
    Then "state" in response body as "STATE_IDLE"
    
   Examples:    
    | Parent                       | DisplayName | Vendor | DecoderIdentifier | SupportedChannelVendor |
    | org/Test_Org/orgUnits/Unit-01| DolbyDecoder  | VENDOR_LTN    | NoneID           | VENDOR_DOLBY    |
    
    
    
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
    And User calls "ListSourceAPI" with "POST" API request
    
  Examples:
    | Parent                       | DisplayName| AngleName 																| ForeignIDType | ForeignIDValue | AutomatedStartMargin | AutomatedEndMargin | AutomateBooking |
    | org/Test_Org/orgUnits/Unit-01| Source     |platforms/video/cameraAngles/jockey_cam_10 | CHRIMSEventCode| test 					| 10s      							| 10s 							| true					 |
    
    
  @VideoChannel-Dolby
Scenario: Verify whether Video Channel Dolby API is working properly
    Given Create DolbyChannel with "<Parent>", "<DisplayName>", "<Vendor>", "<AutoAngle>", "<connectorID>", "<clusterName>", "<height>", "<frameRate>", "<description>", "<ForeignIDType>", "<ForeignIDValue>", "<createReplay>"
    When User calls "AddChannelAPI" with "POST" API request
    And Extract the channel name
    Then "state" in response body as "STATE_ACTIVE"
    And verify name created maps to "name" using "GetChannelAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    And User calls "DisableChannelAPI" with "POST" API request
    And User calls "ReenableChannelAPI" with "POST" API request
    And User calls "UpdateDolbyChannelAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    #And User calls "ListChannelAPI" with "POST" API request
    #When User calls "DeleteChannelAPI" with "DELETE" API request
    #Then the API calls got success with 200
    #Then "state" in response body as "STATE_ACTIVE"

Examples:
    | Parent                       | DisplayName | Vendor        | AutoAngle                          | connectorID | clusterName                                                      | height | frameRate | description      | ForeignIDType  | ForeignIDValue | createReplay |
    | org/Test_Org/orgUnits/Unit-01| DolbyChannel  | VENDOR_DOLBY  | platforms/video/cameraAngles/backside | 74          | platforms/video/videoChannelClusters/018e5dcb-7d64-7578-b9b7-2f1a324894de | _1080p | _50f      | test description | CHRIMSEventCode | 123            | true         |


   
   @VideoBookings-Dolby
  Scenario: Verify whether Video Booking API is working properly
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
    | org/Test_Org/orgUnits/Unit-01| CHRIMSEventCode | 123            |
   
   