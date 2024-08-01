package testCase;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import reqresPojoBody.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PojoTest {
	
	@Test(priority=1)
	public void postData() throws JsonProcessingException {		
		baseURI="https://reqres.in";
		Faker faker=new Faker();
		PojoLombok body= new PojoLombok();
		body.setName(faker.name().firstName());
		body.setJob(faker.expression("#{options.option 'QA','SDET','Tester'}"));
		
	Response response=	given()
			.contentType(ContentType.JSON)
			.body(body)
			.when().log().all()
			.post("/api/users");
			
	response.getBody();
	response.getStatusCode();	
	String resName=response.jsonPath().getString("name");
	System.out.println(resName);
	String reqName=body.getName();
	System.out.println(reqName);
	Assert.assertEquals(response.jsonPath().getString("name"), body.getName());
	
			ObjectMapper mapper = new ObjectMapper();
//		    java object to json object
		    String mapperdata=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
		    System.out.println("  java object to json object  :  "+mapperdata);		    
		    
//		    json object to java object
		    String jsondata="{\r\n"
		    		+ "    \"name\": \"Hugo\",\r\n"
		    		+ "    \"job\": \"Tester\"\r\n"
		    		+ "}";
		    PojoLombok javaobject=  mapper.readValue(jsondata,PojoLombok.class);
		  System.out.println(javaobject.getName());
		  System.out.println( javaobject.getJob());
	}
}
