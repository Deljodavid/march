package listeners;

import base.Baseclass;
import report.ExtendReporting;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.Zip;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class Listenerclass extends Baseclass implements ITestListener{

	ExtentReports ex = ExtendReporting.getExtendreport();
	ExtentTest Test;
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	Test = ex.createTest(result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Test.pass(result.getMethod().getMethodName()+" run successfully");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver=null;
		String methodname =result.getMethod().getMethodName();
		
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			Test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot(methodname, driver)).build());
			driver.quit();
			System.out.println("reached");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("catch");
			
		}
		

		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		ex.flush();
		try {
			String zip = Zip.zip(new File("./report"));
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("./report.zip"));
			byte[] decode = Base64.getDecoder().decode(zip);
			stream.write(decode);
			stream.close();
			
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("report.zip");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Result");

			
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(getvalueofkey("uname"), getvalueofkey("upass")));
			email.setSSLOnConnect(true);
			email.setFrom("****@gmail.com");
			email.setSubject("Test Results");
			email.attach(attachment);
			email.setMsg("Results are attached");
			
			email.addTo(getvalueofkey("Toadd"));
			email.send();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("catch");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
