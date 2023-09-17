package io.testomat.ui.playwright.pages;

import io.testomat.ui.playwright.PlaywrightElement;
import io.testomat.ui.playwright.PlaywrightWrapper;

public abstract class BasePage {

    private static final String baseDeviceType = "desktop";

    public PlaywrightElement findDeviceSpecific(String childSelector) {
        return PlaywrightWrapper.find("#content-" + baseDeviceType + " " + childSelector);
    }

}
