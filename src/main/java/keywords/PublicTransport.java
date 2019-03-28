package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicTransport {
  private static String home = "Móricz Zsigmond körtér";
  private static String workplace = "Andrássy út 66.";


  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    driver.get("https://www.google.com/maps/dir///@47.5115653,19.0587185,15z/data=!4m2!4m1!3e3");
    getPublicRoute(driver);
  }
  public static void setRoute(WebDriver driver){
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div/input"))).sendKeys(home);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div/input"))).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div/div/input"))).sendKeys(workplace);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div/div/input"))).sendKeys(Keys.ENTER);
  }

  public static String getPublicRoute(WebDriver driver){
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    setRoute(driver);
    String firstOpportunity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[5]/div[1]/div[2]/div[2]/h1/span[2]/span[1]"))).getText();
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
    Date now = new Date();
    String currentTime = sdfTime.format(now);
    String[] current = currentTime.split(":");
    int currentMinutes = Integer.parseInt(current[1]);
    String[] first = firstOpportunity.split(":");
    int firstMinutes = Integer.parseInt(first[1]);
    System.out.println(firstMinutes - currentMinutes);
    return "yes";
  }
}
