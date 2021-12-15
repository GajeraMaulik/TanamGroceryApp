package com.example.tanamgroceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.ShoppingCartData
import com.example.tanamgroceryapp.adapter.ShoppingCartAdapter
import kotlinx.android.synthetic.main.activity_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {
    private  lateinit var rvShoppingCart: RecyclerView
    private val itemList: MutableList<ShoppingCartData> = ArrayList()
    private lateinit var subTotal:TextView
    private lateinit var  taxRate:TextView
    private lateinit var totalPrice:TextView
    //  private lateinit var tax:Double
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        setSupportActionBar(toolbar)

        val scbackBtn=findViewById<ImageButton>(R.id.scbackBtn)
        scbackBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener
        }

        rvShoppingCart=findViewById(R.id.rvShoppingcart)
        rvShoppingCart.adapter=ShoppingCartAdapter(itemList)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shoppingcart,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting-> Toast.makeText(this,"Item 1 seleted",Toast.LENGTH_LONG).show()
            R.id.aboutUs-> Toast.makeText(this,"Item 2 seleted",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}