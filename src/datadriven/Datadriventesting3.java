/**
 * 
 */
package datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author HIMU
 *
 */
public class Datadriventesting3 {

	WebDriver driver=new FirefoxDriver();
	@BeforeMethod
	public void setpage()
	{
		driver.get("http://newtours.demoaut.com/");
		driver.findElement(By.linkText("REGISTER")).click();
		driver.findElement(By.name("country"));
	}
	@Test
	public void testDrop() throws IOException
	{
		FileInputStream f1=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\dropdown.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(f1);
		XSSFSheet ws=wb.getSheet("sheet1");
		
		//Iterator<Row> rows=ws.iterator();
		//rows.next();
		Row r=null;
		
		List<WebElement>drops=driver.findElements(By.tagName("option"));
		for(int i=0;i<(drops.size());i++)
		{
			//Row r=rows.next();
			r=ws.createRow(i);
			r.createCell(0).setCellValue(drops.get(i).getText());
			drops.get(i).click();
			if(drops.get(i).isSelected())
			{
				
				r.createCell(1).setCellValue("true");
			}
			else
				
			{
				r.createCell(1).setCellValue("false");
			}
			
		}
		FileOutputStream fo=new FileOutputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\resultexcelfiles\\resultdrop.xlsx");
		wb.write(fo);
		fo.close();
		
	}
	@AfterMethod
	public void terminate()
	{
		driver.quit();
	}
}
