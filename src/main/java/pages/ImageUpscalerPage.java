package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ImageUpscalerPage {
    WebDriver driver;

    By pageHeading = By.xpath("/html/body/div[1]/div[2]/section[2]/div/div/div[1]/div[1]/h1/span");
    By uploadFile = By.xpath("//input[@type='file']");
    By imageFile = By.xpath("//img[@alt='input']");
    By uploadBox = By.xpath("//div[@class='uploadBoxContent']");
    By downloadButton = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div/div[2]/div/div[1]/div[2]/button[1]");
    By imagePreview = By.className("__rcs-handle-button");
    By ProcessButton = By.xpath("//button[text()='Process']");
    By uploadOption = By.xpath("//label[@for='upload']");

    public ImageUpscalerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToUpscaler() {
        driver.get("https://www.spyne.ai/image-upscaler");
    }

    public void navigateToUpscalerUpload() {
        driver.get("https://www.spyne.ai/image-upscaler/upload");
    }

    public void uploadImage(String filePath) {
        WebElement UploadFileElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(uploadFile));
        UploadFileElement.sendKeys(filePath);
    }

    public boolean isPageHeadingDisplayed() {
        WebElement UploadButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(pageHeading));
        return UploadButtonElement.isDisplayed();
    }

    public boolean isUploadButtonDisplayed() {
        WebElement UploadButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(uploadOption));
        return UploadButtonElement.isDisplayed();
    }

    public boolean isUploadSuccessful() {
        WebElement imgElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(imageFile));
        String expectedFileName = "testimg.webp";
        String imgSrc = imgElement.getAttribute("src");
        imgSrc.contains(expectedFileName);
        return imgSrc.contains(expectedFileName);
    }

    public void startProcessing() {
        WebElement ProcessButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(ProcessButton));
        ProcessButtonElement.click();
        System.out.println("Clicked on Process");
    }

    public boolean isUpscalingSuccessful() {
        WebElement ImagePreviewElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(imagePreview));
        System.out.println("waiting...");
        return ImagePreviewElement.isDisplayed();
    }

    public boolean isDownloadButtonDisplayed() {
        WebElement DownloadButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(downloadButton));
        return DownloadButtonElement.isDisplayed();
    }

    public void downloadImage() {
        WebElement DownloadButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(downloadButton));
        if (DownloadButtonElement.isDisplayed()) {
            DownloadButtonElement.click();
        }
    }

    public boolean isImagePreviewDisplayed() {
        WebElement ImagePreviewElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(imagePreview));
        return ImagePreviewElement.isDisplayed();
    }
}
