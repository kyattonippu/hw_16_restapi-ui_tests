package tests;

import api.AuthApi;
import api.BooksApi;
import helpers.WithLogin;
import api.models.BookCollectionResponse;
import api.models.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;

public class DemoqaTests extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    private final int NULL = 0;


    @Test
    @Tag("API")
    @DisplayName("Deleting book from Demoqa via API and UI")
    @WithLogin
    void deletingBookUsingApiAndUiTest() {
        BookCollectionResponse collection = BooksApi.requestBookCollection();

        LoginResponse authResponse =
        step("API Login request", AuthApi::authResponse
        );

        step("Clear book collection via API", () ->
               BooksApi.clearBooksCollection(authResponse.getToken(), authResponse.getUserId())
        );

        step("Add book to collection via API", () ->
                BooksApi.addBookToCollection(collection.getBooks()[NULL].getIsbn(), authResponse.getToken(), authResponse.getUserId())
        );

        step("Accept cookie", () ->
            profilePage.clickOnCookieConsentIfDisplayed()
        );

        step("Open profile", () ->
             profilePage.openProfilePage()
        );

        step("Check that book was added to collection", () ->
              profilePage.checkBookInCollection()
        );

        step("Delete book from collection via UI", () ->
             profilePage.deleteBookFromCollection()
        );

        step("Confirm book deletion", () ->
             profilePage.confirmDelete()
        );

        step("Check that book was deleted from collection", () ->
             profilePage.checkTableBody(collection.getBooks()[NULL].getTitle())
        );
    }
}
