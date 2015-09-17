package datadriven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dropdowntest4 {
	WebDriver driver=new FirefoxDriver();
	@BeforeMethod
	public void setpage()
	{
		driver.get("http://newtours.demoaut.com/");
		driver.findElement(By.linkText("REGISTER")).click();
		
	}
	@Test
	public void testDrop() throws IOException
	{
		FileInputStream f1=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\dropdown4.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(f1);
		XSSFSheet ws=wb.getSheet("sheet1");
		Iterator<Row>rows=ws.iterator();
		rows.next();
		while(rows.hasNext())
		{
			Row r=rows.next();
			String drop=r.getCell(0).getStringCellValue();
		    driver.findElement(By.name("country")).sendKeys(drop);
		    r.createCell(1).setCellValue("Passed");
		    
		}
		FileOutputStream fo=new FileOutputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\resultexcelfiles\\resultdrop1.xlsx");
		wb.write(fo);
		fo.close();
}
}
