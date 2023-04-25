import com.codeborne.pdftest.assertj.Assertions;
import org.googlenotauthorized.SignInPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SignInPageTest {

    private String createAccountLink = "https://accounts.google.com/v3/signin/identifier?dsh=S1477041361%3A1675765131384446&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAmgQ&hl=uk&passive=true&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHd7r039xISXLNyW9iuaLW1qrLpevDeEGqLaFBFEWmwPTNUicNIdMPzSuXBPIxezN65F4E8wUg";
    private String forgotEmailLink = "https://accounts.google.com/signin/v2/usernamerecovery";
    private SignInPage signInPage;
    private static final Logger LOGGER = Logger.getLogger(SignInPageTest.class.getName());

    @BeforeClass
    public void setUp(){
        LOGGER.info("Test class is started");
    }

    @BeforeMethod
    public void setUpEach(){
        System.setProperty("selenide.browser", "Chrome");
        open("https://accounts.google.com/v3/signin/identifier?dsh=S1477041361%3A1675765131384446&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAmgQ&hl=uk&passive=true&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHd7r039xISXLNyW9iuaLW1qrLpevDeEGqLaFBFEWmwPTNUicNIdMPzSuXBPIxezN65F4E8wUg");
        getWebDriver().manage().window().maximize();
        signInPage = new SignInPage();
    }

    @Test
    public void checkPageHeadingContainsCorrectPageName(){
        String actualResult = signInPage.getHeading();
        String expectedResult = "Увійти";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkErrorMessageForSubmittingFormWithInvalidEmail(){
        String actualResult = signInPage.getErrorMessageForInvalidEmail("test@@@@");
        String expectedResult = "Введіть дійсні електронну адресу або номер телефону";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkErrorMessageForSubmittingFormWithEmptyEmail(){
        String actualResult = signInPage.getErrorMessageForEmptyEmail("");
        String expectedResult = "Введіть електронну адресу або номер телефону";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlAfterClickingForgotEmailButton(){
        signInPage.forgotEmailButtonClick();
        String actualResult = url();
        String expectedResult = forgotEmailLink;
        Assertions.assertThat(actualResult.contains(expectedResult));
    }

    @Test
    public void checkUrlAfterClickingCreateAccountButton(){
        switchTo().window(0);
        signInPage.createAccountButtonClick();
        String actualResult = url();
        String expectedResult = createAccountLink;
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkTextOnOpenedPageAfterClickingMoreButton(){
        signInPage.moreButtonClick();
        Assertions.assertThat(title().contains("Як користуватися Chrome у режимі гостя"));
    }

    @Test
    public void checkTextOnOpenedPageAfterClickingMoreButtonWithSecondMethod(){
        signInPage.moreButtonClickSecondMethod();
        Assertions.assertThat(title().contains("Як користуватися Chrome у режимі гостя"));
    }

    @AfterClass
    public void tearDown(){
        LOGGER.info("Test class is finished");
    }

}
