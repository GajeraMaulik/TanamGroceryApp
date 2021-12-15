package com.example.tanamgroceryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.tanamgroceryapp.Data.OnBoadingData
import com.example.tanamgroceryapp.adapter.OnBoadingViewAdapter
import com.example.tanamgroceryapp.login.SignInActivity
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private var onBoadingViewAdapter: OnBoadingViewAdapter? = null
    var tabLayout: TabLayout? = null
    private lateinit var onBoadingViewPager: ViewPager

    var position = 0
    var SharedPreference: SharedPreferences? = null

    var next: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (restorePrefData()) {
            val i = Intent(applicationContext, SignInActivity::class.java)
            startActivity(i)
            finish()
        }
        setContentView(R.layout.activity_main)

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

        position = onBoadingViewPager!!.currentItem

        next?.setOnClickListener {
            position++
            onBoadingViewPager.currentItem = position
            if (position==OnBoadingData.size) {
                savePrefData()
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
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if(position==2){
                    next?.text = "GET STARTED"
                }else{
                    next?.text = "Next"
                }
                Log.d("onPageSelected", "position     $position")
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    private fun savePrefData() {
        SharedPreference = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = SharedPreference!!.edit()
        editor.putBoolean("isFirstTimerun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean {
        SharedPreference = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return SharedPreference!!.getBoolean("isFirstTimerun", false)
    }
}


