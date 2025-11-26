package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By signInButton = By.id("sign_in_btn");
    private By accountName = By.xpath("//span[contains(@class,'hi-user') and contains(@class,'containMiniTitle')]");
    private By userMenuLink = By.id("menuUserLink");
    private By closeButton = By.xpath("//div[@id='loginMiniTitle']//div[@class='closeBtn']");
    private By usernameRequiredMsg = By.xpath("//label[text()='Username field is required']");
    private By errorMessage = By.xpath("//label[contains(@class,'invalid') or contains(text(),'Incorrect')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        WebElement userField = driver.findElement(usernameField);
        userField.clear();
        userField.sendKeys(username);
    }

    public void fillPassword(String password) {
        WebElement pwdField = driver.findElement(passwordField);
        pwdField.clear();
        pwdField.sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
        sleep(2000);
    }

    public boolean isUserLoggedIn(String expectedUsername) {
        try {
            sleep(2000);
            // Vérifier si l'élément menuUserLink contient le texte du username
            WebElement userMenu = wait.until(ExpectedConditions.presenceOfElementLocated(userMenuLink));
            String menuText = userMenu.getText();
            return menuText.toLowerCase().contains(expectedUsername.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedInUsername() {
        sleep(2000);
        WebElement userMenu = wait.until(ExpectedConditions.presenceOfElementLocated(userMenuLink));
        return userMenu.getText();
    }

    public void openLoginDialog() {
        // Attendre que le loader disparaisse avant de cliquer
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userMenuLink));
        wait.until(ExpectedConditions.elementToBeClickable(userMenuLink));
        
        try {
            driver.findElement(userMenuLink).click();
        } catch (Exception e) {
            // Fallback avec JavaScript si le clic normal échoue
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "document.getElementById('menuUserLink').click()");
        }
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public boolean isSignInButtonEnabled() {
        try {
            WebElement button = driver.findElement(signInButton);
            return button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameRequiredMessage() {
        try {
            WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredMsg));
            return msgElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getErrorMessage() {
        try {
            sleep(1000);
            WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return msgElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
