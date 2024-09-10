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

    By uploadButton = By.xpath("//button[text()='Upload an image']");
    By uploadFile = By.xpath("//input[@type='file']");
    By imageFile = By.xpath("//img[@alt='input']");
    By uploadOption = By.id("upload");
    By uploadBox = By.xpath("//div[@class='uploadBoxContent']");
    By uploadStatus = By.className("Toastify");
    By errorMessage = By.id("error_message_id");
    By downloadButton = By.id("download_button_id");
    By upscalingStatus = By.id("upscaling_status_id");
    By imagePreview = By.id("image_preview_id");


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

    public boolean isUploadButtonDisplayed() {
        WebElement UploadButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(uploadButton));
        System.out.println(UploadButtonElement.isDisplayed());
        return UploadButtonElement.isDisplayed();
    }

    public boolean isUploadSuccessful() {
        WebElement imgElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(imageFile));
        String expectedFileName = "testimg.webp";
        String imgSrc = imgElement.getAttribute("src");
        imgSrc.contains(expectedFileName);
        System.out.println(imgSrc.contains(expectedFileName));
        return imgSrc.contains(expectedFileName);
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public boolean isUpscalingSuccessful() {
        return driver.findElement(upscalingStatus).isDisplayed();
    }

    public boolean isDownloadButtonDisplayed() {
        return driver.findElement(downloadButton).isDisplayed();
    }

    public void downloadImage() {
        WebElement downloadBtn = driver.findElement(downloadButton);
        if (downloadBtn.isDisplayed()) {
            downloadBtn.click();
        }
    }

    public boolean isImagePreviewDisplayed() {
        return driver.findElement(imagePreview).isDisplayed();
    }
}
