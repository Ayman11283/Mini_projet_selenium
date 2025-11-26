package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test de mise à jour du prix total après ajout au panier
 * Converti depuis MajPrixTot.robot
 */
public class UpdateTotalPriceTest extends BaseTest {

    private static final String PRODUCT_URL = "https://advantageonlineshopping.com/#/product/9";
    private static final double UNIT_PRICE = 299.99;
    private static final int EXPECTED_QUANTITY = 2;

    @Test(description = "Tester le prix total après ajout au panier")
    public void testerLePrixTotalApresAjoutAuPanier() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Naviguer vers le produit
        driver.get(PRODUCT_URL);
        driver.manage().window().maximize();

        // Augmenter la quantité
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='plus' and @increment-value-attr='+']")));
        addButton.click();

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Cliquer sur ADD TO CART
        WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//button[text()='ADD TO CART']")));
        addToCartBtn.click();

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Aller au checkout
        WebElement checkoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//button[contains(text(),'CHECKOUT')]")));
        checkoutBtn.click();

        // Vérifier le prix total
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[@class='roboto-medium cart-total ng-binding']")));
        String displayedPriceText = priceElement.getText();
        System.out.println("Prix affiché : " + displayedPriceText);

        // Calculer le prix attendu
        double expectedPrice = UNIT_PRICE * EXPECTED_QUANTITY;
        System.out.println("Prix attendu : $" + expectedPrice);

        // Extraire le prix numérique du texte affiché
        Pattern pattern = Pattern.compile("[\\d,.]+");
        Matcher matcher = pattern.matcher(displayedPriceText);
        
        Assert.assertTrue(matcher.find(), "Le prix devrait être trouvé dans le texte");
        
        String priceString = matcher.group().replace(",", "");
        double displayedPrice = Double.parseDouble(priceString);

        // Vérifier que les prix correspondent
        Assert.assertEquals(displayedPrice, expectedPrice, 0.01, 
            "Le prix total devrait correspondre au prix unitaire × quantité");
        
        System.out.println("✓ Test réussi: Prix total correct = $" + displayedPrice);
    }
}
