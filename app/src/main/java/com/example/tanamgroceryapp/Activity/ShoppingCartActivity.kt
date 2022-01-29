package com.example.tanamgroceryapp.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.Adapter.ShoppingCartAdapter
import com.example.tanamgroceryapp.ApplicationInitialize
import com.example.tanamgroceryapp.Constants
import com.example.tanamgroceryapp.Interfaces.ItemClickListner
import com.example.tanamgroceryapp.R
import com.google.gson.Gson

class ShoppingCartActivity : AppCompatActivity(), ItemClickListner {
    private lateinit var rvShoppingCart: RecyclerView
    private lateinit var tvSubTotal: TextView
    private lateinit var tvTaxRate: TextView
    private lateinit var tvTotalPrice: TextView
    lateinit var cardData: CardData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shopping_cart)
        setSupportActionBar(findViewById(R.id.scToolbar))

        val scbackBtn = findViewById<ImageButton>(R.id.scbackBtn)
        val checkoutBtn=findViewById<Button>(R.id.checkoutBtn)
        tvSubTotal = findViewById(R.id.subTotal)
        tvTaxRate = findViewById(R.id.taxRate)
        tvTotalPrice = findViewById(R.id.totalPrice)


        scbackBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener
        }


        Log.d("cart value ", "-----" + Gson().toJson(ApplicationInitialize.mShoppingCart))

        rvShoppingCart = findViewById(R.id.rvShoppingcart)

        val cartItemList: MutableList<CardData> = ArrayList()

        for (key in ApplicationInitialize.mShoppingCart.keys) {
            println("Element at key $key = ${ApplicationInitialize.mShoppingCart[key]}")
            ApplicationInitialize.mShoppingCart[key]?.let { cartItemList.add(it) }
        }

        checkoutBtn.setOnClickListener {
            if(ApplicationInitialize.mShoppingCart.isEmpty()){
                Toast.makeText(this,"Please Add Some Item into Cart",Toast.LENGTH_LONG).show()
            }else {
                val i = Intent(this, ShippingAddressActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        Log.d("cartItemList value ", "-----" + Gson().toJson(cartItemList))
        rvShoppingCart.adapter = ShoppingCartAdapter(cartItemList, this, this)
        total()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("menu", "menu")
        menuInflater.inflate(R.menu.menu_shoppingcart, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.scItem1 -> Toast.makeText(this, "Item 1 seleted", Toast.LENGTH_LONG).show()
            R.id.scItem2 -> Toast.makeText(this, "Item 2 seleted", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickItem(pos: Int) {
        total()
        // Toast.makeText(this, "Itemc: $pos", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("SetTextI18n")
    private fun total() {
        var subTotal=0.0
        var totalPrice=0.0
        val tax = 1.08
        var quantity = 0

        for (key in ApplicationInitialize.mShoppingCart.keys) {
            println("Element at key $key = ${ApplicationInitialize.mShoppingCart[key]}")
            ApplicationInitialize.mShoppingCart[key]?.let {
                quantity += it.quantity
                subTotal += it.quantity * it.discountPrice
                totalPrice += tax + subTotal

            }
        }
        tvSubTotal.text = getString(R.string.dollar)+ " " + Constants.roundPoint(subTotal, 2)
        tvTotalPrice.text = getString(R.string.dollar)+ " " + Constants.roundPoint(totalPrice, 2)

    }


}