package com.example.tanamgroceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shipping_address.*

class ShippingAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)
        setSupportActionBar(findViewById(R.id.saToolbar))

        val sabackBtn=findViewById<ImageButton>(R.id.saBackBtn)

        sabackBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shippingaddress, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saItem1 -> Toast.makeText(this, "Item 1 seleted", Toast.LENGTH_LONG).show()
            R.id.saItem2 -> Toast.makeText(this, "Item 2 seleted", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

}