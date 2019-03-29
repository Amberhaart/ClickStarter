package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrainSchedule {
  private static WebDriverWait webDriverWait;
  private static String url = "http://elvira.mav-start.hu/";

  private static WebElement whereFromField;
  private static WebElement whereToField;
  private static WebElement submitBtn;
  private static String whereFrom = "Rákoscsaba";
  private static String whereTo = "Budapest-Keleti";

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

  public static int getCurrentTime() {
    String currentTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    String time = currentTime.replace(":", "");
    int timeNumber = Integer.parseInt(time);
    return timeNumber;
  }

  public static ArrayList<Integer> getDepartures(WebDriver driver) {
    searchFromSchedule(driver, whereFrom, whereTo);

    List<WebElement> listOfWebelements = driver.findElements(By.className("l"));
    ArrayList listOfDepartures = new ArrayList();
    int i;
    for (i = 0; i < listOfWebelements.size(); i++) {
      if (i % 2 == 0) {
        listOfDepartures.add(Integer.parseInt(listOfWebelements.get(i).getText().replace(":", "")));
      }
    }
    return listOfDepartures;
  }

  public static String getRecommendedTime(WebDriver driver) {
    ArrayList<Integer> list = getDepartures(driver);
    Integer smallestBigger = new Integer(0);
    int i;
    for (i = 0; i < list.size(); i++) {
      if (getCurrentTime() < list.get(i)) {
        smallestBigger = list.get(i);
        break;
        }
    }
    int min = smallestBigger % 100;
    int hour = smallestBigger / 100;
    return "A következő vonat " + hour + " óra " + min + " perckor indul " + whereFrom + " vasútállomásról a "
            + whereTo + " pályaudvarra.";
  }

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    System.out.println(getRecommendedTime(driver));
  }
}
