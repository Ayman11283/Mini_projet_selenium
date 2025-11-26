package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.CreateAccountPage;
import utils.BaseTest;

/*Vérification du masquage des mots de passe dans la page de création de compte */
public class PasswordMaskedTest extends BaseTest {

    @Test
    public void verifierMasquageMotDePasse() {
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Naviguer vers la page de création de compte
        homePage.clickUser();
        sleep(2000);
        homePage.clickCreateNewAccount();
        sleep(2000);

        // Cliquer sur les champs password et confirm password
        createAccountPage.clickPasswordField();
        createAccountPage.clickConfirmPasswordField();
        sleep(1000);

        // Vérifier que les champs sont de type "password" (masqués)
        Assert.assertEquals(createAccountPage.getPasswordFieldType(), "password",
            "Le champ password devrait être de type 'password'");
        Assert.assertEquals(createAccountPage.getConfirmPasswordFieldType(), "password",
            "Le champ confirm password devrait être de type 'password'");
    }
}
