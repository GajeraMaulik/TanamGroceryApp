package com.example.tanamgroceryapp.Data

import androidx.annotation.DrawableRes

class ImageData {
    var name: String
        private set


    //getters and setters
    var strImage: String? = null
        private set



    //optional @DrawableRes
    @DrawableRes
    var resId = 0
        private set

    constructor(name: String, resId: Int) {
        this.name = name
        this.resId = resId
    }

    constructor(name: String, strImage: String?) {
        this.name = name
        this.strImage = strImage.toString()
    }

    override fun toString(): String {
        return name
    }
}