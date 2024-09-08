package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
\import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ImageUpscalerPage;
import utils.DriverFactory;

public class ImageUpscalerTests {
    WebDriver driver;
    ImageUpscalerPage page;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Set up Extent Reports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/extentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set up WebDriver
        DriverFactory factory = new DriverFactory();
        driver = factory.getDriver("chrome");  // Can be parameterized from config
        page = new ImageUpscalerPage(driver);
    }

    @Test
    public void testNavigationToWebsite() {
        test = extent.createTest("testNavigationToWebsite");
        page.navigateToUpscaler();
        assert page.isUploadButtonDisplayed() : "Upload button not displayed";
        test.pass("Upload button displayed correctly");
    }

    @Test(enabled = false)
    public void testValidFileUpload() {
        test = extent.createTest("testValidFileUpload");
        page.uploadImage("/path/to/valid_image.jpg");  // Replace with actual file path
        assert page.isUploadSuccessful() : "Valid file upload failed";
        test.pass("Valid image uploaded successfully");
    }

    @AfterClass
    public void teardown() {
        extent.flush();
        driver.quit();
    }
}
