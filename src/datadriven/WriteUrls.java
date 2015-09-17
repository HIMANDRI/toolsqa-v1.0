package datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WriteUrls {
WebDriver driver=new FirefoxDriver();
@BeforeMethod
public void setpage()
{
	driver.get("http://newtours.demoaut.com/");
	
}
@Test
public void setexpurl() throws IOException
{
	FileInputStream fi=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\expresult.xlsx");
	XSSFWorkbook wb=new XSSFWorkbook(fi);
	XSSFSheet ws=wb.getSheet("sheet1");
	Row r=null;
	List<WebElement>links=driver.findElements(By.tagName("a"));
	for(int i=0;i<links.size();i++)
	{
		r=ws.createRow(i);
		links.get(i).click();
		String url=driver.getCurrentUrl();
		r.createCell(1).setCellValue(url);
		driver.navigate().back();
		links=driver.findElements(By.tagName("a"));
	}
	FileOutputStream fo=new FileOutputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\resultexcelfiles\\urlresult.xlsx");
    wb.write(fo);
    fo.close();


}
}
