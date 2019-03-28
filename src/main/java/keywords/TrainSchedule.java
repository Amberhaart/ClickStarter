package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainSchedule {
  public static WebDriverWait webDriverWait;
  public static String url = "http://elvira.mav-start.hu/";

  public static WebElement whereFromField;
  public static WebElement whereToField;
  public static WebElement submitBtn;

  public static void goToTrainSchedulPage(WebDriver driver, String url) {
    webDriverWait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.manage().window().maximize();
    driver.get(url);
  }

  public static void searchFromSchedule(WebDriver driver, String whereFrom, String whereTo) {
    goToTrainSchedulPage(driver, url);
    whereFromField = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("i")));
    whereFromField.sendKeys(whereFrom);

    whereToField = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("e")));
    whereToField.sendKeys(whereTo);

    submitBtn = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("go")));
    submitBtn.click();
  }

  public static void selectTime() {

  }

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    System.out.println((driver));
    driver.quit();
  }
}
