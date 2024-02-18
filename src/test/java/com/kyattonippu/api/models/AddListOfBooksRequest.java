package com.kyattonippu.api.models;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksRequest {
    String userId;
    List<IsbnBookModel> collectionOfIsbns;
}