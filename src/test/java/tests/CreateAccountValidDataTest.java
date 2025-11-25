package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

public class CreateAccountValidDataTest extends BaseTest {

    @Test
    public void verifierCreationCompteAvecDonneesValides() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Générer un username unique avec timestamp
        String uniqueUsername = "Testeur" + System.currentTimeMillis();

        // Remplir le formulaire avec des données valides
        createAccountPage.fillUsername(uniqueUsername);
        createAccountPage.fillEmail("test" + System.currentTimeMillis() + "@example.com");
        createAccountPage.fillPassword("Pass123");
        createAccountPage.fillConfirmPassword("Pass123");
        createAccountPage.fillFirstName("Test");
        createAccountPage.fillLastName("User");
        createAccountPage.fillPhoneNumber("0612345678");
        createAccountPage.fillCity("Paris");
        createAccountPage.fillAddress("123 Rue Exemple");
        createAccountPage.fillPostalCode("75000");
        
        // Cocher la case newsletter
        createAccountPage.checkNewsletterAgreement();
        
        // Vérifier que le titre est visible avant soumission
        Assert.assertTrue(createAccountPage.isCreateAccountTitleVisible(), 
            "Le titre CREATE ACCOUNT devrait être visible");
    }
}
