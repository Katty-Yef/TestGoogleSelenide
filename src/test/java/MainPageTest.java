import com.codeborne.pdftest.assertj.Assertions;
import org.googlenotauthorized.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPageTest {

    private MainPage mainPage;
    private static final Logger LOGGER = Logger.getLogger(MainPageTest.class.getName());

    @BeforeClass
    public void setUp(){
        LOGGER.info("Test class is started");
    }
    @BeforeMethod
    public void setUpEach(){
        System.setProperty("selenide.browser", "Chrome");
        open("https://www.google.com/");
        getWebDriver().manage().window().maximize();

        mainPage = new MainPage();
    }

    @Test
    public void logoIsShown(){
        boolean expectedResult = true;
        boolean actualResult = mainPage.logoImageState();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void gmailIsOpened(){
        String expectedResult = "https://www.google.com/intl/uk/gmail/about/";
        mainPage.gmailButtonClick();
        mainPage.waitUntilElementDisappear(mainPage.getGmailButton(), 5);
        String actualResult = url();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void imagesIsOpened(){
        String expectedResult = "https://www.google.com.ua/imghp?hl=uk&ogbl";
        mainPage.imagesButtonClick();
        mainPage.waitUntilElementDisappear(mainPage.getImagesButton(), 5);
        String actualResult = url();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void menuIsSelected(){
        boolean actualResult = mainPage.getMenuStateAfterClick();
        boolean expectedResult = true;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void loginPageIsOpened(){
        mainPage.loginButtonClick();
        mainPage.waitUntilElementDisappear(mainPage.getLoginButton(), 5);
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void searchForSmth(){
        mainPage.searchWithEnter("test");
        mainPage.waitUntilElementDisappear(mainPage.getLogoImage(), 5);
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void searchButtonWithoutText(){
        mainPage.searchButtonClick();
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void searchWithButton(){
        mainPage.searchWithButton("test2");
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void clearSearchFieldWithText(){
        String actualResult = mainPage.searchFieldEnterText("test3").clearButtonClick();
        String expectedResult = null;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void clearSearchFieldWithoutText(){
        String actualResult = mainPage.clearButtonClick();
        String expectedResult = "";
        Assert.assertNotEquals(actualResult,expectedResult);
    }

    @Test(invocationCount = 10)
    public void searchWithImage(){
        mainPage.searchWithImage("C:\\Users\\maksy\\Kate\\cat.jpg");
        mainPage.waitUntilElementDisappear(mainPage.getImagesButton(), 5);
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assertions.assertThat(actualResult).isNotEqualTo(expectedResult);
    }

    @Test
    public void luckySearch(){
        mainPage.luckyButtonClick();
        boolean actualResult = url().contains("https://www.google.com/");
        boolean expectedResult = true;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @AfterClass
    public void tearDown(){
        LOGGER.info("Test class is finished");
    }

}
