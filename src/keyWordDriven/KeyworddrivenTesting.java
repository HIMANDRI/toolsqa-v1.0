package keyWordDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeyworddrivenTesting {
	FirefoxDriver driver;
	@BeforeMethod
	public void setup()
	{
		driver=new FirefoxDriver();
		driver.get("http://gmail.com");
	}
	@Test
	public void loginText() throws IOException, InterruptedException
	{
		FileInputStream f=new FileInputStream("F:\\Project\\toolsqa\\login.properties");
		Properties pr=new Properties();
		pr.load(f);
		driver.findElement(By.id(pr.getProperty("txt_username"))).sendKeys("mallickhimu");
		Thread.sleep(1000);
		driver.findElement(By.id(pr.getProperty("btn_nxt"))).click();
		driver.findElement(By.id(pr.getProperty("txt_pwd"))).sendKeys("123456");
		driver.findElement(By.id(pr.getProperty("btn_signIn"))).click();
		
		
	}

}
