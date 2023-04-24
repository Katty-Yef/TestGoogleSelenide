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
    private SelenideElement gmailButton = $x("//div[@class='gb_q gb_r'][1]/a");
    private SelenideElement imagesButton = $x("//div[@class='gb_q gb_r'][2]/a");
    private SelenideElement menuButton = $x("//div[@id='gbwa']");
    private SelenideElement iframe = $x("//iframe[@name='app']");
    private SelenideElement loginButton = $x("//div[@class='gb_We']/a");
    private SelenideElement searchField = $x("//input[@class='gLFyf']");
    private SelenideElement searchButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']");
    private SelenideElement clearButton = $x("//div[@class='vOY7J M2vV3']");
    private SelenideElement searchByImageButton = $x("//div[@class='nDcEnd']");
    private SelenideElement uploadButton = $x("//form/input[@type='file']");
    private SelenideElement luckyButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@class='RNmpXc']");
    private SelenideElement emptySpace = $x("//div[@id='gb']");

    public boolean logoImageState(){
        boolean logoIsDisplayed = logoImage.isDisplayed();
        return logoIsDisplayed;
    }

    public void gmailButtonClick(){
        gmailButton.click();
        gmailButton.should(Condition.disappear);
    }

    public void imagesButtonClick(){
        imagesButton.click();
        imagesButton.should(Condition.disappear);
    }

    public boolean getMenuStateAfterClick(){
        menuButton.click();
        boolean iframeIsOpened = iframe.isEnabled();
        return iframeIsOpened;
    }

    public SignInPage loginButtonClick(){
        loginButton.click();
        return new SignInPage();
    }

    public void waitUntilElementDisappear(SelenideElement element, int duration) {
        element.should(Condition.disappear, Duration.ofSeconds(duration));
    }

    public MainPage searchFieldEnterText(String text){
        searchField.val(text);
        return this;
    }

    public MainPage searchWithEnter(String text){
        searchFieldEnterText(text);
        searchField.sendKeys(Keys.ENTER);
        return new MainPage();
    }

    public MainPage searchButtonClick(){
        searchButton.click();
        return new MainPage();
    }

    public MainPage searchWithButton(String text){
        searchFieldEnterText(text);
        emptySpace.click();
        searchButtonClick();
        return new MainPage();
    }

    public String clearButtonClick(){
//        if (!(searchField.text().equals(""))){
//            clearButton.click();
//            return searchField.text();
//        }
//        else return null;
        if (getClearButtonState() == true) {
            clearButton.click();
            return searchField.text();
        }
        else return null;
    }

    public boolean getClearButtonState(){
        if (clearButton.isDisplayed())
            return true;
        else return false;
    }

    public MainPage searchByImageClick(){
        searchByImageButton.click();
        return this;
    }

    public void searchWithImage(String filePath){
        searchByImageClick();
        File file = new File(filePath);
        uploadButton.uploadFile(file);
    }

    public String luckyButtonClick(){
        luckyButton.click();
        return url();
    }

}
