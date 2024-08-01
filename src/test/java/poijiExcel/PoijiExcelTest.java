package poijiExcel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import interview.Utils;
import testCase.APIs;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.poiji.bind.Poiji;

import assertions.AssertionUtils;
import io.restassured.response.Response;

public class PoijiExcelTest {

	@DataProvider(name = "ExcelData")
	public Iterator<PoijiExcel> getCreatePoijiExcelData() throws EncryptedDocumentException, IOException {
		//String filename="C:\\Users\\Reka\\eclipse-workspace\\rest_assured_training\\src\\test\\resources\\reqres.xlsx";
		List<LinkedHashMap<String, String>> excelDataAsListOfMap = Utils.readDataDromExcel("reqresExcel","Sheet1");
		System.out.println(excelDataAsListOfMap);
		
		List<PoijiExcel> PoijiExcelData = new ArrayList<>();
		for (LinkedHashMap<String, String> data : excelDataAsListOfMap) {
			PoijiExcel PojoExcelBody = PoijiExcel.builder()				
								.name(data.get("name"))
								.job(data.get("job"))
								.build();
			PoijiExcelData.add(PojoExcelBody);		
			
		}
		return PoijiExcelData.iterator();
	}
		
	@Test(dataProvider="ExcelData")
	public void pojiExcelReadTest(PoijiExcel PoijiExcelData) {
			APIs apis=new APIs();
			Map<String,Object> excelData = new HashMap<>();
	        excelData.put("name", PoijiExcelData.getName());
	        excelData.put("job", PoijiExcelData.getJob());	
	        System.out.println(excelData);
			Response response = apis.createAPIs(PoijiExcelData);
	       System.out.println( response.body().asString());     		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//	https://youtu.be/e3a5OgvhFqs?si=p9-GBGmI-V2kZh7q	
	
	@DataProvider(name = "PoijiExcelData")
	public Iterator<ReqResPoiji> getCreateDataByPoiji() {
//		List<PoijiExcel> PoijiExcelData = Poiji.fromExcel(new File("./src/test/resources/reqresPoiji.xlsx"),
//				PoijiExcel.class);
		
		List<ReqResPoiji> PoijiExcelData= Poiji.fromExcel(new File("src/test/resources/testData/reqresPoiji.xlsx"), ReqResPoiji.class);	
		System.out.println(PoijiExcelData);
		return PoijiExcelData.iterator();
	}

	//@Test(dataProvider="PoijiExcelData")
	public void excelReadTest(ReqResPoiji reqresPoijiExcel) {	
			APIs apis=new APIs();
			Map<String,Object> excelData = new HashMap<>();
	        excelData.put("name", reqresPoijiExcel.getName());
	        excelData.put("job", reqresPoijiExcel.getJob());	
	        System.out.println(excelData);
			Response response = apis.createAPIs(reqresPoijiExcel);
	        System.out.println( response.body().asString());
	        
	}
}
