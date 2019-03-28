package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nameday {

  public static String nameday = "";

  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver;
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("permissions.default.desktop-notification", 1);
    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    driver = new FirefoxDriver(capabilities);
    driver.manage().window().maximize();

    wishHappyNameDayOnFacebook(driver);

  }

  public static void logIntoFacebook(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.get("https://www.facebook.com/");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(Config.userName);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(Config.password);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton"))).click();
  }

  public static void postOnFacebook(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    Actions actions = new Actions(driver);
    logIntoFacebook(driver);

    WebElement post = wait.until(ExpectedConditions.elementToBeClickable(By.className("_sg1")));
    actions.moveToElement(post).perform();
    post.click();

    WebElement postBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div[3]/div/div/div[2]/div[1]/div/div/div/div[2]/div/div[1]/div/div[1]/div[1]/div[2]/div/div/div/div/div/div/div[2]/div")));
    actions.moveToElement(postBox).perform();
    postBox.click();
    postBox.sendKeys("Minden kedves " + nameday + " nevű ismerősömnek Boldog Névnapot kívánok!");

    WebElement shareButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/div[3]/div/div/div[2]/div[1]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/button")));
    shareButton.click();
  }

  public static void getNameday(WebDriver driver) {
    driver.get("http://mainevnap.hu/");
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    nameday = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[3]/table/tbody/tr/td/div/table/tbody/tr[1]/td[2]/span[1]"))).getText();
  }

  public static void wishHappyNameDayOnFacebook(WebDriver driver) {
    getNameday(driver);
    postOnFacebook(driver);
  }
}
