package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import support.SupportFunctions;

public class testCase1 extends baseClass

{
	SupportFunctions supportobj;

	@Test
	public void checkAvailableHotelHotel() throws BiffException, IOException
	{
		System.out.println("start");
		supportobj = new SupportFunctions(driver);
		String Place= supportobj.retrivetextFromInputFile(Sheetname, 0, 1);
		String checkinDate=supportobj.retrivetextFromInputFile(Sheetname, 1, 1);
		String checkoutDate=supportobj.retrivetextFromInputFile(Sheetname, 2, 1);
		String guests= supportobj.retrivetextFromInputFile(Sheetname, 3, 1);
		String HotelName=supportobj.retrivetextFromInputFile(Sheetname, 4, 1);
		supportobj.addPlace(Place);
		supportobj.selectCheckinDate(checkinDate);
		supportobj.selectCheckOutDate(checkoutDate);
		supportobj.SelectNumberofPersons(guests);
		supportobj.clickOnApply();
		supportobj.ClickonSearchBtn();
		supportobj.ClikOnhotel(HotelName);
		Assert.assertEquals(HotelName, supportobj.getHotelName());
		
		
		
	}

}
