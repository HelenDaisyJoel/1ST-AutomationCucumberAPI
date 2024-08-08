package stepDefinition;

import static io.restassured.RestAssured.given;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CreateChannel;
import pojo.CreateSource;
import resources.APIResources;
import resources.ScenarioContext;
import resources.Utils;
import resources.VideoBooking;
import resources.VideoChannel;
import resources.VideoDecoder;
import resources.VideoEncoder;
import resources.VideoSource;

public class stepDefinition extends Utils {
	
    RequestSpecification res, request;
    ResponseSpecification resspec;
    Response response;
    
    String name, EncoderName;
    String updateURL,requestBody;
    String token;
    VideoEncoder encoder = new VideoEncoder();
    VideoDecoder decoder = new VideoDecoder();
    VideoSource source	=	new VideoSource();
    VideoChannel channel=new VideoChannel();
    VideoBooking booking=new VideoBooking();
    
    ScenarioContext scenarioContext=new ScenarioContext();

    public stepDefinition() throws IOException {
        token = getGlobalValue("token");
    }
    
    @Given("Create VideoEncoder with {string}, {string}, {string}, {string}")
    public void create_video_encoder_with(String Parent, String DisplayName, String EncoderIdentifier, String Vendor) throws IOException {
    	String randomDisplayName = DisplayName + "-"+UUID.randomUUID().toString().substring(0, 4);
    	String randormEncoderID=EncoderIdentifier+"-"+UUID.randomUUID().toString().substring(0, 4);
    	res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(encoder.createEncoderPayload(Parent, randomDisplayName, randormEncoderID, Vendor));
//    	Response encoderName = res.path("name"); // Adjust the path as per your response structure
//        ScenarioContext.setContext("EncoderName", encoderName);
    	
    
    }

    @Given("Create VideoDecoder with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void create_video_decoder_with(String Parent, String DisplayName, String Vendor, String DecoderIdentifier, String SupportedChannelVendor, String VendorChannelIdentifier, String ProjectID, String Zone, String InstanceName) throws IOException {
    	String randomDisplayName = DisplayName + "-"+UUID.randomUUID().toString().substring(0, 4);
    	String randormDecoderID=DecoderIdentifier+"-"+UUID.randomUUID().toString().substring(0, 4);
    	String randormVendorChannelIdentifier=VendorChannelIdentifier+"-"+UUID.randomUUID().toString().substring(0, 4);
       	
    	res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(decoder.createDecoderPayload(Parent, randomDisplayName, Vendor, randormDecoderID, SupportedChannelVendor, randormVendorChannelIdentifier, ProjectID, Zone, InstanceName));
    
    }
    

    @Given("Create DolbyVideoDecoder with {string}, {string}, {string}, {string}, {string}")
    public void create_dolby_video_decoder_with(String Parent, String DisplayName, String Vendor, String DecoderIdentifier, String SupportedChannelVendor) throws IOException {
    	String randomDisplayName = DisplayName + "-"+UUID.randomUUID().toString().substring(0, 4);
    	String randormDecoderID=DecoderIdentifier+"-"+UUID.randomUUID().toString().substring(0, 4);
    	res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(decoder.createDolbyDecoderPayload(Parent,randomDisplayName,Vendor,randormDecoderID,SupportedChannelVendor));
    }
    
    @Given("Create PhenixChannel with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void create_phenix_channel_with(String Parent, String DisplayName, String Vendor, String vendorChannelId, String AutoAngle, String description, String createReplayString, String ForeignIDType, String ForeignIDValue) throws IOException {
    	boolean createReplay = Boolean.parseBoolean(createReplayString);
    	String randormDisplayName=DisplayName+"-"+UUID.randomUUID().toString().substring(0, 4);
    	String randormvendorChannelId=vendorChannelId+"-"+UUID.randomUUID().toString().substring(0, 4);
       	VideoChannel channel = new VideoChannel();
        
        // Create Phenix Channel
        CreateChannel phenixChannel = channel.CreatePhenixChannel(Parent, randormDisplayName, Vendor, randormvendorChannelId, AutoAngle, description, createReplay, ForeignIDType, ForeignIDValue);
        
    	res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(phenixChannel);
    	System.out.println("Parent"+Parent);
    }
    
    
    
