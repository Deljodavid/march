package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReporting {
	
	public static ExtentReports getExtendreport() {

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
	spark.config().setDocumentTitle("Automation Results");
	spark.config().setReportName("Navigate");
	spark.config().setTheme(Theme.DARK);
	extent.attachReporter(spark);
	
	return extent;
	
	
	
	}
}
	