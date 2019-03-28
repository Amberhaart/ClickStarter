package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class News {
  public static WebElement getMainNewsContainer(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"cimlap-grid-elem elem-375 panelgap\"]")));
  }

  public static String getHeadlines(WebDriver driver) {
    getMainNewsContainer(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String headlines = (String) js.executeScript (" return document.getElementsByClassName(\"cimlap-grid-elem\")[1].textContent");
    System.out.println(headlines);
    return headlines;
  }

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    driver.get("https://hvg.hu/");
    getHeadlines(driver);
  }
}
