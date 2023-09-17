package io.testomat.ui.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class BasePage {

    private static final String baseDeviceType = "desktop";

    public SelenideElement find(String childSelector) {
        return $("#content-" + baseDeviceType + " " + childSelector);
    }

    public ElementsCollection findElements(String childSelector) {
        return $$("#content-" + baseDeviceType + " " + childSelector);
    }

}
