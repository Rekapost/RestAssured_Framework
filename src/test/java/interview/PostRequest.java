package interview;
import java.util.HashMap;
import io.restassured.response.Response;
//import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.asserts.*;
import junit.framework.Assert;
import reusableMethods.RestUtils;

public class PostRequest {
	
	@Test
	 public void post() {	 
		 String endPoint="https://reqres.in/api/users";
		 /*{
    "name": "morpheus",
    "job": "leader"
			}
		  */
		 String requestPayload="{\r\n"
		 		+ "    \"name\": \"morpheus\",\r\n"
		 		+ "    \"job\": \"leader\"\r\n"
		 		+ "} ";
		 Response response= RestUtils.performPost(endPoint, requestPayload, new HashMap<>());
		 int status= response.getStatusCode();
		 Assert.assertEquals(response.statusCode(), 201);	 
	 }
}
