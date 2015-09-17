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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WriteLinks {
	WebDriver driver=new FirefoxDriver();
@BeforeMethod
public void setbrws()
{
	
	driver.get("http://newtours.demoaut.com");
}
@Test
public void linktest() throws IOException
{
	FileInputStream fi=new FileInputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\excelfiles\\links.xlsx");
	XSSFWorkbook wb=new XSSFWorkbook(fi);
	XSSFSheet ws=wb.getSheet("sheet1");
	Row r=null;
	List<WebElement>links=driver.findElements(By.tagName("a"));
	for(int i=0;i<links.size();i++)
	{
		r=ws.createRow(i);
		r.createCell(0).setCellValue(links.get(i).getText());
	}
	
	FileOutputStream fo=new FileOutputStream("F:\\Project\\toolsqa\\src\\com\\toolsqa\\resultexcelfiles\\linksresult.xlsx");
    wb.write(fo);
    fo.close();

}
}
