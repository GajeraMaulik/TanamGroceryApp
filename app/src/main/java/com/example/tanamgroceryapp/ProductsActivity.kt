package com.example.tanamgroceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.Adapter.ProductsAdapter
import com.example.tanamgroceryapp.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity(), ProductsAdapter.ClickListener {


    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.pToolbar)

        binding.pbackBtn.setOnClickListener {
            Log.d("maulik", "m")
            onBackPressed()
            return@setOnClickListener
        }

    }


    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        val productslist: MutableList<CardData> = ArrayList()
        productslist.add(
            CardData(
                7,
                R.drawable.item_product_tomatoes,
                true,
                "Fruit",
                5.0,
                8.9,
                "Fresh Tomatoes",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                8,
                R.drawable.item_product_grapes,
                true,
                "Fruit",
                5.0,
                8.9,
                "Red Grapes",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                9,
                R.drawable.item_product_watermelon,
                true,
                "Fruit",
                5.0,
                8.9,
                "Fresh Watermelon",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                10,
                R.drawable.item_product_orange,
                true,
                "Fruit",
                5.0,
                8.9,
                "Orange",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                11,
                R.drawable.item_product_pinnaple,
                true,
                "Fruit",
                5.0,
                8.9,
                "Pinnaple",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                12,
                R.drawable.item_product_fstrawberry,
                true,
                "Fruit",
                5.0,
                8.9,
                "Fresh Strawberry",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                13,
                R.drawable.item_product_localstrawberry,
                true,
                "Fruit",
                5.0,
                8.9,
                "Local Strawberry",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        productslist.add(
            CardData(
                14,
                R.drawable.item_product_saladstrawberry,
                true,
                "Fruit",
                5.0,
                8.9,
                "Salad Strawberry",
                true,
                0,
                0,
                0.0,
                0.0,
                "Disc. 10%Off"
            )
        )
        binding.rvProducts.adapter = ProductsAdapter(productslist, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_products, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> {
                startActivity(Intent(this, SearchFilterActivity::class.java))
            }
            R.id.pItem1 -> Toast.makeText(this, "Item 1 seleted", Toast.LENGTH_LONG).show()
            R.id.pItem2 -> Toast.makeText(this, "Item 2 seleted", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun clickedItem(cardData: CardData) {
        Log.d("TAG", "maulik" + cardData.id);

        when (cardData.id) {
            7 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            8 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            9 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            10 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            11 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            12 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            13 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            14 ->
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).apply { putExtra("Id", cardData.id) })
            else ->
                Toast.makeText(this, "No Action", Toast.LENGTH_LONG).show()
        }
    }

}