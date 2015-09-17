/**
 * 
 */
package datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author HIMU
 *
 */
public class DatadrivenTesting {
	FirefoxDriver driver;
	@BeforeMethod
	public void setupBrowser() throws InterruptedException
	{
		ProfilesIni pr=new ProfilesIni();
		FirefoxProfile fp=pr.getProfile("himu");
		driver=new FirefoxDriver(fp);
		driver.get("http://newtours.demoaut.com/");
		Thread.sleep(1000);
	}
	@Test
	public void linktest() throws IOException     
	{
		
			FileInputStream fi=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\linktest.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				XSSFSheet ws=wb.getSheet("sheet1");
				Iterator<Row>rows=ws.iterator();
				rows.next();
				while(rows.hasNext())
				{
					Row r=rows.next();
					String linkname=r.getCell(0).getStringCellValue();
			 try{
				 driver.findElement(By.linkText(linkname)).click();
			 
				String acturl=driver.getCurrentUrl();
				r.createCell(2).setCellValue(acturl);
				String expurl=r.getCell(1).getStringCellValue();
				if(acturl.contains(expurl))
				{
					r.createCell(3).setCellValue("passed");
				}
				
				driver.navigate().back();
			 }	
			 catch(Exception e)
			 {
				 r.createCell(3).setCellValue("link not found");
			 }
			     }
				FileOutputStream fo=new FileOutputStream("C:\\Users\\HIMU\\Desktop\\resultlinktest.xlsx");
				wb.write(fo);
				fo.close();
	}
		@AfterTest
		public void doquit()
		{
			driver.quit();
		}
			
		
	}


