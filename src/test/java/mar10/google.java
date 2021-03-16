package mar10;



import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;



import base.Baseclass;

public class google extends Baseclass {
	public static Logger log = LogManager.getLogger("google");
    

	@Test(groups= {"0"})
	public void gotogoogle() throws IOException {
		
		DOMConfigurator.configure("Log4j.xml");
		driver = Baseclass.browsersetup();
		log.info("started browser");
		driver.get(getvalueofkey("url1"));
		log.info("reached google");
		driver.quit();
		
	}
}
