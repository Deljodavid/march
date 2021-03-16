package mar10;



import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.xml.DOMConfigurator;

import base.Baseclass;

public class Wikipedia extends Baseclass {
	
	
	public static Logger log = LogManager.getLogger("wiki");
	
	
	
	public WebDriver driver;
	@Test(groups= {"1"})
	public void gotowiki() throws IOException{
		DOMConfigurator.configure("Log4j.xml");
		driver = Baseclass.browsersetup();
		log.info("started browser");
		driver.get(getvalueofkey("url2"));
		log.info("reached wiki");
		Assert.assertFalse(true);
		driver.quit();
		
	}
	
	
	
}
