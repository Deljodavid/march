package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Baseclass {
	
	public static WebDriver driver;
	public static WebDriver browsersetup() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", getvalueofkey("pathcd"));
		driver = new ChromeDriver();
		return driver;
	}
	
	public static String takescreenshot(String methodname, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = "./report/screenshots/"+methodname+".png";
		String filename = "./screenshots/"+methodname+".png";
		FileUtils.copyFile(src,new File(destination));
		return filename;
		
		
		
	}
	
	public static String getvalueofkey(String key) throws IOException {
		
		Properties propfile = new Properties();
		FileInputStream fis = new FileInputStream("values.properties");
		propfile.load(fis);
		return (String) propfile.get(key);
		
		
		
	}

}
