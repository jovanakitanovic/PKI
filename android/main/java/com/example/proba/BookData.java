package com.example.proba;

import java.util.ArrayList;
import java.util.LinkedList;

public class BookData {
    String bookName;
    String bookAuthor;
    int image;
    String opis;
    int ID;
    int numPages;
    int year;
    LinkedList<CommentsData> comments;

    BookData(String bookName,String bookAuthor, int imege,String opis,int ID,int numPages, int year){
        this.bookName=bookName;
        this.bookAuthor=bookAuthor;
        this.image=imege;
        this.ID=ID;
        this.opis=opis;
        this.numPages=numPages;
        this.year=year;
    }
}
