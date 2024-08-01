package reporting;
import org.testng.ITestContext;
import org.testng.ITestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.ExtentColor;
import org.testng.ITestResult;
import java.util.Arrays;

public class SetUp implements ITestListener {

	// append testcase to report
	// to avoid overlap of testcase while running and generate report we have to use thread local
	// 2 thread 2 testcase

	public static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
	public static ExtentReports create_ExtentReport;
	public static String name="Reka";
	/*
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 */

	public void onStart(ITestContext context) {
		// not implemented
		// extent report will be initialized
		String fileName = ExtentReportManager.getReportNameWithTimeStamp();
		// reports folder will be created
		String fullReportPath = System.getProperty("user.dir") + "\\reports\\" + fileName;
		create_ExtentReport = ExtentReportManager.createExtentReport(fullReportPath, "Test API Automation Report",
				"Test Execution Report");
	}

	// when @Test annotated method starts execution. onTestStartMethod will be
	// excuted automatically
	public void onTestStart(ITestResult result) {
		// not implemented
		ExtentTest test=create_ExtentReport.createTest("Test Name :   "+result.getTestClass().getName()+" -  "+result.getMethod().getMethodName());
		// extract name of current test
		// set by thread , test executed by current thread / given thread
		extentTestThreadLocal.set(test);	
	}
	
	public  void onTestFailure(ITestResult result) {
	    // not implemented
		
	        ExtentReportManager.logFailDetails(result.getThrowable().getMessage());
	        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
	        stackTrace = stackTrace.replaceAll(",", "<br>"); // br New Line
	        String formmatedTrace = "<details>\n" +
						                "    <summary>Click Here To See Exception Logs</summary>\n" +
						                "    " + stackTrace + "\n" +
						            "</details>\n";
	        ExtentReportManager.logExceptionDetails(formmatedTrace);
	  }

	public void onFinish(ITestContext context) {
		// not implemented
		// Extent report will be genearted with data
		if (create_ExtentReport != null) {
			create_ExtentReport.flush();
		}
	}
 
}
