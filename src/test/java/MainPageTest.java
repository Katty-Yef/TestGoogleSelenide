import com.codeborne.pdftest.assertj.Assertions;
import org.googlenotauthorized.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPageTest extends BaseClass<MainPage>{

    private static String urlGoogleStartPage = "https://www.google.com/";

    public MainPageTest() {
        super(urlGoogleStartPage);
    }

    @Override
    protected MainPage createInstance(){
        return new MainPage();
    }

    @Test
    public void checkLogoIsShown(){
        boolean expectedResult = true;
        boolean actualResult = page.getLogoImageState();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void CheckUrlForGmailAfterClickingGmailButton(){
        page.gmailButtonClick();
        String actualResult = url();
        Assert.assertTrue(actualResult.contains(urlGoogleStartPage));
        Assert.assertTrue(actualResult.contains("/gmail/about/"));
    }

    @Test
    public void checkUrlForImagesSearchAfterClickingImagesButton(){
        page.imagesButtonClick();
        String actualResult = url();
        Assert.assertTrue(actualResult.contains("imghp"));
    }

    @Test
    public void checkMenuWidgetIsExpandedAfterSelectingIt(){
        page.menuButtonClick();
        boolean actualResult = page.getMenuState();
        boolean expectedResult = true;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlForLoginPageIsOpenedAfterSelectingLogin(){
        page.loginButtonClick();
        String actualResult = url();
        String expectedResult = urlGoogleStartPage;
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlForSearchAfterEnteringTextAndPressingEnter(){
        page.pressEnterForSearchFiled("test");
        String actualResult = url();
        String expectedResult = urlGoogleStartPage;
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlIsNotChangedAfterClickingSearchButtonWithoutText(){
        page.searchButtonClick();
        String actualResult = url();
        String expectedResult = urlGoogleStartPage;
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUrlForSearchAfterEnteringTextAndClickingSearchButton(){
        page.clickSearchButtonForSearchField("test2");
        String actualResult = url();
        String expectedResult = urlGoogleStartPage;
        Assert.assertNotEquals(actualResult, expectedResult);
    }

    @Test
    public void checkSearchQueryIsClearedAfterSelectingClearButtonWithEnteredText(){
        String actualResult = page.enterTextToSearchField("test3").clearButtonClick();
        String expectedResult = "";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkClearButtonIsNotShownIfNoTextEnteredToSearchField(){
        Boolean actualResult = page.getClearButtonState();
        Boolean expectedResult = false;
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(invocationCount = 5)
    public void checkUrlIsChangedAfterSearchingWithImage(){
        page.uploadFileForSearchWithImage("cat.jpg");
        page.waitUntilElementDisappear(page.getImagesButton(), 5);
        String actualResult = url();
        String expectedResult = urlGoogleStartPage;
        Assertions.assertThat(actualResult).isNotEqualTo(expectedResult);
    }

    @Test
    public void checkUrlIsChangedAfterSelectingLuckySearchButton(){
        page.luckyButtonClick();
        boolean actualResult = url().contains(urlGoogleStartPage);
        boolean expectedResult = true;
        Assert.assertEquals(actualResult, expectedResult);
    }

}
