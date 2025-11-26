package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

/*Vérification du username trop court lors de la création de compte */
public class UsernameShortTest extends BaseTest {

    @Test
    public void verifierUsernameTropCourt() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Entrer un username trop court
        createAccountPage.clickUsernameField();
        createAccountPage.fillUsername("ab");
        createAccountPage.pressTabOnUsername();
        sleep(1000);

        // Vérifier le message d'erreur
        Assert.assertTrue(createAccountPage.isUsernameTooShortMsgVisible(),
            "Le message 'Use 5 character or longer' devrait être visible");
    }
}
