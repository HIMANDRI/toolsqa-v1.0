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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Datadriventesting2 {
	WebDriver driver=new FirefoxDriver();
	@BeforeMethod
	public void setpage()
	{
		driver.get("http://newtours.demoaut.com/");
		
	}
	@Test
	public void setexpurl() throws IOException
	{
		FileInputStream fi=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\testlinks.xlsx");
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
				if(acturl.equals(expurl))
				{
					r.createCell(3).setCellValue("Passed");
				}
				driver.navigate().back();
			  }
				
				
			catch(Exception e)	
			{
				
					r.createCell(3).setCellValue("failed");
			}
			}
			FileOutputStream fo=new FileOutputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\resultexcelfiles\\linktest2result.xlsx");
			wb.write(fo);
			fo.close();
		
		
	 }	
	
		
		
	
}	


