package io.testomat.ui.selenide.asserts;

import io.testomat.ui.common.data.BaseProjectInfo;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class ProjectsPageAsserts {

    private final BaseProjectInfo expectedProjectTile;

    public ProjectsPageAsserts hasCorrectInfo() {
        var baseTile = $(by("title", expectedProjectTile.getName()));

        baseTile.$("h3").shouldHave(text(expectedProjectTile.getName())
                                        .because("Expected project name is equal to " + expectedProjectTile.getName()));
        baseTile.$("p").shouldHave(text(String.valueOf(expectedProjectTile.getCount()))
                                       .because("Expected project count is equal to " + expectedProjectTile.getCount()), text("tests"));
        baseTile.$("img").shouldBe(visible
                                       .because("Expected project count is equal to " + expectedProjectTile.getCount()));
        baseTile.$("span").shouldHave(text(expectedProjectTile.getLabel().toString())
                                          .because("Expected project label is equal to " + expectedProjectTile.getLabel().toString()));
        return this;
    }

    public ProjectsPageAsserts isDeleted() {
        $("h3").shouldNotHave(text(expectedProjectTile.getName())
                                        .because("Project with name " + expectedProjectTile.getName() + "should be deleted"));
        return this;
    }

}
