package test.java.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.pages.CartPage;
import main.java.pages.DescriptionProductPage;
import main.java.pages.ProductPage;
import test.java.utils.BaseTest;

public class AjoutUnitaireTest extends BaseTest {

    @Test
    public void verifierElementsPageProduits() {
        ProductPage productPage = new ProductPage(driver);
        DescriptionProductPage descriptionPage = new DescriptionProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Naviguer vers la catégorie Laptops
        productPage.navigateToCategory("laptops");
        sleep(2000);

        // Vérifier les éléments présents sur la page de la catégorie Laptops
        Assert.assertTrue(productPage.isProductTitleVisible(), "Product title should be visible");
        Assert.assertTrue(productPage.isItemsCountVisible(), "Items count should be visible");
        Assert.assertTrue(productPage.isFilterByLabelVisible(), "Filter by label should be visible");
        Assert.assertTrue(productPage.isProductPriceVisible(), "Product price should be visible");
        Assert.assertTrue(productPage.isProductImageVisible(), "Product image should be visible");
        Assert.assertTrue(productPage.isBuyNowButtonVisible(), "Buy now button should be visible");

        // Cliquer sur le premier produit et attendre le chargement
        productPage.clickFirstProduct();
        sleep(2000);

        // Vérifier les éléments présents sur la page de détails du produit
        Assert.assertTrue(descriptionPage.isDescriptionVisible(), "Description should be visible");
        Assert.assertTrue(descriptionPage.isPriceVisible(), "Price should be visible");
        descriptionPage.isColorVisible(); // Optional element
        descriptionPage.isProductSpecificationsVisible(); // Optional element
        Assert.assertTrue(descriptionPage.isAddToCartButtonVisible(), "Add to cart button should be visible");

        // Ajouter au panier et récupérer la quantité
        String qtyProduct = descriptionPage.addProductToCartAndGetQuantity();
        System.out.println("Quantité du produit: " + qtyProduct);

        // Naviguer vers le panier
        descriptionPage.clickTotalDropdown();
        sleep(3000);

        // Vérifier les éléments du panier
        Assert.assertTrue(cartPage.isCartTitleVisible(), "Cart title should be visible");
        Assert.assertTrue(cartPage.isProductNameVisible(), "Product name should be visible");
        Assert.assertTrue(cartPage.isQuantityDisplayVisible(), "Quantity should be visible");
        Assert.assertTrue(cartPage.isUnitPriceVisible(), "Unit price should be visible");
        Assert.assertTrue(cartPage.isTotalPriceVisible(), "Total price should be visible");

        // Vérifier que la quantité est correcte
        String qtyCart = cartPage.getQuantityValueFromCart();
        System.out.println("Quantité dans le panier: " + qtyCart);
        cartPage.verifyQuantitiesEqual(qtyProduct, qtyCart);
        
        System.out.println("✓ Test ajout unitaire réussi");
    }
}
