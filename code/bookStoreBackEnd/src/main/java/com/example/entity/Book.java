package com.example.entity;


/*
create table book(
    bookId int primary key auto_increment,
    bookName varchar(100),
    bookLabel varchar(100),
    bookPrice double,
    bookAuthor varchar(100),
    bookPress varchar(100),
    bookDetail varchar(1000),
    bookCoverUrl varchar(100)
)



* */

public class Book {
    private Integer bookId;
    private String bookName;
    private String bookLabel;
    private String bookPrice;
    private String bookAuthor;
    private String bookPress;
    private String bookDetail;
    private String bookCoverUrl;

    public Book(Integer bookId, String bookName, String bookLabel, String bookPrice, String bookAuthor, String bookPress, String bookDetail, String bookCoverUrl) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookLabel = bookLabel;
        this.bookPrice = bookPrice;
        this.bookAuthor = bookAuthor;
        this.bookPress = bookPress;
        this.bookDetail = bookDetail;
        this.bookCoverUrl = bookCoverUrl;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookLabel() {
        return bookLabel;
    }

    public void setBookLabel(String bookLabel) {
        this.bookLabel = bookLabel;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }
}
