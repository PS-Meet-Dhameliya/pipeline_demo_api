package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestStepFinished;

public class ExtentReportListners implements ConcurrentEventListener {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        // Register event handlers
        publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    private void onTestRunStarted(TestRunStarted event) {
        sparkReporter = new ExtentSparkReporter("./ExtendReports/extentReport.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Cucumber Testing");
        sparkReporter.config().setReportName("Cucumber Test Results");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        test = extent.createTest(event.getTestCase().getName());
    }

    private void onTestStepFinished(TestStepFinished event) {
        // Detailed step information
        String stepDetails = event.getTestStep().toString(); // Adjust based on how you want to extract step details

        if (event.getResult().getStatus().equals(Status.PASSED)) {
            test.pass(stepDetails);
        } else if (event.getResult().getStatus().equals(Status.FAILED)) {
            Throwable exception = event.getResult().getError();
            test.fail(stepDetails).fail(exception);
        }
        // Handle other statuses (SKIPPED, PENDING, etc.)
        else {
            test.skip("Test step skipped: " + stepDetails);
        }
    }

    private void onTestRunFinished(TestRunFinished event) {
        if (extent != null) {
            extent.flush();
        }
    }
}
