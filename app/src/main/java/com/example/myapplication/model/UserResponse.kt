package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
class UserResponse {
    @SerializedName("data")
    @Expose
    var data:User? = null

    class User {
        @SerializedName("username")
        @Expose
        var username:String?=null

        @SerializedName("priviledge")
        @Expose
        var priviledge:String?=null

        @SerializedName("token")
        @Expose
        var token:String?=null
    }
} */
class UserResponse {
    @SerializedName("username")
    @Expose
    var username:String?=null

    @SerializedName("priviledge")
    @Expose
    var priviledge:String?=null

    @SerializedName("token")
    @Expose
    var token:String?=null

}