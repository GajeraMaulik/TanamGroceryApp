package com.example.tanamgroceryapp.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.Data.SliderData
import com.example.tanamgroceryapp.adapter.HomeCategoriesAdapter
import com.example.tanamgroceryapp.adapter.SliderAdapter
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.Data.PopularDealsData
import com.example.tanamgroceryapp.adapter.PopularDealsAdapter
import com.example.tanamgroceryapp.categories.ProductCategoriesActivity
import kotlin.math.abs

class HomeFragment : Fragment() {
    private lateinit var rvCategories:RecyclerView
    private  lateinit var homeCategoriesAdapter: HomeCategoriesAdapter
    private  lateinit var rvPopolarDeals:RecyclerView
    private  lateinit var popularDealsAdapter: PopularDealsAdapter
    private  lateinit var viewPager2: ViewPager2
    private  val sliderHandelr=Handler()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?=inflater.inflate(R.layout.fragment_home,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesBtn=view.findViewById<ImageButton>(R.id.categoriesBtn)

        viewPager2 = view.findViewById(R.id.viewPager_ImageSlide)


        val sliderData: MutableList<SliderData> = ArrayList()
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
        }


        val homeCategoriesList: MutableList<HomeCategoriesData> = ArrayList()
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

        rvCategories = view.findViewById(R.id.rvCategories)
        homeCategoriesAdapter = HomeCategoriesAdapter(homeCategoriesList)
        rvCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = HomeCategoriesAdapter(homeCategoriesList)

        val popularDealsList: MutableList<PopularDealsData> = ArrayList()
        popularDealsList.add(
            PopularDealsData(
                R.drawable.item,false,"Chicken Village","10.9",true,243,0,0.0,0.0

            )
        )
        popularDealsList.add(
            PopularDealsData(
                R.drawable.item1,false,"Gurame Fish","10.9",true,243,0,0.0,0.0

            )
        )
        popularDealsList.add(
            PopularDealsData(
                R.drawable.item2,true,"Tomatoes","10.9",true,243,0,0.0,0.0

            )
        )
        popularDealsList.add(
            PopularDealsData(
                R.drawable.item3,false,"Fresh Milk","10.9",true,243,0,0.0,0.0

            )
        )
        popularDealsList.add(
            PopularDealsData(
                R.drawable.item4,true,"Fresh Avocados","10.9",true,243,0,0.0,0.0

            )
        )
        popularDealsList.add(
            PopularDealsData(
                R.drawable.item5,true,"Fresh Grapes","10.9",true,243,0,0.0,0.0

            )
        )
        rvPopolarDeals=view.findViewById(R.id.rvPopolarDeals)
        rvPopolarDeals.layoutManager=GridLayoutManager(context,2)
        rvPopolarDeals.adapter=PopularDealsAdapter(popularDealsList)

    }

    private val sliderRunneble = Runnable {
        viewPager2.currentItem=viewPager2.currentItem+1
    }
    override fun onPause(){
        super.onPause()
        sliderHandelr.postDelayed(sliderRunneble,3000)
    }
    override fun onResume(){
        super.onResume()
        sliderHandelr.postDelayed(sliderRunneble,3000)
    }
}





