package keywords;

import configuration.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainSchedule {
  public static WebDriverWait webDriverWait;
  public static String url = "http://elvira.mav-start.hu/";

  public static void goToTrainSchedulPage(WebDriver driver, String url) {
    webDriverWait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.manage().window().maximize();
    driver.get(url);
  }

  public static void serachFromSchedule(WebDriver driver) {

  }


}
