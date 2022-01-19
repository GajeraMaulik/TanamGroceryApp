package com.example.tanamgroceryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.tanamgroceryapp.Adapter.ProductsAdapter
import com.example.tanamgroceryapp.Adapter.RecentSearchAdapter
import com.example.tanamgroceryapp.Data.RecentSearchData
import com.example.tanamgroceryapp.Adapter.SearchAdapter
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.databinding.ActivitySearchResultsBinding
import kotlinx.android.synthetic.main.activity_search_results.*
import java.util.*
import kotlin.collections.ArrayList

class SearchResultsActivity : AppCompatActivity(),ProductsAdapter.ClickListener{
   private var sharedPreference: SharedPreferences? = null

    private var recentlist: MutableList<RecentSearchData> = ArrayList()
    private var productslist: MutableList<CardData> = ArrayList()
    private  lateinit var searchAdapter:SearchAdapter
    private  lateinit var recentSearchAdapter: RecentSearchAdapter
    private  lateinit var binding: ActivitySearchResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.clearAll.setOnClickListener {
            Log.d("maulik", "ClearAll")
            if (recentlist.isNotEmpty()) {
                recentlist.clear()
            }
        }

        binding.srsearchBar.doOnTextChanged { text, _, _, _ ->
            val query= text.toString().toLowerCase(Locale.getDefault())

            filterWithQuery(query)

             // savePrefData(recentlist) for (currentItem in productslist) {
            for (currentItem in productslist)
                if (currentItem.itemName.length > 5) {

                    if (currentItem.itemName.toLowerCase(Locale.getDefault()).contains(query) && recentlist.isNullOrEmpty()) {
                        recentlist.add(
                            RecentSearchData(
                                R.drawable.ic_recentsearch_time,
                                query,
                                R.drawable.ic_recentsearch_close
                            )
                        )
                        notifyDataSetChanged()

                    }
                }
            }
        recentSearchAdapter = RecentSearchAdapter(recentlist)
        binding.rvReacentSearch.adapter = recentSearchAdapter




        /* recentlist.add(
             RecentSearchData(
                 R.drawable.ic_recentsearch_time,"Tomatoes",R.drawable.ic_recentsearch_close
             )
         )
         recentlist.add(
             RecentSearchData(
                 R.drawable.ic_recentsearch_time,"Local Fresh Spinach",R.drawable.ic_recentsearch_close
             )
         )
         recentlist.add(
             RecentSearchData(
                 R.drawable.ic_recentsearch_time,"Frehs Orange",R.drawable.ic_recentsearch_close
             )
         )

 */

        binding.closeBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener

        }

        binding.searchfilterbtn.setOnClickListener {
            val intent = Intent(this, SearchFilterActivity::class.java)
            startActivity(intent)
        }

        getData()
        attachAdapter(productslist)
    }


 /*   override fun onResume() {
        super.onResume()
        getData()
    }
*/

    private fun getData() {
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
        rvSearchlist.adapter = ProductsAdapter(productslist, this)
    }

    private fun attachAdapter( productslist: MutableList<CardData>) {
        searchAdapter = SearchAdapter(productslist, this)
        binding.rvSearchlist.adapter = searchAdapter
    }

    private fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: MutableList<CardData> = onFilterChanged(query)
            attachAdapter(filteredList)
            toggleRecyclerView(filteredList)
        } else if (query.isEmpty()) {
            attachAdapter(productslist)
        }
    }
    private fun onFilterChanged(filterQuery: String): MutableList<CardData> {
        val filteredList = ArrayList<CardData>()
        for (currentItem in productslist) {
            if (currentItem.itemName.toLowerCase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentItem)
            }
        }
        return filteredList
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            // Do something with spokenText
            srSearchBar.setText(spokenText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
*/


    private fun toggleRecyclerView( productslist: MutableList<CardData> ) {
        if (productslist.isEmpty()) {
            binding.rvReacentSearch.visibility = View.INVISIBLE
            binding.noSearchResultsFoundText.visibility = View.VISIBLE
        } else {
            binding.rvReacentSearch.visibility = View.VISIBLE
            binding.noSearchResultsFoundText.visibility = View.INVISIBLE
        }
    }

/*
    companion object {
        const val SPEECH_REQUEST_CODE = 0
    }*/

    override fun clickedItem(cardData: CardData) {
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

    override fun notifyDataSetChanged() {

    }
}