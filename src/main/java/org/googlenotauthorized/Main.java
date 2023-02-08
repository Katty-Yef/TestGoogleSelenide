package org.googlenotauthorized;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class Main {
    public static void main(String[] args) {

        System.setProperty("selenide.browser", "Chrome");
        open("https://google.com/");

        SelenideElement searchByImageButton = $("div.nDcEnd");
        SelenideElement uploadButton = $x("//form/input[@type='file']");

        searchByImageButton.click();
        File file = new File("C:\\Users\\maksy\\Kate\\cat.jpg");
        uploadButton.uploadFile(file);

    }
}