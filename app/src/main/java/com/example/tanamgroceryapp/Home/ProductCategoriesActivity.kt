package com.example.tanamgroceryapp.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.Data.ShoppingCartData
import com.example.tanamgroceryapp.ProductsActivity
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.adapter.ProductCategoriesAdapter

class ProductCategoriesActivity() : AppCompatActivity(),ProductCategoriesAdapter.ClickListener {
    private  lateinit var rvProductCategories: RecyclerView
    private  lateinit var productCategoriesAdapter: ProductCategoriesAdapter
    val itemList: MutableList<ShoppingCartData> = ArrayList()
    val productCategoriesList: MutableList<HomeCategoriesData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_categories)
        setSupportActionBar(findViewById(R.id.pcToolbar))
        rvProductCategories =findViewById(R.id.rvProductCategories)


        val pcbackBtn=findViewById<ImageButton>(R.id.pcbackBtn)
        pcbackBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener
        }

        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.fruits,
                "Fruits",
                "45 Items",

                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.vegetables,
                "Vrgetables",
                "45 Items",

                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.bakery,
                "Bakery",
                "45 Items",

                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.dairy,
                "Dairy",
                "45 Items",

                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.mushroom,
                "Mushroom",
                "45 Items",

                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.fish,
                "Fish",
                "45 Items",

                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.pizzas,
                "Pizzas",
                "45 Items",


                )
        )
        productCategoriesList.add(
            HomeCategoriesData(
                R.drawable.chicken,
                "Chicken",
                "45 Items",

                )
        )

        rvProductCategories.adapter = ProductCategoriesAdapter(productCategoriesList,this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_productcategories,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting-> Toast.makeText(this,"Item 1 seleted", Toast.LENGTH_LONG).show()
            R.id.aboutUs-> Toast.makeText(this,"Item 2 seleted", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun clickedItem(homeCategoriesData: HomeCategoriesData) {
        Log.d("TAG","maulik" +homeCategoriesData.catName)
        when(homeCategoriesData.catName){
            "Fruits" ->
                startActivity(Intent(this, ProductsActivity::class.java))
            else ->
                Toast.makeText(this,"No Action",Toast.LENGTH_LONG).show()
        }
    }
}