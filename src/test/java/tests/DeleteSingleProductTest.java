package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DescriptionProductPage;
import pages.ProductPage;
import utils.BaseTest;

/**
 * Test de suppression d'un produit unique du panier
 * Converti depuis DeleteProdctUnique.robot
 */
public class DeleteSingleProductTest extends BaseTest {

    @Test(description = "Vérifier suppression produit unitaire - le panier devrait être vide")
    public void verifierSuppressionProduitUnitaire() {
        ProductPage productPage = new ProductPage(driver);
        DescriptionProductPage descriptionPage = new DescriptionProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Ajouter un seul produit
        productPage.navigateToCategory("LAPTOPS");
        productPage.clickFirstProduct();
        
        String quantity = descriptionPage.addProductToCartAndGetQuantity();
        System.out.println("Quantité ajoutée: " + quantity);

        // Aller au panier
        descriptionPage.clickTotalDropdown();
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Récupérer le prix avant suppression
        String prixAvant = cartPage.getCartTotalPrice();
        System.out.println("Prix avant suppression: " + prixAvant);

        // Supprimer le produit
        cartPage.removeFirstProduct();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Récupérer le prix après suppression
        String prixApres = cartPage.getCartTotalPrice();
        System.out.println("Prix après suppression: " + prixApres);

        // Vérifier que le panier est vide
        Assert.assertEquals(prixApres, "$0.00", 
            "Le panier devrait être vide après suppression");
        System.out.println("✓ Suppression unitaire réussie: Panier vide");
    }
}
