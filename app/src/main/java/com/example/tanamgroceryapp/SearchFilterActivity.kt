package com.example.tanamgroceryapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.Adapter.SearchfilterAdapter

class SearchFilterActivity : AppCompatActivity() {
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
                        R.drawable.ic_search_fruit, "Fresh Fruits", "45 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_vegetables, "Fresh Vegetables", "68 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_mushroom, "Mushrooms", "17 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_baguette, "Bakery", "24 Items"
                )
        )

        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_fish, "Fresh Fish", "36 Items"
                )
        )
        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_cheese, "Dairy", "30 Items"
                )
        )
        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_pizza, "Pizzas", "10 Items"
                )
        )
        categorieslist.add(
                HomeCategoriesData(
                        R.drawable.ic_search_chicken, "Chicken", "26 Items"
                )
        )
        rvsearchCategories.adapter = SearchfilterAdapter(categorieslist)

    }
}