package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartTitle = By.xpath("//h3[contains(@class,'roboto-regular') and contains(normalize-space(.),'SHOPPING CART')]");
    private By cartProductName = By.xpath("//label[contains(@class,'roboto-light') and normalize-space(.)='PRODUCT NAME']");
    private By cartQuantityInput = By.xpath("//table[@id='shoppingCart']//input[contains(@name, 'quantity')]");
    private By cartQuantityDisplay = By.xpath("//td[@class='smollCell quantityMobile']//label[@class='ng-binding']");
    private By cartUnitPrice = By.xpath("//p[@class='price roboto-regular ng-binding']");
    private By cartTotalPrice = By.xpath("(//p[@class='price roboto-regular ng-binding'])[2]");
    private By cartRemoveButton = By.xpath("//a[@class='remove red ng-scope']");
    private By productNamesInCart = By.xpath("//table[@id='shoppingCart']//a[@class='ng-binding']");
    private By allRemoveButtons = By.xpath("//table[@id='shoppingCart']//a[contains(@class,'remove')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCartTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle)).isDisplayed();
    }

    public boolean isProductNameVisible() {
        return driver.findElement(cartProductName).isDisplayed();
    }

    public boolean isQuantityDisplayVisible() {
        return driver.findElement(cartQuantityDisplay).isDisplayed();
    }

    public boolean isUnitPriceVisible() {
        return driver.findElement(cartUnitPrice).isDisplayed();
    }

    public boolean isTotalPriceVisible() {
        return driver.findElement(cartTotalPrice).isDisplayed();
    }

    public boolean isRemoveButtonVisible() {
        return driver.findElement(cartRemoveButton).isDisplayed();
    }

    public String getQuantityValueFromCart() {
        return driver.findElement(cartQuantityDisplay).getText();
    }

    public void verifyQuantitiesEqual(String qtyProduct, String qtyCart) {
        if (!qtyProduct.equals(qtyCart)) {
            throw new AssertionError("Les quantités ne sont pas égales: produit=" + qtyProduct + ", panier=" + qtyCart);
        }
    }

    public List<String> getAllCartQuantities() {
        List<String> quantities = new ArrayList<>();
        List<WebElement> qtyElements = driver.findElements(cartQuantityDisplay);
        for (WebElement element : qtyElements) {
            quantities.add(element.getText());
        }
        return quantities;
    }

    public int verifyTotalQuantities(List<String> expectedQuantities) {
        List<String> cartQuantities = getAllCartQuantities();
        System.out.println("Quantités dans le panier: " + cartQuantities);
        
        int expectedTotal = expectedQuantities.stream().mapToInt(Integer::parseInt).sum();
        int actualTotal = cartQuantities.stream().mapToInt(Integer::parseInt).sum();
        
        System.out.println("Total attendu: " + expectedTotal);
        System.out.println("Total réel: " + actualTotal);
        
        if (actualTotal != expectedTotal) {
            throw new AssertionError("Le total des quantités ne correspond pas: attendu=" + expectedTotal + ", réel=" + actualTotal);
        }
        return actualTotal;
    }

    public List<String> getAllProductNamesInCart() {
        List<String> names = new ArrayList<>();
        List<WebElement> nameElements = driver.findElements(productNamesInCart);
        for (WebElement element : nameElements) {
            names.add(element.getText());
        }
        return names;
    }

    public void removeProductByIndex(int index) {
        By removeButton = By.xpath("(//table[@id='shoppingCart']//a[contains(@class,'remove')])[" + index + "]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
        driver.findElement(removeButton).click();
        sleep(2000);
    }

    public void removeFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartRemoveButton));
        ((JavascriptExecutor) driver).executeScript("document.querySelector(\"a.remove.red.ng-scope\").click()");
        sleep(2000);
    }

    public void verifyProductAbsentFromCart(String productName) {
        List<String> cartProducts = getAllProductNamesInCart();
        if (cartProducts.contains(productName)) {
            throw new AssertionError("Le produit " + productName + " est encore dans le panier");
        }
        System.out.println("✓ Produit " + productName + " supprimé avec succès");
    }

    public int countProductsInCart() {
        List<String> names = getAllProductNamesInCart();
        return names.size();
    }

    public String getCartTotalPrice() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotalPrice));
            String priceText = driver.findElement(cartTotalPrice).getText();
            System.out.println("Prix total récupéré: " + priceText);
            return priceText;
        } catch (Exception e) {
            System.out.println("Prix total récupéré: $0.00");
            return "$0.00";
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
