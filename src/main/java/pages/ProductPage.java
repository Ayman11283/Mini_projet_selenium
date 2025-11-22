package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By buyNowButton = By.xpath("//button[@name='buy_now' and normalize-space(.)='BUY NOW']");
    private By productTitle = By.xpath("//h3[contains(@class,'categoryTitle') and normalize-space(text())='LAPTOPS']");
    private By productPrice = By.xpath("//a[contains(@class,'productPrice')]");
    private By firstProductList = By.xpath("(//li[contains(@ng-repeat,'product')])[1]//a[contains(@class,'productName')]");
    private By productDescription = By.xpath("//div[@class='product-description']");
    private By productImage = By.xpath("//img[contains(@class,'imgProduct')]");
    private By itemsTitle = By.xpath("//a[contains(@class,'titleItemsCount') and contains(normalize-space(.),'ITEMS')]");
    private By filterBy = By.xpath("//label[contains(text(), 'FILTER BY')]");
    private By homeButton = By.xpath("//div[@class='logo']//a");
    private By laptopsCategory = By.xpath("//span[@id='laptopsTxt']");
    private By tabletsCategory = By.xpath("//span[@id='tabletsTxt']");
    private By speakersCategory = By.xpath("//span[@id='speakersTxt']");
    private By loader = By.xpath("//div[@class='loader']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isBuyNowButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buyNowButton)).isDisplayed();
    }

    public boolean isProductTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).isDisplayed();
    }

    public boolean isFirstProductListVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductList)).isDisplayed();
    }

    public boolean isProductPriceVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).isDisplayed();
    }

    public boolean isProductDescriptionVisible() {
        return driver.findElement(productDescription).isDisplayed();
    }

    public boolean isProductImageVisible() {
        return driver.findElement(productImage).isDisplayed();
    }

    public boolean isItemsCountVisible() {
        return driver.findElement(itemsTitle).isDisplayed();
    }

    public boolean isFilterByLabelVisible() {
        return driver.findElement(filterBy).isDisplayed();
    }

    public void navigateToCategory(String category) {
        By categoryLocator;
        switch (category.toLowerCase()) {
            case "laptops":
                categoryLocator = laptopsCategory;
                break;
            case "tablets":
                categoryLocator = tabletsCategory;
                break;
            case "speakers":
                categoryLocator = speakersCategory;
                break;
            default:
                throw new IllegalArgumentException("Catégorie invalide: " + category);
        }
        
        WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", categoryElement);
        categoryElement.click();
        sleep(2000);
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductList));
        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        sleep(1000);
        driver.findElement(firstProductList).click();
        sleep(3000);
    }

    public void returnToHome() {
        driver.findElement(homeButton).click();
        sleep(2000);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