    @Given("Create NoneChannel with {string}, {string}, {string},  {string},{string}, {string},  {string}, {string}")
    public void create_none_channel_with(String Parent, String DisplayName, String Vendor, String AutoAngle, String decoder, String description, String ForeignIDType, String ForeignIDValue) throws IOException {
    	String randormDisplayName=DisplayName+"-"+UUID.randomUUID().toString().substring(0, 4);
    	String dynamicDecoderName = scenarioContext.getContext("DecoderName").toString();
    	VideoChannel channel = new VideoChannel();
        // Create Phenix Channel
        CreateChannel phenixChannel = channel.CreateNoneChannel(Parent, randormDisplayName, Vendor, AutoAngle, dynamicDecoderName, description, ForeignIDType, ForeignIDValue);
        
        
    	res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(phenixChannel);
    	System.out.println("Parent"+Parent);
    }
    

    @Given("Create DolbyChannel with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void create_dolby_channel_with(String Parent, String DisplayName, String Vendor, String AutoAngle, String connectorID, String clusterName, String height, String frameRate, String description, String ForeignIDType, String ForeignIDValue, String createReplay) throws IOException {
        // Convert createReplay from String to boolean
        boolean createReplayBool = Boolean.parseBoolean(createReplay);
        String randormDisplayName=DisplayName+"-"+UUID.randomUUID().toString().substring(0, 4);

        VideoChannel channel = new VideoChannel();
            
        // Create Phenix Channel
        CreateChannel dolbyChannel = channel.CreateDolbyChannel(Parent, randormDisplayName, Vendor,
                AutoAngle, connectorID, clusterName, height, frameRate, 
                description, ForeignIDType, ForeignIDValue, createReplayBool);
            
        res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(dolbyChannel);
        System.out.println("Parent: " + Parent);
    }

