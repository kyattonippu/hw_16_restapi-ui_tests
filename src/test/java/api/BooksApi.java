package api;

import api.models.AddBookBodyModel;
import api.models.AddBookResponse;
import api.models.BookCollectionResponse;
import api.models.ListOfIsbns;

import java.util.ArrayList;

import static data.ApiEndpoints.BOOKS;
import static io.restassured.RestAssured.given;
import static api.specs.Specifications.*;

public class BooksApi {
    public static void clearBooksCollection(String token, String userId) {
        given(request)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete(BOOKS)
                .then()
                .spec(successDeleteBooksResponse204);
    }
    public static BookCollectionResponse requestBookCollection() { BookCollectionResponse collection =
            given(request)
                    .when()
                    .get("/BookStore/v1/Books")
                    .then()
                    .spec(successBookCollectionResponse200)
                    .extract().as(BookCollectionResponse.class);
        return collection;
    }

    public static AddBookResponse addBookToCollection(String isbn, String token, String userId) {

        ArrayList books = new ArrayList<>();
        books.add(new ListOfIsbns(isbn));

        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return
                given(request)
                        .header("Authorization", "Bearer " + token)
                        .body(bookData)
                        .when()
                        .post(BOOKS)
                        .then()
                        .spec(successAddBooksResponse201)
                        .extract().as(AddBookResponse.class);
    }




}
