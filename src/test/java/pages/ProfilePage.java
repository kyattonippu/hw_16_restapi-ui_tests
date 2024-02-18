package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.ApiEndpoints.PROFILE;

public class ProfilePage {
    final private SelenideElement deleteButton = $("#delete-record-undefined"),
            okButton = $("#closeSmallModal-ok"),
            bannerRoot = $(".fc-consent-root"),
            emptyRows = $(".rt-noData"),
            tableBody = $(".ReactTable");

    public ProfilePage openProfilePage() {
        open(PROFILE);
        return this;
    }

    public void clickOnCookieConsentIfDisplayed() {
        if (bannerRoot.isDisplayed()) {
            bannerRoot.$(byText("Consent")).click();
        }
    }

    public ProfilePage checkBookInCollection() {
        emptyRows.shouldNotBe(visible);
        return this;
    }

    public ProfilePage deleteBookFromCollection() {
        deleteButton.click();
        return this;
    }

    public ProfilePage confirmDelete() {
        okButton.click();
        Selenide.switchTo().alert().accept();
        Selenide.switchTo().parentFrame();
        return this;
    }

    public ProfilePage checkTableBody(String bookTitle) {
        open(PROFILE);
        tableBody.shouldNotHave(text(bookTitle));
        return this;
    }
}
