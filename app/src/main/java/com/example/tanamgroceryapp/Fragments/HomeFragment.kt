package com.example.tanamgroceryapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.Data.SliderData
import com.example.tanamgroceryapp.Adapter.HomeCategoriesAdapter
import com.example.tanamgroceryapp.Adapter.SliderAdapter
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.Adapter.PopularDealsAdapter
import com.example.tanamgroceryapp.Home.ProductCategoriesActivity
import com.example.tanamgroceryapp.ProductsActivity
import kotlin.math.abs

class HomeFragment : Fragment(),HomeCategoriesAdapter.ClickListener{
    private lateinit var rvCategories:RecyclerView
    private  lateinit var homeCategoriesAdapter: HomeCategoriesAdapter
    private  lateinit var rvPopolarDeals:RecyclerView
    private  lateinit var popularDealsAdapter: PopularDealsAdapter
    private  lateinit var viewPager2: ViewPager2
    private  val sliderHandelr=Handler()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?=inflater.inflate(R.layout.fragment_home,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sliderData: MutableList<SliderData> = ArrayList()
        val homeCategoriesList: MutableList<HomeCategoriesData> = ArrayList()
        val categoriesBtn=view.findViewById<ImageButton>(R.id.categoriesBtn)

        viewPager2 = view.findViewById(R.id.viewPager_ImageSlide)
        rvPopolarDeals=view.findViewById(R.id.rvPopolarDeals)
        rvCategories = view.findViewById(R.id.rvCategories)




        sliderData.add(SliderData(R.drawable.slider7))
        sliderData.add(SliderData(R.drawable.slider4))
        sliderData.add(SliderData(R.drawable.slider2))
        sliderData.add(SliderData(R.drawable.slider3))
        sliderData.add(SliderData(R.drawable.slider4))
        sliderData.add(SliderData(R.drawable.slider5))
        viewPager2.adapter = SliderAdapter(sliderData,viewPager2)


        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))

        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.75f + r * 0.25f
            page.scaleX = 0.75f + r * 0.45f
        }

        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandelr.removeCallbacks(sliderRunneble)
                sliderHandelr.postDelayed(sliderRunneble,2000)
            }

        })



        categoriesBtn.setOnClickListener {
            val intent = Intent(requireContext(), ProductCategoriesActivity::class.java)
            startActivity(intent)
            return@setOnClickListener
        }


        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.fruits,
                "Fruits",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.vegetables,
                "Vrgetables",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.bakery,
                "Bakery",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.dairy,
                "Dairy",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.mushroom,
                "Mushroom",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.fish,
                "Fish",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.pizzas,
                "Pizzas",
                "45 Items",

                )
        )
        homeCategoriesList.add(
            HomeCategoriesData(
                R.drawable.chicken,
                "Chicken",
                "45 Items",
                )
        )


      //  homeCategoriesAdapter = HomeCategoriesAdapter(homeCategoriesList)
        rvCategories.adapter = HomeCategoriesAdapter(homeCategoriesList,this)
    }


    private fun productData(){
        val popularDealsList: MutableList<CardData> = ArrayList()
        popularDealsList.add(
            CardData(
                1, R.drawable.item,false,"Chicken",5.2,10.9,"Chicken Village",true,0,0,0.0,0.0,"Disc. 10%Off"

            )
        )
        popularDealsList.add(
            CardData(
                2, R.drawable.item1,false,"Fish",6.2,10.5,"Gurame Fish",true,0,0,0.0,0.0,"Disc. 10%Off"

            )
        )
        popularDealsList.add(
            CardData(
                3, R.drawable.item2,true,"Vrgetables",7.9,11.5,"Tomatoes",true,0,0,0.0,0.0,"Disc. 10%Off"

            )
        )
        popularDealsList.add(
            CardData(
                4, R.drawable.item3,false,"Dairy",6.9,9.2,"Fresh Milk",true,0,0,0.0,0.0,"Disc. 10%Off"

            )
        )
        popularDealsList.add(
            CardData(
                5,   R.drawable.item4,true,"Fruits",6.9,9.5,"Fresh Avocados",true,0,0,0.0,0.0,"Disc. 10%Off"

            )
        )
        popularDealsList.add(
            CardData(
                6,  R.drawable.item5,true,"Fruits",5.5,8.5,"Fresh Grapes",true,0,0,0.0,0.0,"Disc. 10%Off"

            )
        )
        rvPopolarDeals.adapter=PopularDealsAdapter(popularDealsList)
    }

    private val sliderRunneble = Runnable {
        viewPager2.currentItem=viewPager2.currentItem+1
    }
    override fun onPause(){
        super.onPause()
        sliderHandelr.postDelayed(sliderRunneble,2000)
    }
    override fun onResume(){
        super.onResume()
        sliderHandelr.postDelayed(sliderRunneble,2000)
        productData()
    }

    override fun clickedItem(homeCategoriesData: HomeCategoriesData) {
        Log.d("TAG","maulik" +homeCategoriesData.catName);

        when(homeCategoriesData.catName){
            "Fruits" ->
                startActivity(Intent(context,ProductsActivity::class.java))
            else ->
                Toast.makeText(context,"No Action",Toast.LENGTH_LONG).show()
        }
    }
}





