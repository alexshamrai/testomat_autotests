package io.testomat.report;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

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
    private static final ThreadLocal<ByteArrayOutputStream> outContent = new ThreadLocal<>();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @Override
    public void beforeAll(ExtensionContext context) {
        var filename = String.format("target/report/run_%d/%s_Results.html", System.currentTimeMillis(), context.getDisplayName());
        var htmlReporter = new ExtentSparkReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        var baos = new ByteArrayOutputStream();
        outContent.set(baos);
        System.setOut(new PrintStream(new LoggingOutputStream(baos, originalOut)));
        System.setErr(new PrintStream(new LoggingOutputStream(baos, originalErr)));

        test = reports.createTest(context.getDisplayName());
        test.log(Status.INFO, context.getDisplayName() + " - started");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        addConsoleLogs();

        if (context.getExecutionException().isPresent()) {
            var stackTrace = getStackTrace(context);
            test.fail("Stack Trace: " + stackTrace);
        } else {
            test.pass(context.getDisplayName() + " - passed");
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        reports.flush();
    }

    private static String getStackTrace(ExtensionContext context) {
        var throwable = context.getExecutionException().get();
        test.fail(throwable.getLocalizedMessage());

        var stringWriter = new StringWriter();
        var printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    private static void addConsoleLogs() {
        var content = outContent.get();
        if (content != null && content.size() > 0) {
            test.info("Console Logs: " + content);
        }

        System.setOut(originalOut);
        System.setErr(originalErr);
        outContent.remove();
    }

    static class LoggingOutputStream extends OutputStream {
        private final ByteArrayOutputStream baos;
        private final PrintStream original;

        public LoggingOutputStream(ByteArrayOutputStream baos, PrintStream original) {
            this.baos = baos;
            this.original = original;
        }

        @Override
        public void write(int b) {
            baos.write(b);
            original.write(b);
        }
    }

}