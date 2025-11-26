package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

/*Vérification des règles de validation des mots de passe lors de la création de compte */
public class PasswordRulesValidationTest extends BaseTest {

    @Test
    public void verifierMotDePasseTropCourt() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Entrer un mot de passe trop court
        createAccountPage.clickPasswordField();
        createAccountPage.fillPassword("ab");
        createAccountPage.pressTabOnPassword();
        sleep(1000);

        // Vérifier le message d'erreur
        Assert.assertTrue(createAccountPage.isPasswordTooShortMsgVisible(),
            "Le message 'Use 4 character or longer' devrait être visible");
    }

    @Test
    public void verifierMotDePasseSansMinuscule() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Entrer un mot de passe sans minuscule
        createAccountPage.clickPasswordField();
        createAccountPage.fillPassword("PASSWORD1");
        createAccountPage.pressTabOnPassword();
        sleep(1000);

        // Vérifier le message d'erreur
        Assert.assertTrue(createAccountPage.isPasswordLowerLetterMsgVisible(),
            "Le message 'One lower letter required' devrait être visible");
    }

    @Test
    public void verifierMotDePasseSansMajuscule() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Entrer un mot de passe sans majuscule
        createAccountPage.clickPasswordField();
        createAccountPage.fillPassword("password1");
        createAccountPage.pressTabOnPassword();
        sleep(1000);

        // Vérifier le message d'erreur
        Assert.assertTrue(createAccountPage.isPasswordUpperLetterMsgVisible(),
            "Le message 'One upper letter required' devrait être visible");
    }

    @Test
    public void verifierMotDePasseTropLong() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Entrer un mot de passe trop long
        createAccountPage.clickPasswordField();
        createAccountPage.fillPassword("ThisIsAVeryLongPwd1");
        createAccountPage.pressTabOnPassword();
        sleep(1000);

        // Vérifier le message d'erreur
        Assert.assertTrue(createAccountPage.isPasswordTooLongMsgVisible(),
            "Le message 'Use maximum 12 character' devrait être visible");
    }
}
