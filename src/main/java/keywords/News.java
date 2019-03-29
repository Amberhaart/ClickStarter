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
  private static final String newsUrl = "https://hvg.hu/";
  private static final String translateUrl = "https://translate.google.com/?hl=hu";

  public static WebElement getMainNewsContainer(WebDriver driver) {
    driver.get(newsUrl);
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"cimlap-grid-elem elem-375 panelgap\"]")));
  }

  public static Long getHeadLineCounter(WebDriver driver){
    getMainNewsContainer(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return (Long) js.executeScript (" return document.getElementsByClassName(\"cimlap-grid-elem\")[1].childElementCount");
  }

  public static String getHeadline(WebDriver driver, int numberOfHeadline) {
    getMainNewsContainer(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String headlines = (String) js.executeScript (" return document.getElementsByClassName(\"cimlap-grid-elem\")[1].children[" + numberOfHeadline + "].innerText");
    return headlines;
  }

  public static void readItOutLoud(WebDriver driver, String textToRead, int time){
    driver.get(translateUrl);
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id=\"source\"]"))).sendKeys(textToRead);
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
    }
    WebElement loudBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[5]/div[3]/div[2]")));
    loudBtn.click();
    try {
      Thread.sleep(time);
    } catch (Exception e) {
    }
  }
  

  public static void readAllNewsOutWithWait(WebDriver driver){
    for (int i = 0; i < getHeadLineCounter(driver); i++){
      readItOutLoud(driver, getHeadline(driver, i), 7000);
    }
  }

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    readAllNewsOutWithWait(driver);
  }
}