    @Given("Create VideoSource1 with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {String}")
    public void create_video_source1_with(String Parent, String DisplayName, String EncoderName, String AngleName, String ForeignIDType, String ForeignIDValue, String AutomatedStartMargin, String AutomatedEndMargin, String AutomateBooking) throws IOException {
        // Generate a random display name if needed
        String randomDisplayName = DisplayName + "-" + UUID.randomUUID().toString().substring(0, 4);

        // Retrieve encoder name from scenario context
        String dynamicEncoderName = scenarioContext.getContext("EncoderName").toString();

        // Proceed with creating the video source using dynamicEncoderName
        res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(source.createSourcePayload(Parent, randomDisplayName, dynamicEncoderName, AngleName, ForeignIDType, ForeignIDValue, AutomatedStartMargin, AutomatedEndMargin, AutomateBooking));
    }


    
    @Given("Create VideoSource with {string}, {string}, {string}, {string}, {string}, {string}, {string}, {booleanType}")
    public void create_video_source_with(String Parent, String DisplayName, String AngleName, String ForeignIDType, String ForeignIDValue, String AutomatedStartMargin, String AutomatedEndMargin, boolean AutomateBooking) throws IOException {
        // Generate a random display name if needed
        String randomDisplayName = DisplayName + "-" + UUID.randomUUID().toString().substring(0, 4);

        // Retrieve encoder name from scenario context
        String dynamicEncoderName = scenarioContext.getContext("EncoderName").toString();

        // Proceed with creating the video source using dynamicEncoderName
        res = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(source.createSourcePayload(Parent, randomDisplayName, dynamicEncoderName, AngleName, ForeignIDType, ForeignIDValue, AutomatedStartMargin, AutomatedEndMargin, AutomateBooking));
    }

    
    @Given("Create PhenixBooking with {string}, {string}, {string},  {string}, {string}, {string}, {string}")
    public void create_phenix_booking_with(String Parent, String SourceName, String ChannelName, String StartTime, String EndTime, String ForeignIDType, String ForeignIDValue) throws IOException {
    	// Get current time in UTC
        ZonedDateTime startDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusHours(1);
        // Add one hour to the current time for the end time
        ZonedDateTime endDateTime = startDateTime.plusHours(1);
//       Format these times as ISO 8601 strings
        String formattedStartDateTime = startDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        String formattedEndDateTime = endDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println("Start Time: " + formattedStartDateTime);
        System.out.println("End Time: " + formattedEndDateTime);
        
        String dynamicSourceName = scenarioContext.getContext("SourceName").toString();
        String dynamicChannelName = scenarioContext.getContext("phenixChannelName").toString();
        res=given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token)
                .body(booking.createBooking(Parent, dynamicSourceName, dynamicChannelName, formattedStartDateTime, formattedEndDateTime, ForeignIDType, ForeignIDValue));
    }


    @When("User calls {string} with {string} API request")
    public void user_calls_with_api_request(String resource, String method) throws IOException {
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println("Calling API: " + resourceAPI.getResource());
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        switch (method.toUpperCase()) {
            case "GET":
                response = given().spec(requestSpecification()).header("accept", "application/json")
                        .header("Authorization", token).pathParam("name", name).when().get(resourceAPI.getResource());
                System.out.println("Get Request Extracted name: " + name);
                break;
            case "DELETE":
                response = given().spec(requestSpecification()).header("accept", "application/json")
                        .header("Authorization", token).pathParam("name", name).when().delete(resourceAPI.getResource());
                System.out.println("Delete Request Extracted name: " + name);
                break;
            case "POST":
                handlePostRequest(resourceAPI);
                break;
            case "PATCH":
                handlePatchRequest(resourceAPI);
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }

    private void handlePostRequest(APIResources resourceAPI) throws IOException {
        switch (resourceAPI) {
            case AddEncoderAPI:
            	response = res.when().post(resourceAPI.getResource());
//            	.then().body("display_name.name", DisplayName);
                EncoderName = response.jsonPath().getString("name");
                System.out.println("Extracted name: " + EncoderName);
                ScenarioContext.setContext("EncoderName", EncoderName);
                break;
            case AddDecoderAPI:
            	response = res.when().post(resourceAPI.getResource());
                name = response.jsonPath().getString("name");
                System.out.println("Extracted name: " + name);
                ScenarioContext.setContext("DecoderName", name);
                break;
            case AddSourceAPI:
            	response = res.when().post(resourceAPI.getResource());
                name = response.jsonPath().getString("name");
                System.out.println("Extracted name: " + name);
                ScenarioContext.setContext("SourceName", name);
                break;
            case AddChannelAPI:
            	response = res.when().post(resourceAPI.getResource());
                name = response.jsonPath().getString("name");
                System.out.println("Extracted name: " + name);
                ScenarioContext.setContext("ChannelName", name);
                break;
            case AddBookingAPI:
                response = res.when().post(resourceAPI.getResource());
                name = response.jsonPath().getString("name");
                System.out.println("Extracted name: " + name);
                ScenarioContext.setContext("BookingName", name);
                break;

            case DisableEncoderAPI:
            case ReenableEncoderAPI:
            case DisableDecoderAPI:
            case ReenableDecoderAPI:
            case DisableSourceAPI:
            case ReenableSourceAPI:
            case DisableChannelAPI:
            case ReenableChannelAPI:
            case DisableBookingAPI:
            case ReenableBookingAPI:
//                String action;
//                if (resourceAPI == APIResources.DisableEncoderAPI || resourceAPI == APIResources.DisableDecoderAPI|| resourceAPI == APIResources.DisableSourceAPI|| resourceAPI == APIResources.DisableChannelAPI ||resourceAPI == APIResources.DisableBookingAPI) {
//                    action = ":disable";
//                } else {
//                    action = ":reenable";
//                }
//                performActionPostRequest(resourceAPI.getResource().replace("{name}", name) + action);
                
                performActionPostRequest(resourceAPI.getResource() );
//                performActionPostRequest(resourceAPI.getResource(), "TEST12", name, action);
            	
            	String actionSuffix = (resourceAPI == APIResources.DisableEncoderAPI || resourceAPI == APIResources.DisableDecoderAPI|| resourceAPI == APIResources.DisableSourceAPI|| resourceAPI == APIResources.DisableChannelAPI ||resourceAPI == APIResources.DisableBookingAPI) ? "disable" : "reenable";
//                updateURL = resourceAPI.getResource() + "/" + name + actionSuffix;
                
//                performActionPostRequest(resourceAPI,actionSuffix);
                break;

            case ListEncoderAPI:
            case ListDecoderAPI: 
            case ListSourceAPI:
            case ListChannelAPI:
            case ListBookingAPI:
            
                response = given().spec(requestSpecification()).header("accept", "application/json")
                        .header("Authorization", token).body("{}").when().post(resourceAPI.getResource());
                break;
                
                
            case StartBookingAPI:
            case StopBookingAPI:
            	 String actions;
                 if (resourceAPI == APIResources.StartBookingAPI) {
                     actions = ":start";
                 } else {
                     actions = ":stop";
                 }
//                 performActionPostRequest(name + actions);
                 break;

            default:
                throw new IllegalArgumentException("Invalid resource API: " + resourceAPI);
        }
    }

    private void handlePatchRequest(APIResources resourceAPI) throws IOException {
        String updateURL;
        if (resourceAPI == APIResources.UpdateEncoderAPI) {
        	String randomDisplayName = "UpdatedDisplayName" + "-" + UUID.randomUUID().toString().substring(0, 4);
        	String randomID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);

            updateURL = resourceAPI.getResource() + "/" + name + "?field_mask=display_name,vendor_encoder_identifier";
            response = given().spec(requestSpecification())
                    .header("accept", "application/json")
                    .header("Authorization", token)
                    .body(encoder.updateEncoderPayload(randomDisplayName, randomID))
                    .when()
                    .patch(updateURL);
        } else if (resourceAPI == APIResources.UpdateDecoderAPI) {
        	
        	String randomDisplayName = "UpdatedDisplayName" + "-" + UUID.randomUUID().toString().substring(0, 4);
        	String randomID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);
        	String randomChannelID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);

            updateURL = String.format("%s/%s?field_mask=display_name,vendor_decoder_identifier,project_id,zone,instance_name,vendor_channel_identifier", resourceAPI.getResource(), name);
            requestBody = decoder.updateDecoderPayload(randomDisplayName, randomID, "Updated ProjectID", "Updated Zone", "Updated InstanceName", randomChannelID);
            response = given().spec(requestSpecification())
                    .header("accept", "application/json")
                    .header("Authorization", token)
                    .body(requestBody)
                    .when()
                    .patch(updateURL);
        } 
        else if (resourceAPI == APIResources.UpdatePhenixChannelAPI) {
        	
        	String randomDisplayName = "UpdatedDisplayName" + "-" + UUID.randomUUID().toString().substring(0, 4);
//        	String randomID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);
        	String randomChannelID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);

            updateURL = String.format("%s/%s?field_mask=display_name,foreign_ids,auto_angle,vendor_channel_identifier,connector_id,description,video_channel_cluster,height ,frame_rate", resourceAPI.getResource(), name);
            System.out.println(updateURL);
            CreateChannel requestBody1 = channel.UpdatePhenixChannel(randomDisplayName, randomChannelID, "platforms/video/cameraAngles/headon", "updatedDescription", "LegacyStreamCode", "123", true);
            response = given().spec(requestSpecification())
                    .header("accept", "application/json")
                    .header("Authorization", token)
                    .body(requestBody1)
                    .when()
                    .patch(updateURL);
        }
        else if (resourceAPI == APIResources.UpdateDolbyChannelAPI) {
        	
        	String randomDisplayName = "UpdatedDisplayName" + "-" + UUID.randomUUID().toString().substring(0, 4);
//        	String randomID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);
//        	String randomChannelID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);

            updateURL = String.format("%s/%s?field_mask=display_name,foreign_ids,auto_angle,vendor_channel_identifier,create_replay,connector_id,decoder,description,video_channel_cluster,height ,frame_rate", resourceAPI.getResource(), name);
            CreateChannel requestBody1 = channel.UpdateDolbyChannel(randomDisplayName, "VENDOR_DOLBY","platforms/video/cameraAngles/headon","123","platforms/video/videoChannelClusters/018e5dcb-7d64-7578-b9b7-2f1a324894de","_480p","_25f", "updatedDescription", "LegacyStreamCode", "updatedForeignIdValue", true);
            response = given().spec(requestSpecification())
                    .header("accept", "application/json")
                    .header("Authorization", token)
                    .body(requestBody1)
                    .when()
                    .patch(updateURL);
        }
        
        else if (resourceAPI == APIResources.UpdateNoneChannelAPI) {
        	String randomDisplayName = "UpdatedDisplayName" + "-" + UUID.randomUUID().toString().substring(0, 4);
//        	String randomID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);
//        	String randomChannelID = "UpdatedID" + "-" + UUID.randomUUID().toString().substring(0, 4);
        	String decoderName = (String) ScenarioContext.getContext("DecoderName");
            updateURL = String.format("%s/%s?field_mask=display_name,foreign_ids,auto_angle,create_replay,connector_id,decoder,description,video_channel_cluster,height ,frame_rate", resourceAPI.getResource(), name);
            CreateChannel requestBody1 = channel.UpdateNoneChannel(randomDisplayName, "platforms/video/cameraAngles/headon",decoderName,"updatedDescription", "LegacyStreamCode", "123");
            response = given().spec(requestSpecification())
                    .header("accept", "application/json")
                    .header("Authorization", token)
                    .body(requestBody1)
                    .when()
                    .patch(updateURL);
        }
        
      
        
        else if (resourceAPI == APIResources.UpdateSourceAPI) {
            String randomDisplayName = "UpdatedDisplayName" + "-" + UUID.randomUUID().toString().substring(0, 4);
            String encoderName = (String) ScenarioContext.getContext("EncoderName");

            updateURL = String.format("%s/%s?field_mask=display_name,encoder,foreign_ids,automate_booking,automated_start_margin,automated_end_margin,angle_name", 
                                        resourceAPI.getResource(), name);
            CreateSource requestBody = source.updateSource(randomDisplayName, encoderName, "platforms/video/cameraAngles/headon", "LegacyStreamCode", "123", "100s", "120s", false);
            response = given().spec(requestSpecification())
                            .header("accept", "application/json")
                            .header("Authorization", token)
                            .body(requestBody)
                            .when()
                            .patch(updateURL);
        }

        else {
            throw new IllegalArgumentException("Invalid resource API for PATCH request: " + resourceAPI);
        }

        // Print out relevant information for debugging
        System.out.println("Patch Request for: " + name);
        System.out.println("Update API URL: " + updateURL);
        System.out.println(response.asString());
        
        // Assert the response status code
        assertEquals(response.getStatusCode(), 200, "Expected status code 200, but found " + response.getStatusCode());
    }
