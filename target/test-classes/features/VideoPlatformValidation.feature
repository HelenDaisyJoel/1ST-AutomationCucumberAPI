@tag
Feature: Validating the swagger API
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
  
  
  @VideoDecoder-Phenix
  Scenario: Verify whether Video Decoder PhenixAPI is working properly
    Given Create VideoDecoder with "<Parent>", "<DisplayName>", "<Vendor>", "<DecoderIdentifier>", "<SupportedChannelVendor>", "<VendorChannelIdentifier>", "<ProjectID>", "<Zone>", "<InstanceName>"
    When User calls "AddDecoderAPI" with "POST" API request
    Then "state" in response body as "STATE_IDLE"
    And Extract the decoder name
    And verify name created maps to "name" using "GetDecoderAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_IDLE"
    And User calls "DisableDecoderAPI" with "POST" API request
    And User calls "ReenableDecoderAPI" with "POST" API request
    And User calls "UpdateDecoderAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_IDLE"
    #And User calls "ListDecoderAPI" with "POST" API request
    When User calls "DeleteDecoderAPI" with "DELETE" API request
    Then the API calls got success with 200
    Then "state" in response body as "STATE_IDLE"

  Examples:
    | Parent                       | DisplayName | Vendor | DecoderIdentifier | SupportedChannelVendor | VendorChannelIdentifier | ProjectID | Zone | InstanceName |
    | org/Test_Org/orgUnits/Unit-01| Test-01     | LTN    | Test-01           | PHENIX                 | test                    | test      | test | InstanceName |
    
  
    
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
    | org/Test_Org/orgUnits/Unit-01| Source     |platforms/video/cameraAngles/jockey_cam_10 | CHRIMSEventCode| test 					| 10s      							| 10s 							| true					 |
    
	@VideoChannel-Phenix
  Scenario: Verify whether Video Channel Phenix API is working properly
    Given Create PhenixChannel with "<Parent>", "<DisplayName>", "<Vendor>", "<vendorChannelId>", "<AutoAngle>", "<description>", "<createReplay>", "<ForeignIDType>", "<ForeignIDValue>"
    When User calls "AddChannelAPI" with "POST" API request
    And Extract the channel name
    Then "state" in response body as "STATE_ACTIVE"
    And verify name created maps to "name" using "GetChannelAPI"
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    And User calls "DisableChannelAPI" with "POST" API request
    And User calls "ReenableChannelAPI" with "POST" API request
    And User calls "UpdatePhenixChannelAPI" with "PATCH" API request
    And the API calls got success with 200
    And "state" in response body as "STATE_ACTIVE"
    #And User calls "ListChannelAPI" with "POST" API request
    #When User calls "DeleteChannelAPI" with "DELETE" API request
    #Then the API calls got success with 200
    #Then "state" in response body as "STATE_ACTIVE"

 Examples:
    | Parent                       | DisplayName | Vendor				| vendorChannelId 		| AutoAngle 															| description 		| createReplay | ForeignIDType | ForeignIDValue |
    | org/Test_Org/orgUnits/Unit-01| PhenixChannel | VENDOR_PHENIX    |vendor-Phenix134|    platforms/video/cameraAngles/backside | test description| true				| CHRIMSEventCode	| 123			 |
    
   
   
   @VideoBookings-Phenix
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
    #And User calls "ListBookingAPI" with "POST" API request
    When User calls "DeleteBookingAPI" with "DELETE" API request
    Then the API calls got success with 200
    Then "state" in response body as "STATE_PENDING"
   Examples:
    | Parent                       |   ForeignIDType   | ForeignIDValue |
    | org/Test_Org/orgUnits/Unit-01| CHRIMSEventCode | 123            |
   
     
    
  @VideoDecoderDolby
  Scenario: Verify whether Video Decoder DolbyAndNoneAPI is working properly
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
    #When User calls "DeleteDecoderAPI" with "DELETE" API request
    #Then the API calls got success with 200
    #Then "state" in response body as "STATE_IDLE"
    
    Examples:
    | Parent                       | DisplayName | Vendor | DecoderIdentifier | SupportedChannelVendor |
    #| org/Test_Org/orgUnits/Unit-01| DolbyDecoder | VENDOR_LTN    | DolbyID           | VENDOR_DOLBY    |
    | org/Test_Org/orgUnits/Unit-01| NoneDecoder  | VENDOR_LTN    | NoneID           | VENDOR_NONE    |
    
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
    #And User calls "ListChannelAPI" with "POST" API request
    #When User calls "DeleteChannelAPI" with "DELETE" API request
    #Then the API calls got success with 200
    #Then "state" in response body as "STATE_ACTIVE"

 Examples:
    | Parent                       | DisplayName | Vendor				|  AutoAngle 															| decoder																											|description 		|  ForeignIDType | ForeignIDValue |
    | org/Test_Org/orgUnits/Unit-01| NoneChannel | VENDOR_NONE  | platforms/video/cameraAngles/backside |org/TEST12/videoDecoders/01906de8-6aaa-769d-840d-aa8f85ecdbb1 |test description|  CHRIMSEventCode	| 123			 |
     

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

   
    
    @VideoBooking-Phenix
