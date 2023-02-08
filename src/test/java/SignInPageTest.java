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
    public void checkHeading(){
        String actualResult = signInPage.getHeading();
        String expectedResult = "Увійти";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void enterInvalidEmail(){
        String actualResult = signInPage.getErrorInvalidForEmail("test@@@@");
        String expectedResult = "Введіть дійсні електронну адресу або номер телефону";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void enterEmptyEmail(){
        String actualResult = signInPage.getErrorEmptyForEmail("");
        String expectedResult = "Введіть електронну адресу або номер телефону";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void forgotEmailClick(){
        signInPage.forgotEmailButtonClick();
        String actualResult = url();
        String expectedResult = "https://accounts.google.com/signin/v2/usernamerecovery";
        Assertions.assertThat(actualResult.contains(expectedResult));
    }

    @Test
    public void createAccount(){
        switchTo().window(0);
        signInPage.createAccButtonClick();
        String actualResult = url();
        String expectedResult = "https://accounts.google.com/v3/signin/identifier?dsh=S1477041361%3A1675765131384446&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAmgQ&hl=uk&passive=true&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHd7r039xISXLNyW9iuaLW1qrLpevDeEGqLaFBFEWmwPTNUicNIdMPzSuXBPIxezN65F4E8wUg";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void clickMoreButton(){
        signInPage.moreButtonClick();
        Assertions.assertThat(title().contains("Як користуватися Chrome у режимі гостя"));
    }

    @Test
    public void clickMoreButtonSecondMethod(){
        signInPage.moreButtonClickSecondMethod();
        Assertions.assertThat(title().contains("Як користуватися Chrome у режимі гостя"));
    }

    @AfterClass
    public void tearDown(){
        LOGGER.info("Test class is finished");
    }

}
