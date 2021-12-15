package com.example.tanamgroceryapp

import android.graphics.Paint
import android.widget.TextView
import com.example.tanamgroceryapp.Data.ShoppingCartData

object Constants {
    fun setStrike(text: TextView, value: String) {

        text.paintFlags = text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
    val shoppingList: MutableList<ShoppingCartData> = ArrayList()
}
