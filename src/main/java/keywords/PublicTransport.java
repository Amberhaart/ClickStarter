package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicTransport {
  private static String home = "Bertalan Lajos u., Budapest, 1111";
  private static String workplace = "Andrássy út 66.";


  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver = new FirefoxDriver();
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
    driver.get("https://www.google.com/maps/dir///@47.5115653,19.0587185,15z/data=!4m2!4m1!3e3");
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    String firstType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[2]/div/div[2]/span[1]/span[1]/img"))).getAttribute("alt");
    String firstNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[2]/div/div[2]/span[1]/span[2]/span[1]/span[1]/span"))).getText();
    setRoute(driver);
    getPublicRoute(driver, firstType, firstNumber);
  }

  public static void setRoute(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div/input"))).sendKeys(home);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div/input"))).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div/div/input"))).sendKeys(workplace);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div/div/input"))).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[5]/div[1]/div[2]/div[2]/div[4]/button"))).click();
  }

  public static String getNumberAndType(WebDriver driver, String type, String number) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    if (type.equals("Gyalog")) {
      return "Gyalog menj";
    }
    return "A járatod pedig a " + number + " os " + type;
  }

  public static String getPublicRoute(WebDriver driver, String type, String number) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);

    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
    Date now = new Date();
    String currentTime = sdfTime.format(now);
    //String[] current = currentTime.split(":");
    //int currentMinutes = Integer.parseInt(current[1]);
    String fastestRoute;
    String firstAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[1]/h1/span[2]/span[3]/span"))).getText();
    String firstTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[1]/h1/span[2]/span[1]"))).getText();
    String frequency = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[2]/div/div[3]/span[3]"))).getText();
    fastestRoute = "A pontos idő: " + currentTime + ". A leggyorsabb útvonalon " + firstAmount + " alatt érsz oda, és "
            + firstTime + " kor indul. " + getNumberAndType(driver, type, number) + ". Ezután " + frequency + " jár.";
    System.out.println(fastestRoute);
    return fastestRoute;
  }
}
/*metró
/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[5]/div[1]/div[2]/div[2]/div[2]/span[3]/span[2]/span[1]/span[1]/span
busz
/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[5]/div[1]/div[2]/div[2]/div[2]/span[3]/span[2]/span[1]/span[1]/span*/