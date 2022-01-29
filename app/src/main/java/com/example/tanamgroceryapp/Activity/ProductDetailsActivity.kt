package com.example.tanamgroceryapp.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.Data.ImageData
import com.example.tanamgroceryapp.Adapter.ProductDetailsAdapter
import com.example.tanamgroceryapp.ApplicationInitialize
import com.example.tanamgroceryapp.R
import java.util.*
import kotlin.collections.ArrayList
import com.viewpagerindicator.CirclePageIndicator


class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var pdtoolbar: Toolbar
   // private lateinit var productslist: MutableList<CardData>

    private var viewpager: ViewPager? = null



    var currentPage = 0
    var NUM_PAGES = 0


    @SuppressLint("UseSupportActionBar", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        setContentView(R.layout.activity_product_details)

        pdtoolbar = findViewById<Toolbar>(R.id.pdToolbar)
        setSupportActionBar(pdtoolbar)
        val pdbackBtn = findViewById<ImageButton>(R.id.pdbackBtn)
        val pdMinusBtn = findViewById<ImageButton>(R.id.pdMinusBtn)
        val pdAddBtn = findViewById<ImageButton>(R.id.pdAddBtn)
        val tvQuantity = findViewById<TextView>(R.id.pdQuantity)
        val btnAdd = findViewById<LinearLayout>(R.id.pdAdd)
        val intentValue = intent.getStringExtra("Id")
        val itemQty=findViewById<LinearLayout>(R.id.itemQty)
        val pCartBtn=findViewById<ImageButton>(R.id.pCartBtn)


        val mCardData = CardData(
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

        pdbackBtn.setOnClickListener {
            onBackPressed()
            return@setOnClickListener
        }

       viewpager=findViewById(R.id.viewPager)
        initImageData()    // call method

        if (ApplicationInitialize.mShoppingCart.size > 0) {
            val getSingleData = ApplicationInitialize.mShoppingCart[mCardData.id]
            if (getSingleData != null) {
                tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
            }
        } else {
            tvQuantity.text = StringBuilder("").append(mCardData.quantity)
        }

          pdAddBtn.setOnClickListener {
              if (ApplicationInitialize.mShoppingCart.size == 0) {
                   mCardData.quantity = mCardData.quantity + 1
                  tvQuantity.text = StringBuilder("").append(mCardData.quantity)
              } else {
                   val getSingleData = ApplicationInitialize.mShoppingCart[mCardData.id]
                   if (getSingleData != null) {
                      getSingleData.quantity = getSingleData.quantity + 1
                      tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                   } else {
                       mCardData.quantity = mCardData.quantity + 1
                       tvQuantity.text = StringBuilder("").append(mCardData.quantity)
                       Log.d("maulik", "error")
                   }
               }
           }


           pdMinusBtn.setOnClickListener {
              if (ApplicationInitialize.mShoppingCart.size > 0) {
                   val getSingleData = ApplicationInitialize.mShoppingCart[mCardData.id]
                   if (getSingleData != null) {
                       getSingleData.quantity = getSingleData.quantity - 1
                       if (getSingleData.quantity <= 0) {
                           Log.d("maulik", "full  0 ")
                           tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                           ApplicationInitialize.mShoppingCart.remove(getSingleData.id)
                           Toast.makeText(this, "Remove the ${getSingleData.itemName} From Cart", Toast.LENGTH_LONG).show()
                      } else {
                           Log.d("maulik", "full > 0 ")
                           tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                          ApplicationInitialize.mShoppingCart[getSingleData.id] = getSingleData
                       }

                   }
               } else {
                   if (mCardData.quantity >0) {
                       Log.d("maulik", "null >0 ")
                       mCardData.quantity = mCardData.quantity - 1
                       tvQuantity.text = StringBuilder("").append(mCardData.quantity)
                      ApplicationInitialize.mShoppingCart[mCardData.id] = mCardData
                   } else {
                       Log.d("maulik", "null  0 ")
                       ApplicationInitialize.mShoppingCart.remove(mCardData.id)
                   }
               }

           }
         btnAdd.setOnClickListener {
                btnAdd.backgroundTintMode
             if(mCardData.quantity >=1) {
                 Toast.makeText(this, "Add To Cart ${mCardData.itemName} ", Toast.LENGTH_LONG).show()

                 ApplicationInitialize.mShoppingCart[mCardData.id] = mCardData
             }else{
                 Toast.makeText(this, "Please Item Add", Toast.LENGTH_LONG).show()
             }
         }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun initImageData() {

        val imageList: ArrayList<ImageData> = ArrayList()

        imageList.add(
            ImageData(
                "Image",
                R.drawable.productimage
            )
        )
        imageList.add(
            ImageData(
                "Logo",
                "https://images.unsplash.com/photo-1582979512210-99b6a53386f9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
            )
        )
        imageList.add(
            ImageData(
                "Logo",
                "https://images.unsplash.com/photo-1552841847-0e031d33a283?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=765&q=80"
            )
        )
        imageList.add(
            ImageData(
                "Logo",
                "https://images.unsplash.com/photo-1552089123-2d26226fc2b7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"
            )
        )

        //Set the pager with an adapter
        viewpager?.adapter = ProductDetailsAdapter(this, imageList)
        val indicator: CirclePageIndicator = findViewById<View>(R.id.indicator) as CirclePageIndicator
        indicator.setViewPager(viewpager)
        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.radius = 5 * density
        NUM_PAGES = imageList.size

        // Auto start of viewpager
        val handler = Handler(Looper.getMainLooper())
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            viewpager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 1000, 1000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_productdetails, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pdItem1 -> Toast.makeText(this, "Item 1 seleted", Toast.LENGTH_LONG).show()
            R.id.pdItem2 -> Toast.makeText(this, "Item 2 seleted", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

}






