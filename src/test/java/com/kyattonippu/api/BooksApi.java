package com.kyattonippu.api;

import com.kyattonippu.api.models.AddListOfBooksRequest;
import com.kyattonippu.api.models.AddListOfBooksResponse;
import com.kyattonippu.api.models.BookCollectionResponse;
import com.kyattonippu.api.models.IsbnBookModel;

import java.util.ArrayList;

import static com.kyattonippu.api.specs.Specifications.*;
import static com.kyattonippu.data.ApiEndpoints.BOOKS;
import static io.restassured.RestAssured.given;

public class BooksApi {

    public static void clearBookCollection(String token, String userId) {

        given(request)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(BOOKS)
                .then()
                .spec(responseSpec204);
    }

    public static BookCollectionResponse requestBookCollection() {
        BookCollectionResponse collection =
                given(request)
                        .when()
                        .get(BOOKS)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(BookCollectionResponse.class);
        return collection;
    }

    public static AddListOfBooksResponse addBookToCollection(String isb, String token, String userId) {

        ArrayList books = new ArrayList<>();
        books.add(new IsbnBookModel(isb));

        AddListOfBooksRequest bookData = new AddListOfBooksRequest();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return
                given(request)
                        .header("Authorization", "Bearer " + token)
                        .body(bookData)
                        .when()
                        .post(BOOKS)
                        .then()
                        .spec(responseSpec201)
                        .extract().as(AddListOfBooksResponse.class);
    }
}