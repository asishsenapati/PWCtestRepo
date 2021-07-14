package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.commons.lang3.StringUtils; 
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import testCases.baseClass;
public class SupportFunctions extends baseClass
{

	@FindBy(xpath="//*[@id='city']")
    WebElement cityBox;
	
    @FindBy(xpath="//p[@class='locusLabel appendBottom5']")
    List<WebElement> allElements;
    
    @FindBy(xpath="//input[@placeholder='Enter city/ Hotel/ Area/ Building']")
    WebElement cityTextInp;
    
    @FindBy(xpath="//input[@id='guest']")
    WebElement guestBoxInp;
    
    @FindBy(xpath="//div[@aria-disabled='false']")
    WebElement dateSelection;
    
    @FindBy(xpath="//button[@id='hsw_apply_changes_btn']")
    WebElement applyBtn;
    
    @FindBy(xpath="//*[@id='hsw_search_button']")
    WebElement searchBtn;
    
    @FindBy(xpath="//*[@class='DayPicker-Month'][1]")
    WebElement CheckindateClaneder;
    
    
    @FindBy(xpath="//*[@aria-disabled='false']")
    By DateSelectDiv;
    
    
    @FindBy(xpath="//*[@class='DayPicker-Month'][2]")
    WebElement CheckoutdateClaneder;
    
    @FindBy(xpath="//h1[@id='detpg_hotel_name']")
    WebElement hotelName;
    
    @FindBy(xpath="//label[@for='checkin']")
    WebElement checkinBox;
    
    @FindBy(xpath="//label[@for='checkout']")
    WebElement checkoutBox;
   
	WebDriverWait wait ;
	
	public SupportFunctions(WebDriver driver ) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, 5);
	}
 
		/*
		  * This function search the city
		  * select city from dropdown
		  * place- name of city that need to be selcted
		  * */
	public void addPlace(String place)
	{
		try {
			System.out.println("click on city search box");
			cityBox.click();
			System.out.println("clear city search box");
			cityTextInp.clear();
			System.out.println("Add city in search box");
			cityTextInp.sendKeys(place);
			wait.until(ExpectedConditions.visibilityOfAllElements(allElements));
			System.out.println("select city from search results");
			List<WebElement> search = allElements;
			search.get(0).click();
		} catch (Exception e) {
				System.out.println("error in addPlace "+e);
		}
	}
	
	/*
	  * This function clicks on search button
	  * */
	public void ClickonSearchBtn() {
		try {
			System.out.println("click on search button");
			searchBtn.click();
		} catch (Exception e) {
			System.out.println("error in ClickonSearchBtn "+e);
		}
	}
	
	/*
	  * This function clicks on Apply button
	  * */
	public void clickOnApply()
	{
		try {
			System.out.println("click on Apply button");
			applyBtn.click();
		} catch (Exception e) {
			System.out.println("error in clickOnApply "+e);
		}
	} 
	/*
	  * This function retive text from input sheet
	  * Sheet name- name of sheet where data going to be extracted 
	  * column- column number
	  * row- row number
	  * */
	public String retrivetextFromInputFile(String sheetname,int column,int row) throws BiffException, IOException
	{
		
		File Fr= new File(InputFilePath);
		FileInputStream fi = new FileInputStream(Fr);
		Workbook w= Workbook.getWorkbook(fi);
		Sheet s= w.getSheet(sheetname);
		Cell c= s.getCell(column, row);
		return c.getContents();
		
	}
	
	 /*
	  * This function select checkin date from calender
	  *date- date that is going to be selected
	  * */
	 public void selectCheckinDate(String date)
	 {
		 try {
				System.out.println("set cehckin date");
			 checkinBox.click();
			 WebElement dateWidgetFrom = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(CheckindateClaneder));
			 String dates=dateSelection.toString();
				
				System.out.println();
			 driver.findElement(By.xpath(StringUtils.chop(dates)+"[contains(@aria-label,'"+date+"')]")).click();
		} catch (Exception e) {
System.out.println("error in selectCheckinDate"+e);	
}
	 }
	 /*
	  * This function select Checkout date from calender
	  *date- date that is going to be selected
	  * */
	 public void selectCheckOutDate(String date)
	 {
		 try {
			 System.out.println("set cehckout date");
			 checkoutBox.click();
			 WebElement dateWidgetFrom = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(CheckoutdateClaneder));
			 String dates=dateSelection.toString();
			 driver.findElement(By.xpath(StringUtils.chop(dates)+"[contains(@aria-label,'"+date+"')]")).click();
			 
		} catch (Exception e) {
System.out.println("error in selectCheckOutDate"+e);	
}
	 }
	 /*
	  * This function select number of persons for the stay
	  *value- nummber of persons
	  * */
	 public void SelectNumberofPersons(String value)
	 {
		 try {
				System.out.println("set number of persons");
			 guestBoxInp.click();
			 driver.findElement(By.xpath("//ul[@data-cy='adultCount']//li[contains(text(),'"+value+"')]")).click();
			
		} catch (Exception e) {
			System.out.println("error in SelectNumberofPersons"+e);
		}
	 }
	 /*
	  * This function select Hotel 
	  *Hotelname- name of hotel
	  * */
	 public void ClikOnhotel(String Hotelname)
	 {
		 try {
				System.out.println("Click on selected hotel");
			driver.findElement(By.linkText(Hotelname)).click();
		} catch (Exception e) {
			System.err.println("error in ClikOnhotel"+e);
		}
	 }
	 /*
	  * This function retrive Hotelanme 
	  * */
	 public String getHotelName()
	 {
			System.out.println("retirive hotel name");
		 String Hotelname = null;
		 String parent= driver.getWindowHandle();
		 Set<String> s= driver.getWindowHandles();
		 Iterator<String>  ie= s.iterator();
		 while(ie.hasNext())
		 {
			try {
				 String childWindow= ie.next();
				 if(!parent.equals(childWindow))
				 {
					 driver.switchTo().window(childWindow);
					 Hotelname= hotelName.getText();
				 }
			} catch (Exception e) {
				System.out.println("error in hotel name retrive"+e);
			}
		 }
		 return Hotelname;
	 }
	 
	 
}
