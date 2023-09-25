package io.testomat.ui.selenide.common;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AssertionModeExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        Configuration.assertionMode = AssertionMode.STRICT;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        if (context.getDisplayName().toLowerCase().contains("soft")) {
            Configuration.assertionMode = AssertionMode.SOFT;
        }
    }
}
