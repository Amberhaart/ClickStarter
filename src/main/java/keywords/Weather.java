package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Weather {
  public static WebDriverWait webDriverWait;
  public static String url = "https://www.idokep.hu/elorejelzes/Budapest";
  public static WebElement dailyForecastLeft;
  public static WebElement dailyForecastRight;

  public static void goToWeatherPage(WebDriver driver, String url) {
    webDriverWait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.manage().window().maximize();
    driver.get(url);
  }

  public static String getWeatherForecastText(WebDriver driver) {
    goToWeatherPage(driver, url);
    dailyForecastLeft = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("bal")));
    dailyForecastRight = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("jobb")));
    return dailyForecastLeft.getText() + dailyForecastRight.getText();
  }

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    System.out.println(getWeatherForecastText(driver));
    driver.quit();
  }
}