Scenario: Verify whether Video Booking API is working properly
    Given Create VideoEncoder with "<Parent>", "<EncoderDisplayName>", "<EncoderIdentifier>", "<Vendor>"
    When User calls "AddEncoderAPI" with "POST" API request
    And Extract the encoder name
    Given Create VideoDecoder with "<Parent>", "<DecoderDisplayName>", "<Vendor>", "<DecoderIdentifier>", "<SupportedChannelVendor>", "<VendorChannelIdentifier>", "<ProjectID>", "<Zone>", "<InstanceName>"
    When User calls "AddDecoderAPI" with "POST" API request
    And Extract the decoder name
    
    Given Create VideoSource1 with "<Parent>", "<SourceDisplayName>", "<EncoderName>", "<AngleName>", "<ForeignIDType>", "<ForeignIDValue>", "<AutomatedStartMargin>", "<AutomatedEndMargin>", <AutomateBooking>

    When User calls "AddSourceAPI" with "POST" API request
    And Extract the source name    
    Given Create PhenixChannel with "<Parent>", "<ChannelDisplayName>", "<ChannelVendor>", "<vendorChannelId>", "<AutoAngle>", "<description>", "<createReplay>", "<ForeignIDType>", "<ForeignIDValue>"
    When User calls "AddChannelAPI" with "POST" API request
    And Extract the channel name 
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
    #And User calls "ListBookingAPI" with "POST" API request
    When User calls "DeleteBookingAPI" with "DELETE" API request
    Then the API calls got success with 200
    Then "state" in response body as "STATE_PENDING"

