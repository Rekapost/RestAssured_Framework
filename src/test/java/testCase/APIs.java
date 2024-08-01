package testCase;
import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import poijiExcel.PoijiExcel;
import poijiExcel.ReqResPoiji;
import reusableMethods.RestUtils;
import scenariosExcel.CreateReqResPojoScenario;
import reqresPojoBody.*;

public class APIs {

	public Response createAPIs(Map<String,Object> createPayload) {
		String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
		//object to string so casting (String)
		return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());		 
	}
	
	//ReqResPojo is java object
	public Response createAPIs(PojoBuilder createPayload) {
		String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
		//object to string so casting (String)
		return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());		 
		}
	
	public Response createAPIs(PojoLombok createPayload) {
		String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
		//object to string so casting (String)
		return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());		 
		}
	
	public Response createAPIs(PoijiExcel createPayload) {
		String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
		//object to string so casting (String)
		return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());		 
		}

	public Response createAPIs(ReqResPoiji createPayload) {
		String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
		//object to string so casting (String)
		return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());	
	}
	
	public Response createAPIs(CreateReqResPojoScenario createPayload) {
		// TODO Auto-generated method stub
		String endPoint	= (String) BaseEnvironment.dataFromJsonFile.get("createQAEndpoint");
		return  RestUtils.performPost(endPoint, createPayload, new HashMap<>());	
	}

	

	
}
