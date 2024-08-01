package reusableMethods;
import reporting.ExtentReportManager;
import java.util.HashMap;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RestUtils {
	
	private static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload, Map<String,String>headers) {
        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload);
    }
	
	 public static Response performPost(String endPoint, String requestPayload, Map<String,String>headers) { 
		 	RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers); 
		 	Response response	 = requestSpecification.post(); 
		 	printRequestLogInReport(requestSpecification);
		 	printResponsetLogInReport(response);
		 	return response;
		 	}
//OR	 
/*	public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers) {
		Response response = RestAssured.given().log().all()
				.baseUri(endPoint)
				.contentType(ContentType.JSON)
				.headers(headers)
				.body(requestPayload)
				.when()
				.post(endPoint)
				.then().log().all().extract().response();
		return response;
	}
*/
	
	  public static Response performPost(String endPoint, Map<String, Object> requestPayload, Map<String,String>headers) {
		  RequestSpecification  requestSpecification = getRequestSpecification(endPoint, requestPayload,headers); 
		  Response response = requestSpecification.post();
		  printRequestLogInReport(requestSpecification);
		  printResponsetLogInReport(response);
		  return response; 
	  }
	 
//OR
/*	public static Response performPost(String endPoint, Map<String, Object> requestPayload,
			Map<String, String> headers) {

		Response response = RestAssured.given().log().all()
				.baseUri(endPoint)
				.contentType(ContentType.JSON)
				.headers(headers)
				.body(requestPayload)
				.when()
				.post(endPoint)
				.then()
				.log().all().extract().response();
		return response;
	}
*/
	  
	public static Response performPost(String endPoint, Object requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
		Response response = requestSpecification.post();
		printRequestLogInReport(requestSpecification);
		printResponsetLogInReport(response);
		return response;
	}	
	
	private static void printRequestLogInReport(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
		ExtentReportManager.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are ");
        //ExtentReportManager.logInfoDetails("Headers are "+ queryableRequestSpecification.getHeaders().asList().toString());
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request body is  : ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
        //ExtentReportManager.logJson(queryableRequestSpecification.getBody());
	}
	
	private static void printResponsetLogInReport(Response response) {
		
        ExtentReportManager.logInfoDetails("Response Status is " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Headers are ");
       // ExtentReportManager.logInfoDetails("Response Headers are "+response.getHeaders() .asList().toString());
        ExtentReportManager.logHeaders(response.getHeaders() .asList());
        ExtentReportManager.logInfoDetails("Response body is  : ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
	}	
}
