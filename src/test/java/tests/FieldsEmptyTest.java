package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

public class FieldsEmptyTest extends BaseTest {

    @Test
    public void verifierChampsVides() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Cliquer sur le champ username puis appuyer sur TAB
        createAccountPage.clickUsernameField();
        createAccountPage.pressTabOnUsername();
        sleep(2000);

        // Vérifier le message d'erreur pour username requis
        Assert.assertTrue(createAccountPage.isUsernameRequiredMsgVisible(),
            "Le message d'erreur username requis devrait être visible");

        // Cliquer sur email puis password
        createAccountPage.clickEmailField();
        createAccountPage.clickPasswordField();
        sleep(2000);

        // Vérifier le message d'erreur pour email requis
        Assert.assertTrue(createAccountPage.isEmailRequiredMsgVisible(),
            "Le message 'Email field is required' devrait être visible");

        // Cliquer sur confirm password
        createAccountPage.clickConfirmPasswordField();
        sleep(2000);

        // Vérifier le message d'erreur pour password requis
        Assert.assertTrue(createAccountPage.isPasswordRequiredMsgVisible(),
            "Le message 'Password field is required' devrait être visible");
    }
}
