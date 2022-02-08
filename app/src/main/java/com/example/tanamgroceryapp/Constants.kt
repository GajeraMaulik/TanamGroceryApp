package com.example.tanamgroceryapp

import android.graphics.Paint
import android.util.Log
import android.widget.TextView
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.Data.ShoppingCartData
import java.math.RoundingMode
import java.text.DecimalFormat

object Constants {
    fun setStrike(text: TextView, value: String) {

        text.paintFlags = text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    //  val shoppingList: MutableList<ShoppingCartData> = ArrayList()


    fun roundPoint(input: Double, places: Int): String {
        val df: DecimalFormat = when (places) {
            2 -> {
                DecimalFormat("0.00")
            }
            3 -> {
                DecimalFormat("0.000")
            }
            4 -> {
                DecimalFormat("0.0000")
            }
            else -> {
                DecimalFormat("0.00")
            }
        }
        df.roundingMode = RoundingMode.HALF_UP
        Log.d("roundPoint 1:  ", df.format(input))
        return df.format(input)
    }

    fun capitalizeWords(str: String): String {
        val words = str.split(" ").toMutableList()

        var output = ""

        if(words.size>1){
            output=words[0].capitalize()+" "+ words[1].capitalize()[0]+"."
        }else{
            output=words[0].capitalize()
        }



      /*  for (word in words) {
            output += word.capitalize() + " "
        }*/

        output = output.trim()
        return output
    }
}
