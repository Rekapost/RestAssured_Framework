package interview;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import reusableMethods.JsonUtils;
import reusableMethods.Payloads;
import reusableMethods.RestUtils;
import testCase.APIs;
import testCase.BaseEnvironment;

public class Utils {
	
	public static Logger logger= (Logger)LogManager.getLogger();

	public static void info(String message) {
		logger.info(message);	
		}
//1.	
	public static RequestSpecification getRequest(String endpoint, Object payload, Map<String, String> headers )
		{	
	       		given()
				.baseUri(endpoint)
				.body(payload)
				.headers(headers)
				.contentType(ContentType.JSON);	
	       return requestSpecification;
	}
	  
//2	
	public static Response performPost(String endpoint, Object payload, Map<String, String> headers) {
		RequestSpecification request= getRequest( endpoint, payload,  headers);
		Response reponse=request.post();
		return reponse;	
		
		//String requestPayload
		//Map<String, Object> requestPayload
		//sObject requestPayloadAsPojo
	}
	
//3.
	public static Map<String,Object> CreatePayloadMap(String name,String job) {	
		Map<String , Object> data= new HashMap<>();
		data.put("name", name);
		data.put("job", job);
		return data;		
	}
		
	public static String CreatePayloadString(String name, String job) {	 
		String payload="{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
		return payload;		
			}
	
//4.
	//@Test
	public void testPostUsingMap() {
		String endPoint = "https://reqres.in/api/users";
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMap("RekaNV", "QA");
		Response response = RestUtils.performPost(endPoint, requestMapPayload, new HashMap<>());
		Assert.assertEquals(response.statusCode(), 201);
	}
	
//x	
	public Response createAPIs(Map<String,Object> createPayload) {
			String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
			//object to string so casting (String)
			return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());		 
			}
//y.@Test
	public void dataFromDataFaker() throws StreamReadException, DatabindException, IOException {
		APIs apis=new APIs();  // OR u can extend the class to APIs
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMapDataFaker();
		Response response = apis.createAPIs(requestMapPayload);
		Assert.assertEquals(response.statusCode(), 201);
	}

//5.@Test
	public void testPostUsingJsonFile() throws StreamReadException, DatabindException, IOException {
		Map<String, Object> data = JsonUtils.getJsonDataAsMap("/environments/qa/ApiData.json");
		// String endPoint="https://reqres.in/api/users";
		String endPoint = (String) data.get("createQAEndpoint");
		Map<String, Object> requestMapPayload = Payloads.CreatePayloadMap("RekaNV", "QA");
		Response response = RestUtils.performPost(endPoint, requestMapPayload, new HashMap<>());
		Assert.assertEquals(response.statusCode(), 201);
	}

	
	
	public static Map<String,Object> readJsonFile(String filename) throws StreamReadException, DatabindException, IOException 
	{
		ObjectMapper mapper= new ObjectMapper();
		String path=System.getProperty("user.dir")+"src/test/resources"+ filename;
		Map<String,Object> data= mapper.readValue(new File(path), new TypeReference<Map<String,Object>>(){});
		return data;	
	}	
		
	public static List<LinkedHashMap<String,String>> readDataDromExcel(String excelFileName, String sheetname) throws EncryptedDocumentException, IOException
	{
		DataFormatter formatter=new DataFormatter();
		List<LinkedHashMap<String,String>> dataFromExcel= new ArrayList<>();
		LinkedHashMap<String,String> mapdata ;
		List<String> allKeys= new ArrayList<String>();
		Workbook workbook=WorkbookFactory.create(new File("src/test/resources/testData/" + excelFileName + ".xlsx"));		
		Sheet sheet= workbook.getSheet(sheetname);
		int rowCount=sheet.getPhysicalNumberOfRows();	
		for(int i=0;i<rowCount;i++) {
			mapdata = new LinkedHashMap<String,String>();
			if(i==0) {
				int collCount =	sheet.getRow(i).getPhysicalNumberOfCells();
				   for(int j=0;j<collCount;j++) {
					 allKeys.add(sheet.getRow(0).getCell(j).getStringCellValue());		//heading 		   
				   }
			}else {
			int collCount =	sheet.getRow(i).getPhysicalNumberOfCells();
			   for(int j=0;j<collCount;j++) {	
				  //String cellValue= sheet.getRow(i).getCell(j).getStringCellValue();
				  String formattedCellValue= formatter.formatCellValue(sheet.getRow(i).getCell(j));			   		   	
			      mapdata.put(allKeys.get(j), formattedCellValue)  ; 
		     }
			   dataFromExcel.add(mapdata);		
			}			
		}
//		

		return dataFromExcel;
	}
	
	
	public void readDataFromConfigFile() throws IOException {
		String filePath=" ";
		FileInputStream stream= new FileInputStream(filePath);
		Properties prop= new Properties();
		prop.load(stream);
		String value=prop.getProperty("key");
		stream.close();				
	}
	
/*	public void user_login_using_username_and_password_from_given_and(String sheetName, Integer rowNumber)
			throws IOException, InvalidFormatException, InterruptedException {
		ExcelReader reader = new ExcelReader();
		Loggerload.info("User enter login credentials ");
		List<Map<String, String>> testData = reader.getData("src/test/resources/Exceldata/LoginDsAlgo.xlsx", 0);
		String User_name = testData.get(rowNumber).get("username"); // heading
		String Pass_word = testData.get(rowNumber).get("password"); // heading
		collection.loginWithCredentials(User_name, Pass_word);
*/
	
/*	
	RestAssured.requestSpecification = given()
			.header("Content-Type","application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request1.toJSONString());
	response1 = RestAssured.requestSpecification.when()
			.post("/saveprogram")
			.then()
			.extract()
			.response();

	String responseBody=response1.getBody().asString();
	JsonPath js =new JsonPath(responseBody);
	first_programId=js.getString("programId");
	first_programName=js.getString("programName");
	System.out.println("programId : "+first_programId);
	System.out.println("programName : "+first_programId);
*/		
}