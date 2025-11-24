package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DescriptionProductPage;
import utils.BaseTest;
import utils.CommonKeywords;

public class SuppressionProduitTest extends BaseTest {

    @Test
    public void verifierSuppressionProduitDuPanier() {
        DescriptionProductPage descriptionPage = new DescriptionProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CommonKeywords commonKeywords = new CommonKeywords(driver);

        // Appeler la méthode d'ajout multiple
        commonKeywords.addThreeProductsToCart();

        // Aller au panier
        descriptionPage.clickTotalDropdown();
        sleep(3000);

        Assert.assertTrue(cartPage.isCartTitleVisible(), "Cart title should be visible");

        // Récupérer le prix total AVANT suppression
        String prixAvant = cartPage.getCartTotalPrice();
        System.out.println("Prix total avant suppression: " + prixAvant);

        // Supprimer un produit (le premier dans la liste)
        cartPage.removeFirstProduct();
        sleep(2000);

        // Récupérer le prix total APRÈS suppression
        String prixApres = cartPage.getCartTotalPrice();
        System.out.println("Prix total après suppression: " + prixApres);

        // Vérifier que le prix a diminué
        Assert.assertNotEquals(prixAvant, prixApres, "Le prix total devrait avoir diminué après suppression");
        System.out.println("✓ Suppression réussie: Prix avant=" + prixAvant + ", Prix après=" + prixApres);
    }
}
