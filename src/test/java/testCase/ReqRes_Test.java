package testCase;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import junit.framework.Assert;
import reusableMethods.JsonUtils;
import reusableMethods.Payloads;
import reusableMethods.RestUtils;
import org.javers.core.JaversBuilder;
import org.javers.core.Javers;
import org.testng.annotations.Test;
import reqresPojoBody.*;
import testCase.APIs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReqRes_Test {

	/*
	 * { "name": "morpheus", "job": "leader" }
	 */

	 //@Test(priority=1)
	public void testPostUsingString() {
		String endPoint = "https://reqres.in/api/users";
		String requestPayload = Payloads.CreatePayloadString("Reka", "QA");
		Response response = RestUtils.performPost(endPoint, requestPayload, new HashMap<>());
		Assert.assertEquals(response.statusCode(), 201);
	}

	// @Test(priority=2)
	public void testPostUsingMap() {
		String endPoint = "https://reqres.in/api/users";
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMap("RekaNV", "QA");
		Response response = RestUtils.performPost(endPoint, requestMapPayload, new HashMap<>());
		Assert.assertEquals(response.statusCode(), 201);
	}

	// @Test(priority=3)// Read data from .json file
	public void testPostUsingJsonFile() throws StreamReadException, DatabindException, IOException {
		Map<String, Object> data = JsonUtils.getJsonDataAsMap("/environments/qa/ApiData.json");
		// String endPoint="https://reqres.in/api/users";
		String endPoint = (String) data.get("createQAEndpoint");
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMap("RekaNV", "QA");
		Response response = RestUtils.performPost(endPoint, requestMapPayload, new HashMap<>());
		Assert.assertEquals(response.statusCode(), 201);
	}

	// passing environment
	// @Test(priority = 4)
	public void environmentSettings() throws StreamReadException, DatabindException, IOException {
		
		String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
		// ? then
		System.out.println("Environment Value   : "+env);
		Map<String, Object> data = JsonUtils.getJsonDataAsMap("/environments/" + env + "/ApiData.json");
		// String endPoint="https://reqres.in/api/users";
		String endPoint = (String) data.get("createQAEndpoint");
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMap("RekaNV", "QA");
		Response response = RestUtils.performPost(endPoint, requestMapPayload, new HashMap<>());
		Assert.assertEquals(response.statusCode(), 201);
		//pom.xml file ->runas -> maven build ->test -Denv-qa
	}
		
	//@Test(priority = 5)
	public void settingsEnvAndAPI() throws StreamReadException, DatabindException, IOException {
		APIs apis=new APIs();  // OR u can extend the class to APIs
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMap("RekaNV", "QA");
		Response response = apis.createAPIs(requestMapPayload);
		Assert.assertEquals(response.statusCode(), 201);
		//pom.xml file ->runas -> maven build ->test -Denv-qa
	}
	
	//@Test(priority = 6)
	public void dataFromDataFaker() throws StreamReadException, DatabindException, IOException {
		APIs apis=new APIs();  // OR u can extend the class to APIs
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMapDataFaker();
		Response response = apis.createAPIs(requestMapPayload);
		Assert.assertEquals(response.statusCode(), 201);
		//pom.xml file ->runas -> maven build ->test -Denv-qa
//3.		
//		Javers javers=JaversBuilder.javers().build();
//		javers.compare(requestMapPayload, response.body());
	
		/* 
		  s2.setAddress(Arrays.asList(a2, a1));		
          Javers javers = JaversBuilder.javers()
                .withListCompareAlgorithm(ListCompareAlgorithm.AS_SET).build();
	   */
	}
	
	//@Test(priority = 7)   
	public void dataFromPojoBuilder() throws StreamReadException, DatabindException, IOException {
		APIs apis=new APIs();  // OR u can extend the class to APIs
		PojoBuilder pojoBodyBuilder = Payloads.getPayloadFromPojoBuilder();
		Response response = apis.createAPIs(pojoBodyBuilder);
		Assert.assertEquals(response.statusCode(), 201);			
		
		//ReqResPojo payload= new ReqResPojo().toBuilder().name("Reka").build();  		
		////  if 	@Builder(toBuilder=true) 
		
		//ReqResPojo payload= new ReqResPojo();
		//System.out.println(new OjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(payload)  );
	    //ReqResPojo payload= new ReqResPojo().toBuilder().gender(Gender.male).build();
	    //Gender.male should come form enum class
	}
	
	@Test(priority = 8)   
		public void createandverifyResponse() throws JsonMappingException, JsonProcessingException {
			APIs apis=new APIs();  // OR u can extend the class to APIs
			PojoLombok pojoBody=new PojoLombok(); 			
			pojoBody.setName("Reka");
			pojoBody.setJob("RestAssured");
			Response response = apis.createAPIs(pojoBody);
	//1.		
			Assert.assertEquals(response.statusCode(), 201);
			Assert.assertEquals(response.jsonPath().getString("name"), pojoBody.getName());
	
	//2.
			ObjectMapper objectMapper = new ObjectMapper();
			PojoLombok createReqResResponse = objectMapper.readValue(response.getBody().asString(), PojoLombok.class);
	        Assert.assertEquals(createReqResResponse, pojoBody);
	        
	       //String name= createReqResResponse.getName();
	       
	        
	}					
}