Examples:
    | Parent                       | EncoderDisplayName | EncoderIdentifier | Vendor     | DecoderDisplayName | DecoderIdentifier | SupportedChannelVendor | VendorChannelIdentifier | ProjectID | Zone    | InstanceName | SourceDisplayName | AngleName 															| ForeignIDType    | ForeignIDValue | AutomatedStartMargin | AutomatedEndMargin | AutomateBooking | ChannelDisplayName | ChannelVendor| vendorChannelId   | AutoAngle                   					| description         | createReplay | ForeignIDType   | ForeignIDValue |
    | org/Test_Org/orgUnits/Unit-01| Encoder1           | EncoderID1        | VENDOR_LTN | Decoder1           | DecoderID1        | VENDOR_PHENIX          | VendorChannelID1       | Project1  | Zone1   | Instance1    | Source1           | platforms/video/cameraAngles/pan    | CHRIMSEventCode  | Value1         | 5s                   | 5s                 | true            | Channel1           | VENDOR_PHENIX  | VendorChannelId  | platforms/video/cameraAngles/backside  | test description    | true         | CHRIMSEventCode | 123            |
    #| org/Test_Org/orgUnits/Unit-01| Encoder2           | EncoderID2        | VENDOR_LTN | Decoder2           | DecoderID2        | VENDOR_PHENIX          | VendorChannelID2       | Project2  | Zone2   | Instance2    | Source2           | platforms/video/cameraAngles/jockey_cam_2    | CHRIMSEventCode  | CHRIMSEventCode| 10s                  | 10s                | false           | Channel2           | VENDOR_DOLBY  |VendorChannelId-01 | platforms/video/cameraAngles/headon   | another description | false        | CHRIMSEventCode | 124            |

    
    
    
    @CreateEncoder
  Scenario: Verify whether Video Encoder is being successfully created
    Given Create VideoEncoder with "<Parent>", "<DisplayName>", "<EncoderIdentifier>", "<Vendor>"
    When User calls "AddEncoderAPI" with "POST" API request
    #And extract encoder name from encoder API response and save it in scenarioContext with key name
    And Extract the encoder name
    Then the API calls got success with 200
    Then "state" in response body as "STATE_ACTIVE"
    
    
  Examples:
  | Parent                       | DisplayName | EncoderIdentifier | Vendor |
  | org/Test_Org/orgUnits/Unit-01| Test     | EncoderID       | LTN    |
  
  
  @VideoDecoder-Phenix
  Scenario: Verify whether Video Decoder PhenixAPI is working properly
    Given Create VideoDecoder with "<Parent>", "<DisplayName>", "<Vendor>", "<DecoderIdentifier>", "<SupportedChannelVendor>", "<VendorChannelIdentifier>", "<ProjectID>", "<Zone>", "<InstanceName>"
    When User calls "AddDecoderAPI" with "POST" API request
    Then "state" in response body as "STATE_IDLE"
    And Extract the decoder name
   Examples:
    | Parent                       | DisplayName | Vendor | DecoderIdentifier | SupportedChannelVendor | VendorChannelIdentifier | ProjectID | Zone | InstanceName |
    | org/Test_Org/orgUnits/Unit-01| Test-01     | LTN    | Test-01           | PHENIX                 | test                    | test      | test | InstanceName |
      
    
  @VideoSource  
  Scenario: Verify whether Video Source API is working properly
    Given Create VideoSource with "<Parent>", "<DisplayName>", "<AngleName>", "<ForeignIDType>", "<ForeignIDValue>", "<AutomatedStartMargin>", "<AutomatedEndMargin>", <AutomateBooking>
    When User calls "AddSourceAPI" with "POST" API request
    And Extract the source name
    Then "state" in response body as "STATE_ACTIVE"
    
  Examples:
    | Parent                       | DisplayName| AngleName 																| ForeignIDType | ForeignIDValue | AutomatedStartMargin | AutomatedEndMargin | AutomateBooking |
    | org/Test_Org/orgUnits/Unit-01| Source     |platforms/video/cameraAngles/jockey_cam_10 | CHRIMSEventCode| test 					| 10s      							| 10s 							| true					 |
    
	@VideoChannel-Phenix
  Scenario: Verify whether Video Channel Phenix API is working properly
    Given Create PhenixChannel with "<Parent>", "<DisplayName>", "<Vendor>", "<vendorChannelId>", "<AutoAngle>", "<description>", "<createReplay>", "<ForeignIDType>", "<ForeignIDValue>"
    When User calls "AddChannelAPI" with "POST" API request
    And Extract the channel name
    Then "state" in response body as "STATE_ACTIVE"
 Examples:
    | Parent                       | DisplayName | Vendor				| vendorChannelId 		| AutoAngle 															| description 		| createReplay | ForeignIDType | ForeignIDValue |
    | org/Test_Org/orgUnits/Unit-01| PhenixChannel | VENDOR_PHENIX    |vendor-Phenix134|    platforms/video/cameraAngles/backside | test description| true				| CHRIMSEventCode	| 123			 |
       
  @VideoBookings-Phenix
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
    #And User calls "ListBookingAPI" with "POST" API request
    When User calls "DeleteBookingAPI" with "DELETE" API request
    Then the API calls got success with 200
    Then "state" in response body as "STATE_PENDING"
   Examples:
    | Parent                       |   ForeignIDType   | ForeignIDValue |
    | org/Test_Org/orgUnits/Unit-01| CHRIMSEventCode | 123            |
    
    