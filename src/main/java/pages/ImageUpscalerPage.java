package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ImageUpscalerPage {
    WebDriver driver;

    By uploadButton = By.xpath("/html/body/div[1]/div[2]/section[2]/div/div/div[1]/div[2]/button");  // Adjust with actual locators
    By uploadStatus = By.id("upload_status_id");
    By errorMessage = By.id("error_message_id");
    By downloadButton = By.id("download_button_id");
    By upscalingStatus = By.id("upscaling_status_id");

    public ImageUpscalerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToUpscaler() {
        driver.get("https://www.spyne.ai/image-upscaler");
    }

    public void uploadImage(String filePath) {
        driver.findElement(uploadButton).sendKeys(filePath);
    }

    public boolean isUploadButtonDisplayed() {
        return driver.findElement(uploadButton).isDisplayed();
    }

    public boolean isUploadSuccessful() {
        return driver.findElement(uploadStatus).isDisplayed();
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
}
