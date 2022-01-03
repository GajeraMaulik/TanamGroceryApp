package com.example.tanamgroceryapp

import android.app.Application
import com.example.tanamgroceryapp.Data.CardData

class ApplicationInitialize: Application() {

    companion object{
        var mShoppingCart: HashMap<Int, CardData> = HashMap()
    }


}