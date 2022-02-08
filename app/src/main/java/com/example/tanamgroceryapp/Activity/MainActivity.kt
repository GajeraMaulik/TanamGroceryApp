package com.example.tanamgroceryapp.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.tanamgroceryapp.Data.OnBoadingData
import com.example.tanamgroceryapp.Adapter.OnBoadingViewAdapter
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.SharePref
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private var onBoadingViewAdapter: OnBoadingViewAdapter? = null
    private lateinit var onBoadingViewPager: ViewPager
    var tabLayout: TabLayout? = null
    var position = 0
    var next: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (SharePref.getBooleanValue1(this, "isLogin")) {
            Log.d("TAG","isLogin")
            val i = Intent(this, HomeActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            startActivity(i);
            finish()
        } else {
            Log.d("TAG","isFirstTimeRun")
            if (!SharePref.getBooleanValue(this, "isFirstTimeRun")) {
                val i = Intent(applicationContext, SignInActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById<Button>(R.id.next)

        val OnBoadingData: MutableList<OnBoadingData> = ArrayList()
        OnBoadingData.add(
            OnBoadingData(
                "Welcome to Tanam!\n Grocery Applications",
                "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor\n incididunt ut labore",
                R.drawable.group
            )
        )
        OnBoadingData.add(
            OnBoadingData(
                "Welcome to Tanam!\nGrocery Applications",
                "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor\n incididunt ut labore",
                R.drawable.group
            )
        )
        OnBoadingData.add(
            OnBoadingData(
                "Best Quality Grocery \n at your doorstep!",
                "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor\n incididunt ut labore",
                R.drawable.sreen3
            )
        )

        setOnBoadingViewAdapter(OnBoadingData)

        position = onBoadingViewPager.currentItem

        next?.setOnClickListener {
            position++
            onBoadingViewPager.currentItem = position
            if (position == OnBoadingData.size) {
                SharePref.save(this, "isFirstTimeRun", true)
                val i = Intent(applicationContext, SignInActivity::class.java)
                startActivity(i)
                finish()
            }
        }

    }

    private fun setOnBoadingViewAdapter(onBoadingData: List<OnBoadingData>) {
        onBoadingViewPager = findViewById(R.id.screenPager)
        onBoadingViewAdapter = OnBoadingViewAdapter(this, onBoadingData)
        onBoadingViewPager.adapter = onBoadingViewAdapter
        tabLayout?.setupWithViewPager(onBoadingViewPager, true)

        onBoadingViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    next?.text = "GET STARTED"
                } else {
                    next?.text = "Next"
                }
                Log.d("onPageSelected", "position     $position")
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }


}


