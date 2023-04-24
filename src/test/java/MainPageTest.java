import com.codeborne.pdftest.assertj.Assertions;
import org.googlenotauthorized.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
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
        getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getWebDriver().manage().window().maximize();

        mainPage = new MainPage();
    }

    @Test
    public void checkLogoIsShown(){
        boolean expectedResult = true;
        boolean actualResult = mainPage.logoImageState();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void CheckUrlForGmailAfterClickingGmailButton(){
        String expectedResult = "https://www.google.com/intl/uk/gmail/about/";
        mainPage.gmailButtonClick();
        mainPage.waitUntilElementDisappear(mainPage.getGmailButton(), 5);
        String actualResult = url();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlForImagesSearchAfterClickingImagesButton(){
        String expectedResult = "https://www.google.com.ua/imghp?hl=uk&ogbl";
        mainPage.imagesButtonClick();
        mainPage.waitUntilElementDisappear(mainPage.getImagesButton(), 5);
        String actualResult = url();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkMenuWidgetIsExpandedAfterSelectingIt(){
        boolean actualResult = mainPage.getMenuStateAfterClick();
        boolean expectedResult = true;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlForLoginPageIsOpenedAfterSelectingLogin(){
        mainPage.loginButtonClick();
        mainPage.waitUntilElementDisappear(mainPage.getLoginButton(), 5);
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlForSearchAfterEnteringTextAndPressingEnter(){
        mainPage.searchWithEnter("test");
        mainPage.waitUntilElementDisappear(mainPage.getLogoImage(), 5);
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlIsNotChangedAfterClickingSearchButtonButtonWithoutText(){
        mainPage.searchButtonClick();
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlIsChangedAfterClickingSearchButtonWithEnteredText(){
        mainPage.searchWithButton("test2");
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkSearchQueryIsClearedAfterSelectingClearButtonWithEnteredText(){
        String actualResult = mainPage.searchFieldEnterText("test3").clearButtonClick();
        String expectedResult = null;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkClearButtonIsNotShownIfNoTextEnteredToSearchField(){
        Boolean actualResult = mainPage.getClearButtonState();
        Boolean expectedResult = false;
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(invocationCount = 10)
    public void checkUrlIsChangedAfterSearchingWithImage(){
        mainPage.searchWithImage("C:\\Users\\maksy\\Kate\\cat.jpg");
        mainPage.waitUntilElementDisappear(mainPage.getImagesButton(), 5);
        String actualResult = url();
        String expectedResult = "https://www.google.com/";
        Assertions.assertThat(actualResult).isNotEqualTo(expectedResult);
    }

    @Test
    public void checkUrlIsChangedAfterSelectingLuckySearchButton(){
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
