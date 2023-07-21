import org.googlenotauthorized.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SignInPageTest extends BaseClass<SignInPage> {

    private String createAccountLink = "https://accounts.google.com/v3/signin/identifier?dsh=S1477041361%3A1675765131384446&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAmgQ&hl=uk&passive=true&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHd7r039xISXLNyW9iuaLW1qrLpevDeEGqLaFBFEWmwPTNUicNIdMPzSuXBPIxezN65F4E8wUg";
    private String forgotEmailLink = "https://accounts.google.com/signin/v2/usernamerecovery";
    private static String signInLink = "https://accounts.google.com/v3/signin/identifier?dsh=S1477041361%3A1675765131384446&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAmgQ&hl=en&passive=true&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHd7r039xISXLNyW9iuaLW1qrLpevDeEGqLaFBFEWmwPTNUicNIdMPzSuXBPIxezN65F4E8wUg";

    public SignInPageTest(){
        super(signInLink);
    }

    @Override
    protected SignInPage createPageInstance() {
        return new SignInPage();
    }

    @Test
    public void checkPageHeadingContainsCorrectPageName() {
        String actualResult = page.getHeading();
        String expectedResult = "Sign in";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkErrorMessageForSubmittingFormWithInvalidEmail() {
        String actualResult = page.getErrorMessageForInvalidEmail(faker.internet().emailAddress().replace(".", "")); //trying email address without dot
        String expectedResult = "Enter a valid email or phone number";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkErrorMessageForSubmittingFormWithEmptyEmail() {
        String actualResult = page.getErrorMessageForEmptyEmail("");
        String expectedResult = "Enter an email or phone number";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlAfterClickingForgotEmailButton() {
        page.forgotEmailButtonClick();
        String actualResult = url();
        String expectedResult = forgotEmailLink;
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void checkUrlAfterClickingCreateAccountButton() {
        page.createAccountButtonClick();
        String actualResult = url();
        String expectedResult = createAccountLink;
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkTextOnOpenedPageAfterClickingMoreButton() {
        page.moreButtonClick();
        switchTo().window(1);
        Assert.assertTrue(title().contains("Browse Chrome as a guest"));
        closeWindow();
    }

    @Test
    public void checkTextOnOpenedPageAfterClickingMoreButtonWithSecondMethod() {
        page.moreButtonClickSecondMethod();
        switchTo().window(1);
        Assert.assertTrue(title().contains("Browse Chrome as a guest"));
        closeWindow();
    }
}
