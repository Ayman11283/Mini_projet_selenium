package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

public class UsernameAlreadyExistsTest extends BaseTest {

    @Test
    public void verifierUsernameDejaExistant() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Remplir les champs avec un username déjà existant
        createAccountPage.fillUsername("ayaBahri");
        createAccountPage.fillEmail("aya.a.bahri@capgemini.com");
        createAccountPage.fillPassword("Passw0rd!");
        createAccountPage.fillConfirmPassword("Passw0rd!");
        
        // Cocher la case newsletter
        createAccountPage.checkNewsletterAgreement();
        
        // Cliquer sur le bouton créer compte
        createAccountPage.clickRegisterButton();
        sleep(2000);

        // Vérifier le message d'erreur indiquant que le username existe déjà
        Assert.assertTrue(createAccountPage.isUsernameExistsMsgVisible(),
            "Le message indiquant que le username existe déjà devrait être visible");
    }
}
