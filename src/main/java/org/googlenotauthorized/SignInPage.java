package org.googlenotauthorized;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SignInPage {

    private SelenideElement emailField = $x("//input[@id='identifierId']");
    private SelenideElement createAccButton = $x("//div[@class='ZOeJnf']//button//span");
    private SelenideElement emailUsageDropdown = $x("//div[@class='dqyqtf']//ul/li[1]");
    private SelenideElement nextButtonForEmail = $x("//div[@id='identifierNext']//button");
    private SelenideElement errorMessageForInvalidEmail = $x("//div[@class='LXRPh']//div[@class='o6cuMc']");
    private SelenideElement heading = $x("//h1[@id='headingText']");
    private SelenideElement forgotEmailButton = $x("//div[@class='PrDSKc']/button");
    private SelenideElement errorMessageForEmptyEmail = $x("//div[@class='o6cuMc']");
    private SelenideElement moreButton = $x("//span//a");


    public String getHeading(){
        String header = heading.getText();
        return header;
    }

    public void typeEmail(String email){
        emailField.sendKeys(email);
    }

    public void enterEmail(String email){
        typeEmail(email);
        nextButtonForEmail.click();
    }

    public String getErrorInvalidForEmail(String email){
        enterEmail(email);
        return errorMessageForInvalidEmail.getText();
    }

    public String getErrorEmptyForEmail(String email){
        enterEmail(email);
        return errorMessageForEmptyEmail.getText();
    }

    public void forgotEmailButtonClick(){
        forgotEmailButton.click();
    }

    public void createAccButtonClick(){
        createAccButton.click();
        emailUsageDropdown.click();
    }

    public void moreButtonClick(){
        moreButton.click();
    }

    public void moreButtonClickSecondMethod(){
        moreButton.sendKeys(Keys.CONTROL, Keys.ENTER);
    }

}
