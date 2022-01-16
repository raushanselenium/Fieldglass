package pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.DateFormatter;
import utilities.XLUtility;

public class TimesheetsPage {

	WebDriver driver;

	public TimesheetsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='filterStartDate']")
	WebElement startPeriod;

	@FindBy(xpath = "//select[@name='month']")
	WebElement dropDown_from_month;

	@FindBy(xpath = "//select[@name='year']")
	WebElement dropDown_from_year;
	
	@FindBy(xpath="//input[@name='timeSheet_worker_list_search']")
	WebElement btnApplyFilter;

	@FindBy(xpath = "//table[@class='calendarDates']/tbody/tr[2]/td")
	List<WebElement> from_date_cols;

	@FindBy(xpath = "//table[@class='calendarDates']/tbody/tr")
	List<WebElement> from_date_rows;
	
	@FindBy(xpath = "//input[@id='filterEndDate']")
	WebElement endPeriod;

	@FindBy(xpath = "//div[@role='row']")
	List<WebElement> rows;

	@FindBy(xpath = "//div[@role='row'][1]//div[contains(@class,'jqx-grid-cell-')]")
	List<WebElement> cols;

	public int getNoOfRows() {
		return (rows.size());
	}

	public int getNoOfCols() {
		return (cols.size());
	}

	public void setDataInExcel(String sheetName, XLUtility xlutil) throws IOException {
		int r = getNoOfRows();
		int col = 0, colh = 0;
		List<WebElement> headers = driver.findElements(By.xpath("//div[@role='columnheader']//span"));
		for (WebElement header : headers) {
			xlutil.setCellData(sheetName, 0, colh, header.getText());
			colh++;
		}

		for (int i = 1; i < r; i++) {
			List<WebElement> values = driver.findElements(By.xpath("//div[@role='row'][" + i
					+ "]//div[starts-with(@class,'jqx-grid-cell-') and contains(@class,'-align')]"));
			for (WebElement value : values) {
				xlutil.setCellData(sheetName, i, col, value.getText());
				col++;
			}
			col = 0;
		}
	}

	public void setFromDate(String rday, String month, String year) 
	{
		boolean b = false;
		startPeriod.click();
		int mon=Integer.parseInt(month)-1;
		Select from_month = new Select(dropDown_from_month);
		from_month.selectByIndex(mon);

		Select from_year = new Select(dropDown_from_year);
		from_year.selectByVisibleText(year);

		for (int r = 2; r < from_date_rows.size(); r++) 
		{
			for (int c = 1; c <= from_date_cols.size(); c++) 
			{
				WebElement day = driver
						.findElement(By.xpath("//table[@class='calendarDates']/tbody/tr[" + r + "]/td[" + c + "]"));
				String reqD = day.getText().trim();
				if (reqD.equals(rday)) {
					day.click();
					b = true;
					break;
				}
			}
			if (b)
				break;
		}

	}
	
	public void setToDate(String rday, String month, String year) 
	{
		boolean b = false;
		endPeriod.click();
		int mon=Integer.parseInt(month)-1;
		Select from_month = new Select(dropDown_from_month);
		from_month.selectByIndex(mon);

		Select from_year = new Select(dropDown_from_year);
		from_year.selectByVisibleText(year);

		for (int r = 2; r < from_date_rows.size(); r++) 
		{
			for (int c = 1; c <= from_date_cols.size(); c++) 
			{
				WebElement day = driver
						.findElement(By.xpath("//table[@class='calendarDates']/tbody/tr[" + r + "]/td[" + c + "]"));
				String reqD = day.getText().trim();
				if (reqD.equals(rday)) {
					day.click();
					b = true;
					break;
				}
			}
			if (b)
				break;
		}

	}
	
	public void clickApplyFilter()
	{
		btnApplyFilter.click();
	}
	

}
