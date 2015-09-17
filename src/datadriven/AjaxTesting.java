package datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AjaxTesting {
     WebDriver driver=new FirefoxDriver();
     
	@BeforeMethod
	public void setpage()
	{
		driver.get("https://care.ideacellular.com/wps/portal/account/online-recharge");
	}
	@Test
	public void testAjax() throws IOException 
	{
		FileInputStream fi=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\Idea Ajax testdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheet("Sheet1");
		Iterator<Row>rows=ws.iterator();
		rows.next();
		while(rows.hasNext())
		{
			Row r=rows.next();
			driver.findElement(By.name("attrMobileNumber")).clear();
			driver.findElement(By.name("attrMobileNumber")).sendKeys(r.getCell(0).getStringCellValue());
			driver.findElement(By.xpath("//*[@id='billPayForm']")).click();
			WebElement Ajax=driver.findElement(By.xpath("//*[@id='errorHolder']/label"));
			String actAjax=null;
			if(Ajax.getText().isEmpty())
			{
				actAjax="No ajax";
			}
			else
			{
				actAjax="Please enter mobile number";
			}
			r.createCell(2).setCellValue(actAjax);
			String expAjax=r.getCell(1).getStringCellValue();
			if(actAjax.equals(expAjax))
			{
				r.createCell(3).setCellValue("passed");
			}
		
			else
			{
				r.createCell(3).setCellValue("failed");
			}
		}
		FileOutputStream fo=new FileOutputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\resultexcelfiles\\resultAjaxTest.xlsx");
		wb.write(fo);
		fo.close();
		
	}
	@AfterMethod
	public void closebrwsr()
	{
		driver.quit();
	}
}
