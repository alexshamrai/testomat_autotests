package io.testomat.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ExtentReportListener implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback {

    private static ExtentReports reports;
    private static ExtentTest test;

    @Override
    public void beforeAll(ExtensionContext context) {
        var filename = String.format("target/report/run_%d/%s_Results.html", System.currentTimeMillis(), context.getDisplayName());
        var htmlReporter = new ExtentSparkReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        test = reports.createTest(context.getDisplayName());
        test.log(Status.INFO, context.getDisplayName() + " - started");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            test.pass(context.getDisplayName() + " - passed");
        } else {
            test.fail(context.getExecutionException().get().getLocalizedMessage());
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reports.flush();
    }
}