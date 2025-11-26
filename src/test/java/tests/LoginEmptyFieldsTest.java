package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

/**
 * Tests pour valider les champs vides lors de la connexion
 * Convertis depuis ChampsVide.robot
 */
public class LoginEmptyFieldsTest extends BaseTest {

    @Test(priority = 1, description = "Vérifier que le bouton Sign In est désactivé au démarrage")
    public void verifierBoutonSignInDesactiveAuDemarrage() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.openLoginDialog();
        
        // Vérifier que le bouton Sign In est désactivé quand les champs sont vides
        Assert.assertFalse(loginPage.isSignInButtonEnabled(), 
            "Le bouton Sign In devrait être désactivé quand les champs sont vides");
    }

    @Test(priority = 2, description = "Vérifier que email est requis même avec mot de passe rempli")
    public void verifierEmailRequisAvecMotDePasse() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.openLoginDialog();
        loginPage.fillPassword("TestPassword123");
        
        // Vérifier le message d'erreur pour username requis
        String errorMessage = loginPage.getUsernameRequiredMessage();
        Assert.assertTrue(errorMessage.contains("Username field is required"), 
            "Le message 'Username field is required' devrait s'afficher");
    }

    @Test(priority = 3, description = "Vérifier que mot de passe est requis même avec email rempli")
    public void verifierMotDePasseRequisAvecEmail() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.openLoginDialog();
        loginPage.fillUsername("test@example.com");
        
        // Vérifier que le bouton reste désactivé sans mot de passe
        Assert.assertFalse(loginPage.isSignInButtonEnabled(), 
            "Le bouton Sign In devrait être désactivé sans mot de passe");
    }
}
