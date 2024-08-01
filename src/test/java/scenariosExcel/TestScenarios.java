package scenariosExcel;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.DataProvider;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.*;
import org.testng.Assert;
import reporting.ExtentReportManager;
import reporting.SetUp;
import scenariosExcel.BasePojo;
import reusableMethods.ExcelUtils;
import testCase.APIs;
import assertions.*;

public class TestScenarios extends BasePojo{
	
	public static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	APIs apis = new APIs();

	@Test(dataProvider = "createReqResPojoData")
	public void createReqResPojoAndVerify(CreateReqResPojoScenario createReqResPojoScenario) {

		ExtentTest test = SetUp.create_ExtentReport.createTest("Test Name - " + createReqResPojoScenario.getScenarioId(),
				createReqResPojoScenario.getScenarioDesc());
		SetUp.extentTestThreadLocal.set(test);
		
		Response response = apis.createAPIs(createReqResPojoScenario);

		if (createReqResPojoScenario.getExpectedStatusCode() != 200) {
			if (createReqResPojoScenario.getScenarioId().equalsIgnoreCase("CreateReqResPojo_DuplicateID"))
				response = apis.createAPIs(createReqResPojoScenario);
			Assert.assertEquals(response.getStatusCode(), createReqResPojoScenario.getExpectedStatusCode());
			Assert.assertEquals(response.jsonPath().getString("message"), createReqResPojoScenario.getExpectedErrorMessage());
		} else {
			Map<String, Object> expectedValueMap = new HashMap<>();

			expectedValueMap.put("name", createReqResPojoScenario.getName());
			expectedValueMap.put("job", createReqResPojoScenario.getJob());

			if (createReqResPojoScenario.getScenarioId().equalsIgnoreCase("CreateReqResPojo_WithoutID")) {
				expectedValueMap.remove("id");
			}
			 AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
		}

	}

	@DataProvider(name = "createReqResPojoData")
	public Iterator<CreateReqResPojoScenario> getcreateReqResPojoData() throws IOException {
		List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils
				.getExcelDataAsListOfMap("CreateRequestDataScenarios", "Sheet1");
		List<CreateReqResPojoScenario> createReqResPojoData = new ArrayList<>();
		for (LinkedHashMap<String, String> data : excelDataAsListOfMap) {
			CreateReqResPojoScenario createReqResPojoScenario = getCustomizedcreateReqResPojoData(data);
			createReqResPojoData.add(createReqResPojoScenario);
		}
		return createReqResPojoData.iterator();
	}

	private CreateReqResPojoScenario getCustomizedcreateReqResPojoData(LinkedHashMap<String, String> data) {
		CreateReqResPojoScenario createReqResPojoScenario = new CreateReqResPojoScenario();
		createReqResPojoScenario.setScenarioId(data.get("ScenaroID"));
		createReqResPojoScenario.setScenarioDesc(data.get("ScenarioDesc"));
		createReqResPojoScenario.setExpectedStatusCode(Integer.parseInt(data.get("ExpectedStatusCode")));
		if (!data.get("ExpectedErrorMessage").equals("NO_DATA"))
			createReqResPojoScenario.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));

		if (!data.get("Name").equalsIgnoreCase("NO_DATA"))
			createReqResPojoScenario.setName(data.get("Name"));

		if (!data.get("Job").equalsIgnoreCase("NO_DATA"))
			createReqResPojoScenario.setJob(data.get("Job"));

		return createReqResPojoScenario;
	}

}
