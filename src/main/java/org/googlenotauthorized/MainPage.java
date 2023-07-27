package org.googlenotauthorized;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@Getter
public class MainPage {
    private SelenideElement logoImage = $x("//img[@class='lnXdpd']");
    private SelenideElement gmailButton = $x("//div[1]/a[@class='gb_y']");
    private SelenideElement imagesButton = $x("//div[2]/a[@class='gb_y']");
    private SelenideElement menuButton = $x("//div[@id='gbwa']");
    private SelenideElement iframe = $x("//iframe[@name='app']");
    private SelenideElement loginButton = $x("//body/div/div/div/div/div/div/a");
    private SelenideElement searchField = $x("//textarea[@id='APjFqb']");
    private SelenideElement searchButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']");
    private SelenideElement clearButton = $x("//div[@class='vOY7J M2vV3']");
    private SelenideElement searchByImageButton = $x("//div[@class='nDcEnd']");
    private SelenideElement uploadButton = $x("//form/input[@type='file']");
    private SelenideElement luckyButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@class='RNmpXc']");
    private SelenideElement emptySpace = $x("//div[@id='gb']");

    public boolean getLogoImageState() {
        boolean logoIsDisplayed = logoImage.isDisplayed();
        return logoIsDisplayed;
    }

    public void gmailButtonClick() {
        gmailButton.click();
        gmailButton.should(Condition.disappear);
    }

    public void imagesButtonClick() {
        imagesButton.click();
        imagesButton.should(Condition.disappear);
    }

    public void menuButtonClick() {
        menuButton.click();
    }

    public boolean getMenuState() {
        boolean iframeIsOpened = iframe.isEnabled();
        return iframeIsOpened;
    }

    public SignInPage loginButtonClick() {
        loginButton.click();
        return new SignInPage();
    }

    public void waitUntilElementDisappear(SelenideElement element, int duration) {
        element.should(Condition.disappear, Duration.ofSeconds(duration));
    }

    public MainPage enterTextToSearchField(String text) {
        searchField.val(text);
        searchField.sendKeys(Keys.TAB);
        return this;
    }

    public MainPage pressEnterForSearchFiled(String text) {
        enterTextToSearchField(text);
        searchField.sendKeys(Keys.ENTER);
        return new MainPage();
    }

    public MainPage searchButtonClick() {
        searchButton.click();
        return new MainPage();
    }

    public MainPage clickSearchButtonForSearchField(String text) {
        enterTextToSearchField(text);
        searchButtonClick();
        return new MainPage();
    }

    public String clearButtonClick(){
        if (getClearButtonState() == true) {
            clearButton.click();
            return searchField.text();
        }
        else return null;
    }

    public boolean getClearButtonState() {
        Boolean clearButtonState = clearButton.isDisplayed();
        return clearButtonState;
    }

    public MainPage searchByImageButtonClick() {
        searchByImageButton.click();
        return this;
    }

    public void uploadFileForSearchWithImage(String filePath) {
        searchByImageButtonClick();
        File file = new File(filePath);
        uploadButton.uploadFile(file);
    }

    public String luckyButtonClick() {
        luckyButton.click();
        return url();
    }
}
