package io.testomat.playwright;

public abstract class BasePage {

    private static final String baseDeviceType = "desktop";

    public PlaywrightElement findDeviceSpecific(String childSelector) {
        return PlaywrightWrapper.find("#content-" + baseDeviceType + " " + childSelector);
    }

}
