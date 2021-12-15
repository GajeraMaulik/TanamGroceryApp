package com.example.tanamgroceryapp.Data

import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout

class PopularDealsData(itemImage: Int, itemFavorite: Boolean, itemName: String, itemPrice: String, itemRating: Boolean, ratingCounter: Int, txtQuantity: Int,subTotal:Double,total:Double) {
    var itemImage:Int?= itemImage
    var itemFavorite:Boolean?= itemFavorite
    var itemName:String?= itemName
    var itemPrice:String?= itemPrice
    var itemRating:Boolean?= itemRating
    var ratingCounter:Int?= ratingCounter
    var txtQuantity:Int?= txtQuantity
    var subTotal:Double?=subTotal
    var total:Double?=total

}