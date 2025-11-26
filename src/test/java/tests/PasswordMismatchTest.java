package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

/*Vérification des mots de passe non concordants lors de la création de compte */
public class PasswordMismatchTest extends BaseTest {

    @Test
    public void verifierMotsDePasseNonConcordants() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Remplir les champs avec des mots de passe différents
        createAccountPage.fillUsername("TesteurAB");
        createAccountPage.fillEmail("aya.a.bahri@capgemini.com");
        createAccountPage.fillPassword("Pass123");
        createAccountPage.fillConfirmPassword("Pass123!"); // Différent
        
        // Cocher la case newsletter
        createAccountPage.checkNewsletterAgreement();
        sleep(1000);

        // Vérifier le message d'erreur "Passwords do not match"
        Assert.assertTrue(createAccountPage.isPasswordNotMatchMsgVisible(),
            "Le message 'Passwords do not match' devrait être visible");
    }
}
