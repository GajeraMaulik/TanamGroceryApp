package com.example.tanamgroceryapp.Data

class UserProfile {
   lateinit var  userName : String
    lateinit var email : String
    lateinit var password: String

    constructor(userName: String){
        this.userName = userName
    }
    constructor(userName: String,password: String){
        this.userName = userName
        this.password = password
    }
    constructor(userName:String,email:String,password:String){
        this.userName = userName
        this.email = email
        this.password = password
    }
}