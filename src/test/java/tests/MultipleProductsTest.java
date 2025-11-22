package test.java.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.pages.CartPage;
import main.java.pages.DescriptionProductPage;
import test.java.utils.BaseTest;
import main.java.utils.CommonKeywords;

public class MultipleProductsTest extends BaseTest {

    @Test
    public void verifierAjoutMultipleProduits() {
        DescriptionProductPage descriptionPage = new DescriptionProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CommonKeywords commonKeywords = new CommonKeywords(driver);

        // Ajouter les 3 produits
        commonKeywords.addThreeProductsToCart();

        // ========== VERIFICATION PANIER ==========
        System.out.println("=== Vérification du panier ===");
        descriptionPage.clickTotalDropdown();
        sleep(3000);

        Assert.assertTrue(cartPage.isCartTitleVisible(), "Cart title should be visible");

        // Vérifier que le panier contient des produits via le prix total
        String prixTotal = cartPage.getCartTotalPrice();
        System.out.println("Prix total du panier: " + prixTotal);
        
        Assert.assertNotEquals(prixTotal, "$0.00", "Le panier ne devrait pas être vide");
        System.out.println("✓ Test réussi: Panier contient des produits, prix total = " + prixTotal);
    }
}
