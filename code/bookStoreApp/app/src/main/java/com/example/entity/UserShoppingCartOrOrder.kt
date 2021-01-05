package com.example.entity

class UserShoppingCartOrOrder (val userId:Int, val bookId:Int,val bookPrice:Double){
    override fun toString(): String {
        return "{\"userId\":$userId,\"bookId\":$bookId}"
    }
}

