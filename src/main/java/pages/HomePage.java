package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartButton = By.id("shoppingCartLink");
    private By userButton = By.id("menuUserLink");
    private By createNewAccountButton = By.xpath("//div[contains(@class,'login')]//a[contains(@class,'create-new-account')]");
    private By loader = By.cssSelector(".loader");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickUser() {
        // Attendre que le loader disparaisse
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception e) {
            // Loader peut ne pas être présent
        }
        
        // Attendre que le bouton soit cliquable
        WebElement userElement = wait.until(ExpectedConditions.elementToBeClickable(userButton));
        
        // Essayer de cliquer normalement, sinon utiliser JavaScript
        try {
            userElement.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", userElement);
        }
        sleep(1000);
    }

    public void clickCreateNewAccount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createNewAccountButton));
        wait.until(ExpectedConditions.elementToBeClickable(createNewAccountButton));
        
        // Attendre que le loader disparaisse
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception e) {
            // Loader peut ne pas être présent
        }
        
        // Utiliser JavaScript pour cliquer (plus fiable)
        WebElement element = driver.findElement(createNewAccountButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        sleep(1000);
    }

    public void clickCart() {
        driver.findElement(cartButton).click();
    }

    public void verifyHomePageAfterLogin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartButton));
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
