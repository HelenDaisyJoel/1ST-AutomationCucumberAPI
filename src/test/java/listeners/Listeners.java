package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;

public class Listeners implements ITestListener {
    private ExtentReports extent;
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initialize the report
        String path = System.getProperty("user.dir") + "\\report\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Video Platform Test Results");
        reporter.config().setDocumentTitle("Automation Test Report");
        reporter.config().setTheme(Theme.DARK);        
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Helen");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create test entry in report
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Set the current test in ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed");
        test=extent.createTest(result.getName());
        test.log(Status.PASS,"Testcase is Passed:"+result.getName());    
               
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log the exception that caused the failure
        extentTest.get().fail(result.getThrowable());

        // Retrieve the failed test method name
        String methodName = result.getMethod().getMethodName();

        // Assuming you have a way to capture request and response data for your API tests
        // This is a placeholder for where you would normally capture request and response
        // For example:
        // String requestDetails = getRequestDetails();
        // String responseDetails = getResponseDetails();

        // Add logs for request and response details (replace with actual method to get details)
        String requestDetails = "Request details not available"; // Replace with actual request details
        String responseDetails = "Response details not available"; // Replace with actual response details

        extentTest.get().info("Request Details: " + requestDetails);
        extentTest.get().info("Response Details: " + responseDetails);

        // If you have any other data you want to log, you can add it here
        extentTest.get().fail("Test case failed: " + result.getName());
        extentTest.get().fail("Cause of failure: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report
        extent.flush();
    }
}
