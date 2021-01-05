package com.example.entity;

/*
两个表一个是购物车中的
create table bookShoppingCart(
    userId int,
    bookId int,
    primary key (userId, bookId),
    foreign key (userId) references user(userId),
    foreign key (bookId) references book(bookId)
)
一个是购买列表中的
create table bookOrder(
    userId int,
    bookId int,
    primary key (userId, bookId),
    foreign key (userId) references user(userId),
    foreign key (bookId) references book(bookId)
)
*/

public class UserShoppingCartOrOrder {
    private Integer userId;
    private Integer bookId;

    public UserShoppingCartOrOrder(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
