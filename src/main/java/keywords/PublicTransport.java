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
  public static String home = "Gőzmalom utca";
  public static String workplace = "Andrássy út 66.";

  public static void setRoute(WebDriver driver, String home, String workplace) {
    driver.get("https://www.google.com/maps/dir///@47.5115653,19.0587185,15z/data=!4m2!4m1!3e3");
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div/input"))).sendKeys(home);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div/div/input"))).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div/div/input"))).sendKeys(workplace);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div/div/input"))).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[5]/div[1]/div[2]/div[2]/div[4]/button"))).click();
  }

  public static String getPublicRoute(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
    Date now = new Date();
    String currentTime = sdfTime.format(now);
    String fastestRoute;
    String firstAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[1]/h1/span[2]/span[3]/span"))).getText();
    String firstTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[1]/h1/span[2]/span[1]"))).getText();
    String frequency = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[2]/div/div[3]/span[3]"))).getText();
    String numbers = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/jsl/div[3]/div[7]/div[9]/div/div[1]/div/div/div[3]/div[2]/div/div[2]"))).getAttribute("innerText");
    fastestRoute = "A pontos idő: " + currentTime + ". A leggyorsabb útvonalon " + firstAmount + " alatt érsz a munkahelyedre, és a következő járat "
            + firstTime + " kor indul. Ezeket a járatokat használd: " + numbers + ". Ezután " + frequency + " jár.";
    return fastestRoute;
  }
}