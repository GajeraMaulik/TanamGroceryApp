package com.example.tanamgroceryapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tanamgroceryapp.Data.RecentSearchData
import com.example.tanamgroceryapp.Adapter.RecentSearchAdapter
import com.example.tanamgroceryapp.databinding.ActivitySearchResultsBinding

class SearchResultsActivity : AppCompatActivity(){
//    private  val popularDealsAdapter:PopularDealsAdapter = TODO()
    private  val recentlist: MutableList<RecentSearchData> = ArrayList()
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search_results)
        val tvclearAll=findViewById<TextView>(R.id.clearAll)
        val binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

     /*   tvclearAll.setOnClickListener {
            if ( != recentlist.size){
                recentlist.replaceAll(RecentSearchData)
            }else{

            }
        }

       val srsearchBar=findViewById<SearchView>(R.id.srsearchBar)

       srsearchBar.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(p0: String?): Boolean {
             srsearchBar.clearFocus()
               if (Constants.mShoppingCart.contains(p0)){

               }
           }

           override fun onQueryTextChange(p0: String?): Boolean {

           }

       })*/

        recentlist.add(
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
        binding.rvreacentSearch.adapter=RecentSearchAdapter(recentlist)


        binding.closeBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener

        }

        binding.searchfilterbtn.setOnClickListener {
            val intent = Intent(this, SearchFilterActivity::class.java)
            startActivity(intent)
        }
    }
}