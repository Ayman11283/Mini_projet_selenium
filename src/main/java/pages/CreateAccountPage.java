package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By titleCreateAccount = By.xpath("//h3[contains(text(),'CREATE')]");
    private By username = By.xpath("//div[@class='inputContainer ng-scope']//input[@name='usernameRegisterPage']");
    private By email = By.name("emailRegisterPage");
    private By password = By.name("passwordRegisterPage");
    private By confirmPassword = By.name("confirm_passwordRegisterPage");
    private By firstName = By.name("first_nameRegisterPage");
    private By lastName = By.name("last_nameRegisterPage");
    private By phoneNumber = By.name("phone_numberRegisterPage");
    private By country = By.name("countryListboxRegisterPage");
    private By city = By.name("cityRegisterPage");
    private By address = By.name("addressRegisterPage");
    private By state = By.name("state_/_province_/_regionRegisterPage");
    private By postalCode = By.name("postal_codeRegisterPage");
    private By registerButton = By.id("register_btn");
    private By agreeCheckbox = By.name("i_agree");
    private By offersCheckbox = By.name("allowOffersPromotion");

    // Error message locators
    private By usernameRequiredMsg = By.xpath("//input[@name='usernameRegisterPage']/following-sibling::label[contains(@class,'invalid')]");
    private By usernameLongerMsg = By.xpath("//label[contains(normalize-space(.),'Use 5 character or longer')]");
    private By usernameExistsMsg = By.xpath("//label[contains(@class,'smollMargin') and @data-ng-show='!registerSuccess']");
    private By emailRequiredMsg = By.xpath("//label[contains(.,'Email field is required')]");
    private By emailInvalidMsg = By.xpath("//label[@class='animated invalid'][1]");
    private By passwordRequiredMsg = By.xpath("//label[contains(.,'Password field is required')]");
    private By confirmPasswordRequiredMsg = By.xpath("//input[@name='confirm_passwordRegisterPage']/ancestor::div[@class='inputContainer ng-scope']//label[contains(@class,'invalid')]");
    private By passwordNotMatchMsg = By.xpath("//label[text()='Passwords do not match']");
    private By passwordShortMsg = By.xpath("//label[contains(normalize-space(.),'Use 4 character or longer')]");
    private By passwordLowerLetterMsg = By.xpath("//label[contains(normalize-space(.),'One lower letter required')]");
    private By passwordUpperLetterMsg = By.xpath("//label[contains(normalize-space(.),'One upper letter required')]");
    private By passwordLongMsg = By.xpath("//label[contains(normalize-space(.),'Use maximum 12 character')]");

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillUsername(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        WebElement usernameField = driver.findElement(username);
        usernameField.click();
        usernameField.clear();
        usernameField.sendKeys(user);
    }

    public void fillEmail(String emailValue) {
        WebElement emailField = driver.findElement(email);
        emailField.click();
        emailField.clear();
        emailField.sendKeys(emailValue);
    }

    public void fillPassword(String pwd) {
        WebElement passwordField = driver.findElement(password);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(pwd);
    }

    public void fillConfirmPassword(String pwd) {
        WebElement confirmPwdField = driver.findElement(confirmPassword);
        confirmPwdField.click();
        confirmPwdField.clear();
        confirmPwdField.sendKeys(pwd);
    }

    public void fillFirstName(String name) {
        WebElement firstNameField = driver.findElement(firstName);
        firstNameField.click();
        firstNameField.sendKeys(name);
    }

    public void fillLastName(String name) {
        WebElement lastNameField = driver.findElement(lastName);
        lastNameField.click();
        lastNameField.sendKeys(name);
    }

    public void fillPhoneNumber(String phone) {
        WebElement phoneField = driver.findElement(phoneNumber);
        phoneField.click();
        phoneField.sendKeys(phone);
    }

    public void selectCountry(String countryName) {
        Select countryDropdown = new Select(driver.findElement(country));
        countryDropdown.selectByVisibleText(countryName);
    }

    public void fillCity(String cityName) {
        WebElement cityField = driver.findElement(city);
        cityField.click();
        cityField.sendKeys(cityName);
    }

    public void fillAddress(String addr) {
        WebElement addressField = driver.findElement(address);
        addressField.click();
        addressField.sendKeys(addr);
    }

    public void fillState(String stateName) {
        WebElement stateField = driver.findElement(state);
        stateField.click();
        stateField.sendKeys(stateName);
    }

    public void fillPostalCode(String postal) {
        WebElement postalField = driver.findElement(postalCode);
        postalField.click();
        postalField.sendKeys(postal);
    }

    public void checkNewsletterAgreement() {
        driver.findElement(agreeCheckbox).click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void pressTabOnUsername() {
        driver.findElement(username).sendKeys(Keys.TAB);
    }

    public void pressTabOnPassword() {
        driver.findElement(password).sendKeys(Keys.TAB);
    }

    public void clickUsernameField() {
        driver.findElement(username).click();
    }

    public void clickEmailField() {
        driver.findElement(email).click();
    }

    public void clickPasswordField() {
        driver.findElement(password).click();
    }

    public void clickConfirmPasswordField() {
        driver.findElement(confirmPassword).click();
    }

    // Verification methods
    public boolean isCreateAccountTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleCreateAccount)).isDisplayed();
    }

    public boolean isUsernameRequiredMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredMsg)).isDisplayed();
    }

    public boolean isUsernameTooShortMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLongerMsg)).isDisplayed();
    }

    public boolean isUsernameExistsMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameExistsMsg)).isDisplayed();
    }

    public boolean isEmailRequiredMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailRequiredMsg)).isDisplayed();
    }

    public boolean isEmailInvalidMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailInvalidMsg)).isDisplayed();
    }

    public boolean isPasswordRequiredMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRequiredMsg)).isDisplayed();
    }

    public boolean isConfirmPasswordRequiredMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordRequiredMsg)).isDisplayed();
    }

    public boolean isPasswordNotMatchMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordNotMatchMsg)).isDisplayed();
    }

    public boolean isPasswordTooShortMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordShortMsg)).isDisplayed();
    }

    public boolean isPasswordLowerLetterMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLowerLetterMsg)).isDisplayed();
    }

    public boolean isPasswordUpperLetterMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordUpperLetterMsg)).isDisplayed();
    }

    public boolean isPasswordTooLongMsgVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLongMsg)).isDisplayed();
    }

    public String getPasswordFieldType() {
        return driver.findElement(password).getAttribute("type");
    }

    public String getConfirmPasswordFieldType() {
        return driver.findElement(confirmPassword).getAttribute("type");
    }
}
