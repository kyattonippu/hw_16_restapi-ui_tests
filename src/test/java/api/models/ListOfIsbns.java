package api.models;

import lombok.Data;

@Data
public class ListOfIsbns {
    public ListOfIsbns(String isbn) {
        this.isbn = isbn;
    }
    private String isbn;
}
