package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrainSchedule {
  public static WebDriverWait webDriverWait;
  public static String url = "http://elvira.mav-start.hu/";

  public static WebElement whereFromField;
  public static WebElement whereToField;
  public static WebElement submitBtn;
  public static String whereFrom = "Rákoshegy";
  public static String whereTo = "Budapest-Keleti";

  public static void goToTrainSchedulePage(WebDriver driver, String url) {
    webDriverWait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.manage().window().maximize();
    driver.get(url);
  }

  public static void searchFromSchedule(WebDriver driver, String whereFrom, String whereTo) {
    goToTrainSchedulePage(driver, url);
    whereFromField = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("i")));
    whereFromField.sendKeys(whereFrom);

    whereToField = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("e")));
    whereToField.sendKeys(whereTo);

    submitBtn = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("go")));
    submitBtn.click();
  }

  public static String getCurrentTime() {
    Date date = new Date();
    String currentTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    return currentTime;
  }

  public static List<WebElement> getDepartures(WebDriver driver) {
    searchFromSchedule(driver, whereFrom, whereTo);
    int i;
    List<WebElement> listOfDeps = driver.findElements(By.className("l"));
    for (i = 0; i < listOfDeps.size(); i++) {
      if (i % 2 == 0) {
        System.out.println(listOfDeps.get(i).getText());
      }
    }
    return listOfDeps;
  }

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    System.out.println(getDepartures(driver));
    System.out.println(getCurrentTime());
  }
}
