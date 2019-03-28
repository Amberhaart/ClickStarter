package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nameday {
  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    logIntoFacebook(driver);
  }

  public static void logIntoFacebook(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.get("https://www.facebook.com/");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(Config.userName);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(Config.password);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton"))).click();
  }
}
