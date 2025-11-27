package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

public class LoginValidTest extends BaseTest {

    @Test
    public void verifierConnexionValide() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Cliquer sur le bouton utilisateur pour ouvrir le formulaire de connexion
        homePage.clickUser();
        sleep(3000);

        // Note: Utilisez un compte que vous avez créé manuellement dans l'application
        // ou utilisez les credentials que vous connaissez
        String username = "ayaBahri";  // Username valide
        String password = "@123ABc";  // Mot de passe correct
        
        // Remplir les informations de connexion
        loginPage.fillUsername(username);
        sleep(500);
        loginPage.fillPassword(password);
        sleep(500);
        
        // Cliquer sur le bouton Sign In
        loginPage.clickSignIn();
        sleep(5000);

        // Vérifier que l'utilisateur est connecté
        boolean isConnected = loginPage.isUserLoggedIn(username);
        
        if (!isConnected) {
            // Si la connexion échoue, afficher le message pour debug
            String menuText = loginPage.getLoggedInUsername();
            System.out.println("DEBUG - Texte du menu après tentative de connexion: '" + menuText + "'");
            System.out.println("DEBUG - Connexion échouée. Vérifiez que le compte '" + username + "' existe avec le bon mot de passe.");
        }
        
        Assert.assertTrue(isConnected,
            "L'utilisateur '" + username + "' devrait être connecté. Vérifiez que ce compte existe dans l'application.");
    }
}
