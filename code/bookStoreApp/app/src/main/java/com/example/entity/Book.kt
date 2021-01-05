package com.example.entity

import android.os.Parcel
import android.os.Parcelable

class Book(var bookId: Int, var bookName: String,
           var bookLabel: String, var bookPrice: Double,
           var bookAuthor: String, var bookPress: String,
           var bookDetail: String, var bookCoverUrl: String) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookId)
        parcel.writeString(bookName)
        parcel.writeString(bookLabel)
        parcel.writeDouble(bookPrice)
        parcel.writeString(bookAuthor)
        parcel.writeString(bookPress)
        parcel.writeString(bookDetail)
        parcel.writeString(bookCoverUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }


}
//class User(): Parcelable{
//    private var userId:Int = 0
//    private var username:String? = ""
//    private var password:String? = ""
//
//    constructor(parcel: Parcel) : this() {
//        userId = parcel.readInt()
//        username = parcel.readString()
//        password = parcel.readString()
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(userId)
//        parcel.writeString(username)
//        parcel.writeString(password)
//
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun toString(): String {
//        return "User(userId=$userId, username=$username, password=$password)"
//    }
//
//    companion object CREATOR : Parcelable.Creator<User> {
//        override fun createFromParcel(parcel: Parcel): User {
//            return User(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//
//}