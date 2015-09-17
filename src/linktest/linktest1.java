package linktest;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class linktest1 {
	FirefoxDriver driver;
	@BeforeMethod
	public void setbrows()
	{
		ProfilesIni pr=new ProfilesIni();
		FirefoxProfile fp=pr.getProfile("himu");
		driver=new FirefoxDriver(fp);
		driver.get("http://google.co.in");
	}
  @Test
  public void linktests()
  {
	driver.findElement(By.linkText("Gmail")).click();
  }
}
