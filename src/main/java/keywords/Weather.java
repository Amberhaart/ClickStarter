package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Weather {
  public static WebDriverWait webDriverWait;
  public static String url = "https://www.idokep.hu/elorejelzes/Budapest";

  public static void goToWeatherPage(WebDriver driver, String url) {
    webDriverWait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.manage().window().maximize();
    driver.get(url);
  }

  public static String getWeatherForecastText(WebDriver driver) {
    goToWeatherPage(driver, url);
    return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("bal"))).getText()
        + webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("jobb"))).getText();
  }
}
