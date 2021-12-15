package com.example.tanamgroceryapp.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.ShoppingCartActivity
import com.example.tanamgroceryapp.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    val TAG= "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val cartBtn=findViewById<ImageButton>(R.id.cartBtn)
        val bottomNav=findViewById<BottomNavigationView>(R.id.bottomNav)
        val intentValue = intent.getStringExtra("Username")
        findViewById<TextView>(R.id.user).apply{
            text = intentValue.toString()
        }

        val homeFragment= HomeFragment()
        // val cartFragment=CartFragment()
        setCurrentFragment(homeFragment)

        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.nav_home -> setCurrentFragment(homeFragment)


            }
        }
        cartBtn.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }




}