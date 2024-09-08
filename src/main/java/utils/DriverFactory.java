package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup(); // WebDriverManager manages the ChromeDriver
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
           // driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); // WebDriverManager manages the GeckoDriver
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize(); // Maximize browser window
        return driver;
    }
}
