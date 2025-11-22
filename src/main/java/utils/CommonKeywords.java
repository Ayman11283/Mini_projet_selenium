package main.java.utils;

import org.openqa.selenium.WebDriver;
import main.java.pages.ProductPage;
import main.java.pages.DescriptionProductPage;

public class CommonKeywords {
    private WebDriver driver;
    private ProductPage productPage;
    private DescriptionProductPage descriptionPage;

    public CommonKeywords(WebDriver driver) {
        this.driver = driver;
        this.productPage = new ProductPage(driver);
        this.descriptionPage = new DescriptionProductPage(driver);
    }

    /**
     * Ajoute 3 produits (laptop, tablet, speaker) au panier - Méthode réutilisable
     */
    public void addThreeProductsToCart() {
        // AJOUT LAPTOP
        System.out.println("=== Ajout d'un Laptop ===");
        productPage.navigateToCategory("laptops");
        productPage.clickFirstProduct();
        String qtyLaptop = descriptionPage.addProductToCartAndGetQuantity();
        System.out.println("Quantité laptop ajoutée: " + qtyLaptop);
        productPage.returnToHome();

        // AJOUT TABLET
        System.out.println("=== Ajout d'une Tablet ===");
        productPage.navigateToCategory("tablets");
        productPage.clickFirstProduct();
        String qtyTablet = descriptionPage.addProductToCartAndGetQuantity();
        System.out.println("Quantité tablet ajoutée: " + qtyTablet);
        productPage.returnToHome();

        // AJOUT SPEAKER
        System.out.println("=== Ajout d'un Speaker ===");
        productPage.navigateToCategory("speakers");
        productPage.clickFirstProduct();
        String qtySpeaker = descriptionPage.addProductToCartAndGetQuantity();
        System.out.println("Quantité speaker ajoutée: " + qtySpeaker);
        productPage.returnToHome();
    }
}
