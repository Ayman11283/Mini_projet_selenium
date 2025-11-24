package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DescriptionProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By descriptionParagraph = By.xpath("//p[contains(@class,'roboto-light') and contains(@class,'ng-binding')]");
    private By prixProduit = By.xpath("//h2[@class='roboto-thin screen768 ng-binding']");
    private By motColor = By.xpath("//span[@class='bunny' and text()='COLOR']");
    private By quantiteValue = By.xpath("//input[@name='quantity']");
    private By productSpecifications = By.xpath("//article//h3[text()='PRODUCT SPECIFICATIONS']");
    private By addToCartButton = By.xpath("//button[@name='save_to_cart']");
    private By miniPanierDropdown = By.xpath("//div[@id='toolTipCart']");
    private By checkoutButtonDropdown = By.xpath("//button[@id='checkOutPopUp' or @name='check_out_btn']");
    private By totalDropdown = By.xpath("//*[@id='menuCart']");

    public DescriptionProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDescriptionVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionParagraph)).isDisplayed();
    }

    public boolean isPriceVisible() {
        return driver.findElement(prixProduit).isDisplayed();
    }

    public boolean isColorVisible() {
        try {
            return driver.findElement(motColor).isDisplayed();
        } catch (Exception e) {
            System.out.println("Warning: Mot Color absent - test continue");
            return false;
        }
    }

    public String getQuantityValue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantiteValue));
        return driver.findElement(quantiteValue).getAttribute("value");
    }

    public boolean isProductSpecificationsVisible() {
        try {
            return driver.findElement(productSpecifications).isDisplayed();
        } catch (Exception e) {
            System.out.println("Warning: Product specifications absent - test continue");
            return false;
        }
    }

    public boolean isAddToCartButtonVisible() {
        return driver.findElement(addToCartButton).isDisplayed();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public boolean isMiniCartDropdownVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(miniPanierDropdown)).isDisplayed();
    }

    public boolean isCheckoutButtonVisible() {
        return driver.findElement(checkoutButtonDropdown).isDisplayed();
    }

    public boolean isTotalDropdownVisible() {
        return driver.findElement(totalDropdown).isDisplayed();
    }

    public void clickTotalDropdown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalDropdown));
        driver.findElement(totalDropdown).click();
    }

    public String addProductToCartAndGetQuantity() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantiteValue));
        String qty = getQuantityValue();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        WebElement addButton = driver.findElement(addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        sleep(1000);
        ((JavascriptExecutor) driver).executeScript("document.querySelector(\"button[name='save_to_cart']\").click()");
        sleep(2000);
        
        return qty;
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
