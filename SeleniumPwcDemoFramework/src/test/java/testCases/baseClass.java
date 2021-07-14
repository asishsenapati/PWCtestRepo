
package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.read.biff.BiffException;
import support.SupportFunctions;

public class baseClass 
{
	
	public String baseUrl="https://www.makemytrip.com/hotels/";
	public String InputFilePath=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"input"+File.separator+"InputMMT.xls";
	public String Sheetname="Sheet1";
	public  WebDriver driver=null;
	@BeforeClass
	public void setup() throws IOException {
			WebDriverManager.chromedriver().setup(); 
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(baseUrl);
	}
	@AfterClass
	public void tearDown() {
			driver.quit();
	}

}
