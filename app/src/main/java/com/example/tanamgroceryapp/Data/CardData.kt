package com.example.tanamgroceryapp.Data

class CardData(
        var id: Int, var itemImage: Int, var itemFavorite: Boolean,
        var categoriesTitle: String, var discountPrice: Double, var originalPrice: Double,
        var itemName: String, var itemRating: Boolean, var ratingCounter: Int,
        var quantity: Int = 0, var subTotal: Double=0.0, var total: Double=0.0,
        var discount:String
) {

}