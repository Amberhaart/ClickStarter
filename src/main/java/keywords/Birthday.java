package keywords;

import configuration.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;

public class Birthday {

  public static void waitForLoad() {
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }
  }
  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver;
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("permissions.default.desktop-notification", 1);
    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    driver = new FirefoxDriver(capabilities);
    driver.manage().window().maximize();

    logIntoFacebook(driver);
    checkBirthdays(driver);
  }

  public static void logIntoFacebook(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    driver.get("https://www.facebook.com/");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(Config.userName);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(Config.password);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton"))).click();
  }

  public static void checkBirthdays(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeOutInSeconds);
    Actions actions = new Actions(driver);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navItem_2344061033\"]/a"))).click();
    waitForLoad();

    WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"u_jsonp_2_1\"]")));
    actions.moveToElement(tab).perform();
    tab.click();

    WebElement textBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("u_jsonp_3_8")));
    textBox.click();
    textBox.sendKeys("Boldog Születésnapot! :)");
    textBox.sendKeys(Keys.RETURN);
  }
}