//    private void performActionPostRequest(APIResources resourceAPI,String actionSuffix) throws IOException {
//    	updateURL = resourceAPI.getResource() + "/" + name + ":"+actionSuffix;
//        response = given().spec(requestSpecification())
//                .header("accept", "application/json")
//                .header("Authorization", token)
//                .body("{}")
//                .when()
//                .post(updateURL);
//    }

    private void performActionPostRequest(String resource) throws IOException {
//    	String nameWithAction = name + action;
//    	String endpoint = resource.replace("{name}", name) + action;
        response = given().spec(requestSpecification()).header("accept", "application/json")
                .header("Authorization", token).body("{}").pathParam("name", name).when().post(resource);
    }

    @Then("the API calls got success with {int}")
    public void the_api_calls_got_success_with(Integer statusCode) {
        assertEquals(response.getStatusCode(), statusCode.intValue());
    }

    @Then("{string} in response body as {string}")
    public void in_response_body_as(String key, String expectedValue) {
        String actualValue = getJsonPath(response, key);
        assertEquals(actualValue, expectedValue);
        System.out.println(actualValue);
    }

    @And("verify name created maps to {string} using {string}")
    public void verify_name_created_maps_to_using(String expectedName, String resource) throws IOException {
        String createdName = getJsonPath(response, "name");
        name = createdName; // Save the created name for later use

        request = given().spec(requestSpecification()).header("accept", "application/json").header("Authorization", token);
        user_calls_with_api_request(resource, "GET");

        String actualName = getJsonPath(response, "name");
        System.out.println("Verified name: " + actualName);
        assertEquals(actualName, createdName);
        System.out.println("Passed");
    }
    
    @And("Extract the encoder name")
    public void extract_encoder_name() {
        String extractedName = response.jsonPath().getString("name");
        String displayName = response.jsonPath().getString("display_name.value");
        String vendorIdentifier = response.jsonPath().getString("vendor_encoder_identifier");

        System.out.println("Extracted encoder name: " + extractedName);
        
        scenarioContext.setContext("EncoderName", extractedName);
        ScenarioContext.setContext("DisplayName", displayName);
        ScenarioContext.setContext("VendorIdentifier", vendorIdentifier);
        
    }
    
    @When("Extract the decoder name")
    public void extract_the_decoder_name() {
    	 String extractedName = response.jsonPath().getString("name");
         System.out.println("Extracted decoder name: " + extractedName);
         scenarioContext.setContext("DecoderName", extractedName); // Store in scenario context
    }
    @When("Extract the source name")
    public void extract_the_source_name() {
    	 String extractedName = response.jsonPath().getString("name");
         System.out.println("Extracted source name: " + extractedName);
         scenarioContext.setContext("SourceName", extractedName); // Store in scenario context
    }
    @When("Extract the channel name")
    public void extract_the_channel_name() {
    	 String extractedName = response.jsonPath().getString("name");
         System.out.println("Extracted phenix channel name: " + extractedName);
         scenarioContext.setContext("phenixChannelName", extractedName); // Store in scenario context
    }
    
    @When("Extract the phenix booking name")
    public void extract_the_phenix_booking_name() {
   	 String extractedName = response.jsonPath().getString("name");
        System.out.println("Extracted phenix booking name: " + extractedName);
        scenarioContext.setContext("phenixBookingName", extractedName); // Store in scenario context
   }
    
    @Given("Delete the Encoder by its {string} using {string}")
    public void delete_the_encoder_by_its_using(String encoderName, String resource) throws IOException {
        name = encoderName; // Set the name to the provided encoderName

        request = given().spec(requestSpecification()).header("accept", "application/json").header("Authorization", token);
        user_calls_with_api_request(resource, "DELETE");
    }
}
