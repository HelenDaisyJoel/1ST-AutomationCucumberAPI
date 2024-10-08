package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		if (req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging1.txt"));
		req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
		
	
		public static String getGlobalValue(String key) throws IOException
		{
			Properties prop=new Properties();
			FileInputStream fis= new FileInputStream("C:\\SampleWorkspace\\1st-prism-web-integration-test\\1ST-AutomationCucumberAPI\\src\\test\\java\\resources\\global.properties");
			prop.load(fis);
			return prop.getProperty(key);
		}
		public String getJsonPath(Response response, String key) {
	        try {
	            String resp = response.getBody().asString();
	            if (resp != null && !resp.isEmpty()) {
	                JsonPath js = new JsonPath(resp);
	                return js.get(key).toString();
	            } else {
	                System.out.println("Response body is empty or null.");
	                return null;  // Or throw an exception or return a meaningful value based on your requirements.
	            }
	        } catch (Exception e) {
	            System.out.println("Exception occurred while parsing JSON response: " + e.getMessage());
	            e.printStackTrace();
	            return null;  // Or throw an exception or return a meaningful value based on your requirements.
	        }
	    }
		protected static String baseUrl;

	    public Utils() throws IOException {
	        Properties prop = new Properties();
//	        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties");
	        FileInputStream fis= new FileInputStream("C:\\SampleWorkspace\\1st-prism-web-integration-test\\1ST-AutomationCucumberAPI\\src\\test\\java\\resources\\global.properties");
	        prop.load(fis);
	        baseUrl = prop.getProperty("baseUrl");
	    }

	    public String getBaseUrl() {
	        return baseUrl;
	    }
	

}