package com.example.proba;

public class ReccomendationData {
    String userTo;
    String bookId;
    String bookAuthor;
    String bookName;
    int bookImage;
    String userFrom;

    ReccomendationData(String userTo, String bookId, String bookAuthor, String bookName, int bookImage, String userFrom){
        this.userTo=userTo;
        this.bookId=bookId;
        this.bookName=bookName;
        this.bookAuthor=bookAuthor;
        this.bookImage=bookImage;
        this.userFrom=userFrom;
    }
}
