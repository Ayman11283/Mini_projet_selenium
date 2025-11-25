package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

public class InvalidEmailTest extends BaseTest {

    @Test
    public void verifierEmailInvalide() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Remplir les champs avec un email invalide
        createAccountPage.fillUsername("TesteurAB");
        createAccountPage.fillEmail("ayacapgeminicom"); // Email sans @
        createAccountPage.fillPassword("Pass123");
        createAccountPage.fillConfirmPassword("Pass123");
        
        // Cocher la case newsletter
        createAccountPage.checkNewsletterAgreement();
        sleep(1000);

        // Vérifier le message d'erreur pour email invalide
        Assert.assertTrue(createAccountPage.isEmailInvalidMsgVisible(),
            "Le message d'email invalide devrait être visible");
    }
}
