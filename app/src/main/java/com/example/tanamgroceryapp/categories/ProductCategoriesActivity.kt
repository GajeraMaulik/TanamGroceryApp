package com.example.tanamgroceryapp.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.Data.ShoppingCartData
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.adapter.ProductCategoriesAdapter
import kotlinx.android.synthetic.main.activity_product_categories.*

class ProductCategoriesActivity() : AppCompatActivity() {
    private  lateinit var rvProductCategories: RecyclerView
    private  lateinit var productCategoriesAdapter: ProductCategoriesAdapter
    val itemList: MutableList<ShoppingCartData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_categories)
        setSupportActionBar(toolbar)



        val pcbackBtn=findViewById<ImageButton>(R.id.pcbackBtn)
        pcbackBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener
        }




        val productCategoriesList: MutableList<HomeCategoriesData> = ArrayList()
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


        rvProductCategories =findViewById(R.id.rvProductCategories)
        rvProductCategories.layoutManager= GridLayoutManager(this,2)
        productCategoriesAdapter = ProductCategoriesAdapter(productCategoriesList)
        rvProductCategories.adapter = ProductCategoriesAdapter(productCategoriesList)

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
}