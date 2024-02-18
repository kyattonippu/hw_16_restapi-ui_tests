package com.kyattonippu.tests;

import com.kyattonippu.api.AuthApi;
import com.kyattonippu.api.BooksApi;
import com.kyattonippu.api.models.BookCollectionResponse;
import com.kyattonippu.helpers.WithLogin;
import com.kyattonippu.api.models.LoginResponse;
import com.kyattonippu.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;

public class DemoqaTests extends TestBase {

    private final int NULL = 0;
    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("API")
    @DisplayName("Удаление книги из коллекции пользователя")
    @WithLogin
    void deleteBookFromCollectionTest () {

        BookCollectionResponse collection = BooksApi.requestBookCollection();
        LoginResponse authResponse =
                step("API Login request", AuthApi::authResponse
                );
        step("Clear collection of books via API", () ->
                BooksApi.clearBookCollection(authResponse.getToken(), authResponse.getUserId())
        );

        step("Add book to collection via API", () ->
                BooksApi.addBookToCollection(collection.getBooks()[NULL].getIsbn(), authResponse.getToken(), authResponse.getUserId())
        );
        step("Confirm сookie, open profile", () ->
                profilePage.googleConsent()
                        .openPage()
        );
        step("Check that collection is not empty", () ->
                profilePage.checkForBook()
        );
        step("Delete book from collection via UI", () ->
                profilePage.deleteBook()
        );
        step("Confirm book deletion", () ->
                profilePage.confirmDelete()
        );
        step("Check that collection is empty.", () ->
                profilePage.checkTableBody(collection.getBooks()[NULL].getTitle())
        );

    }

}