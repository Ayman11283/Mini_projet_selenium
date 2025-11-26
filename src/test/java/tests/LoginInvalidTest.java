package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

/**
 * Tests pour valider la connexion avec des identifiants invalides
 * Convertis depuis ConnexionInvalide.robot
 */
public class LoginInvalidTest extends BaseTest {

    private static final String INVALID_EMAIL = "sarayousfi.com";
    private static final String INCORRECT_PASSWORD = "SI20";
    private static final String ERROR_MESSAGE = "Incorrect user name or password.";

    @Test(priority = 1, description = "Connexion invalide avec email et mot de passe incorrect")
    public void connexionInvalideAvecEmailEtMotDePasseIncorrect() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.openLoginDialog();
        loginPage.fillUsername(INVALID_EMAIL);
        loginPage.fillPassword(INCORRECT_PASSWORD);
        loginPage.clickSignIn();
        
        // Vérifier que le message d'erreur s'affiche
        Assert.assertTrue(loginPage.getErrorMessage().contains(ERROR_MESSAGE), 
            "Le message d'erreur devrait s'afficher");
    }

    @Test(priority = 2, description = "Connexion invalide avec email invalide")
    public void connexionInvalideAvecEmailInvalide() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.openLoginDialog();
        loginPage.fillUsername(INVALID_EMAIL);
        loginPage.fillPassword(INCORRECT_PASSWORD);
        loginPage.clickSignIn();
        
        // Vérifier que le message d'erreur s'affiche
        Assert.assertTrue(loginPage.getErrorMessage().contains(ERROR_MESSAGE), 
            "Le message d'erreur devrait s'afficher pour email invalide");
    }

    @Test(priority = 3, description = "Connexion invalide avec mot de passe incorrect")
    public void connexionInvalideAvecMotDePasseIncorrect() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.openLoginDialog();
        loginPage.fillUsername(INVALID_EMAIL);
        loginPage.fillPassword(INCORRECT_PASSWORD);
        loginPage.clickSignIn();
        
        // Vérifier que le message d'erreur s'affiche
        Assert.assertTrue(loginPage.getErrorMessage().contains(ERROR_MESSAGE), 
            "Le message d'erreur devrait s'afficher pour mot de passe incorrect");
    }
}
