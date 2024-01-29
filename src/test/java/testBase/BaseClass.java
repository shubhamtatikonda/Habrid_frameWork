package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups = {"Regreassion","master","sanity"})
	@Parameters({"os","browser"})
	
	public void setup(String os , String br) throws IOException
	{
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		
		//Loading Log4j2 file
		logger = LogManager.getLogger(this.getClass()); //log4j
		
//		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
//		{
//			DesiredCapabilities capability = new DesiredCapabilities();
//			//os
//			if(os.equalsIgnoreCase("windows"))
//			{
//				capability.setPlatform(Platform.WIN11);
//			}
//			else if(os.equalsIgnoreCase("mac"))
//			{
//				capability.setPlatform(Platform.MAC);
//			}
//			else
//			{
//				System.out.println("No matching OS");
//				return;
//			}
//			//browser
//			
//			switch(br.toLowerCase())
//			{
//			case"chrome" : capability.setBrowserName("chrome");break;
//			case"edge" : capability.setBrowserName("Microsoftedge");break;
//			default : System.out.println("no matching");return;
//			}
//			WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capability);
//			}
//		else
//			if(p.getProperty("execution_evn").equalsIgnoreCase("local"))
//			{
		
		
	//launching browser base on condition - local
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		default: System.out.println("No Matching browser");
		return;
		}	
			//}
			
		logger.debug("**Appication Log**");
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"Regreassion","master","sanity"})
	public void tearDown()
	{
		driver.close();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
	
	
}
