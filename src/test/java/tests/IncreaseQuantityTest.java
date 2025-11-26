package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

/**
 * Test d'augmentation de quantité de produit
 * Converti depuis IncreaseProductQuantity.robot
 */
public class IncreaseQuantityTest extends BaseTest {

    @Test(description = "Vérifier que la quantité augmente après avoir cliqué sur +")
    public void verifyQuantityIncreasesAfterClickingPlus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Attendre et ouvrir la page Speakers
        WebElement speakersImg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='speakersImg']")));
        speakersImg.click();

        // Cliquer sur Buy Now
        WebElement buyNowBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//button[contains(text(),'BUY NOW')]")));
        buyNowBtn.click();

        // Récupérer la quantité initiale
        WebElement qtyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@name='quantity']")));
        int initialQty = Integer.parseInt(qtyInput.getAttribute("value"));
        System.out.println("Quantité initiale: " + initialQty);

        // Augmenter la quantité
        WebElement plusBtn = driver.findElement(
            By.xpath("//div[@class='e-sec-plus-minus']//div[@class='plus' and @increment-value-attr='+']"));
        plusBtn.click();

        // Récupérer la nouvelle quantité
        int newQty = Integer.parseInt(qtyInput.getAttribute("value"));
        System.out.println("Nouvelle quantité: " + newQty);

        // Vérifier que la quantité a augmenté de 1
        int expectedQty = initialQty + 1;
        Assert.assertEquals(newQty, expectedQty, 
            "La quantité devrait avoir augmenté de 1");

        // Ajouter au panier
        WebElement addToCartBtn = driver.findElement(
            By.xpath("//button[@name='save_to_cart']"));
        addToCartBtn.click();

        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Vérifier la quantité dans l'icône du panier
        WebElement qtyIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//table//tr[@id='product']//label[contains(text(),'QTY')]")));
        String cartQtyText = qtyIcon.getText();
        
        Assert.assertTrue(cartQtyText.contains(String.valueOf(newQty)), 
            "La quantité dans le panier devrait correspondre à " + newQty);
        System.out.println("✓ Test réussi: Quantité augmentée correctement");
    }
}
