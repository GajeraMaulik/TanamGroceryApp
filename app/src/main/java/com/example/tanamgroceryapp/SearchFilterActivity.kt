package com.example.tanamgroceryapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Adapter.HomeCategoriesAdapter
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.Adapter.SearchfilterAdapter

class SearchFilterActivity : AppCompatActivity(),HomeCategoriesAdapter.ClickListener{
    private lateinit var rvsearchCategories: RecyclerView
    private val categorieslist: MutableList<HomeCategoriesData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_filter)
        val sfClosebtn = findViewById<ImageButton>(R.id.sfClosebtn)
        rvsearchCategories = findViewById(R.id.rvsearchCategories)

        sfClosebtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener

        }
        categorieslist.add(
                HomeCategoriesData(
                       1, R.drawable.ic_search_fruit, "Fresh Fruits", "8 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        2,R.drawable.ic_search_vegetables, "Fresh Vegetables", "68 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        3,R.drawable.ic_search_mushroom, "Mushrooms", "17 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        4,R.drawable.ic_search_baguette, "Bakery", "24 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        5,R.drawable.ic_search_fish, "Fresh Fish", "36 Items"
                )
        )
        categorieslist.add(
                HomeCategoriesData(
                        6,R.drawable.ic_search_cheese, "Dairy", "30 Items"
                )
        )
        categorieslist.add(
                HomeCategoriesData(
                        7,R.drawable.ic_search_pizza, "Pizzas", "10 Items"
                )
        )
        categorieslist.add(
                HomeCategoriesData(
                        8,R.drawable.ic_search_chicken, "Chicken", "26 Items"
                )
        )
        rvsearchCategories.adapter = SearchfilterAdapter(categorieslist,this)

    }

    override fun clickedItem(homeCategoriesData: HomeCategoriesData) {
        when(homeCategoriesData.id){
            1 ->
                startActivity(Intent(this,ProductsActivity::class.java))
            else ->
                Toast.makeText(this,"No Action", Toast.LENGTH_LONG).show()
        }
    }
}