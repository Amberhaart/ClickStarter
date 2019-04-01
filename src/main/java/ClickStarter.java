import configuration.Config;
import keywords.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ClickStarter {
  public static void main(String[] args) {
    System.setProperty(Config.webDriverName, Config.webDriverPath);
    WebDriver driver;
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("permissions.default.desktop-notification", 1);
    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    driver = new FirefoxDriver(capabilities);
    driver.manage().window().maximize();

    Nameday.wishHappyNameDayOnFacebook(driver);
    driver.navigate().refresh();
    Birthday.checkBirthdays(driver);
    Birthday.wishHappyBirthday(driver);
    News.readItOutLoud(driver, "Jó reggelt, a mai nap időjárása következik:", 5000);
    News.readItOutLoud(driver, Weather.getWeatherForecastText(driver), 25000);
    News.readItOutLoud(driver, "A mai nap főbb hírei:", 5000);
    News.readAllNewsOutWithWait(driver);
    PublicTransport.setRoute(driver, PublicTransport.home, PublicTransport.workplace);
    News.readItOutLoud(driver, PublicTransport.getPublicRoute(driver), 20000);
    News.readItOutLoud(driver, TrainSchedule.getRecommendedTime(driver), 10000);
    News.readItOutLoud(driver, "Szép napot, sok sikert mára!", 5000);
    driver.quit();
  }
}
