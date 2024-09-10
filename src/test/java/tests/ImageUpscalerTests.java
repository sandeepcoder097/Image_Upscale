package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ImageUpscalerPage;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.time.Duration;

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

    @Test(enabled = true)
    public void testNavigationToWebsite() {
        test = extent.createTest("testNavigationToWebsite");
        page.navigateToUpscaler();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        assert page.isUploadButtonDisplayed() : "Upload button not displayed";
        test.pass("Upload button displayed correctly");

    }

    @Test(enabled = true)
    public void testValidFileUpload() {
        test = extent.createTest("testValidFileUpload");
        page.navigateToUpscalerUpload();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        page.uploadImage("/Users/sandeepsharma/IdeaProjects/SpyneUpscalerAutomation/src/main/resources/testimg.webp");  // Replace with actual file path
        assert page.isUploadSuccessful() : "Valid file upload failed";
        test.pass("Valid image uploaded successfully");
    }

    // 3: Invalid File Upload
    @Test(priority = 3, enabled = true)
    public void testInvalidFileUpload() {
        test = extent.createTest("testInValidFileUpload");
        page.uploadImage("resources/test.txt");  // Replace with actual file path
        Assert.assertTrue(page.isErrorMessageDisplayed(), "Error message not displayed for invalid file");
        test.pass("Invalid File not getting uploaded");
    }

    // 4: Image Upscaling Process
    @Test(priority = 4, enabled = true)
    public void testImageUpscalingProcess() {
        test = extent.createTest("testImageUpscalingProcess");
        page.uploadImage("resources/testimg.webp");
        Assert.assertTrue(page.isUpscalingSuccessful(), "Image upscaling process did not complete successfully");
        test.pass("Image Upscaling process completed");
    }

    // 5: UI Validation (Check Upload, Preview, and Download buttons)
    @Test(priority = 5, enabled = false)
    public void testUIValidation() {
        test = extent.createTest("testUIValidation");
        Assert.assertTrue(page.isUploadButtonDisplayed(), "Upload button is not displayed");
        Assert.assertTrue(page.isImagePreviewDisplayed(), "Image preview is not displayed");
        Assert.assertTrue(page.isDownloadButtonDisplayed(), "Download button is not displayed");
        test.pass("UI Elements are displaying properly");

    }

    // 6: Download Functionality
    @Test(priority = 6, enabled = false)
    public void testDownloadFunctionality() {
        test = extent.createTest("testDownloadFunctionality");
        page.uploadImage("resources/testimg.webp");
        Assert.assertTrue(page.isUpscalingSuccessful(), "Upscaling did not complete");
        page.downloadImage();  // Trigger the download
        test.pass("Download Successful");
        // Optionally, check if the file is actually downloaded to a specific directory
    }

    // 7: Performance Testing (Optional)
    @Test(priority = 7, enabled = false)
    public void testPerformanceForUpscaling() {
        test = extent.createTest("testPerformanceForUpscaling");
        long startTime = System.currentTimeMillis();
        page.uploadImage("resources/testimg.webp");  // Large image file for performance test
        Assert.assertTrue(page.isUpscalingSuccessful(), "Image upscaling process did not complete successfully");

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        Assert.assertTrue(totalTime < 30000, "Image upscaling took too long (>30 seconds)");
        test.pass("Performance upto the mark!");
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
            test.fail("Test failed").addScreenCaptureFromPath(screenshotPath);  // Attach screenshot to Extent Report
            test.fail(result.getThrowable());  // Log the exception
        }
    }

    /*
    @AfterMethod
    public void clearCache() {
        System.out.println("Clearing Cache...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("windows.localStorage.clear();");
        js.executeScript("windows.sessionStorage.clear()");
        driver.manage().deleteAllCookies();
        System.out.println("Cache cleared");
    }*/

    @AfterClass
    public void teardown() {
        extent.flush();
        driver.quit();
    }
}
