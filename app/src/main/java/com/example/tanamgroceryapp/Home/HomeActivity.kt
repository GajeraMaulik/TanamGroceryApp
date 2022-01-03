package com.example.tanamgroceryapp.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.SearchResultsActivity
import com.example.tanamgroceryapp.ShoppingCartActivity
import com.example.tanamgroceryapp.fragments.FavFragment
import com.example.tanamgroceryapp.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    val TAG= "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val cartBtn=findViewById<ImageButton>(R.id.cartBtn)
        val bottomNav=findViewById<BottomNavigationView>(R.id.bottomNav)
        val searchBar=findViewById<EditText>(R.id.searchBar)

        val intentValue = intent.getStringExtra("Username")


        findViewById<TextView>(R.id.user).apply{
            text = intentValue.toString()
        }

        val homeFragment= HomeFragment()
        val favFragment=FavFragment()
        setCurrentFragment(homeFragment)

        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.nav_home -> setCurrentFragment(homeFragment)
                R.id.nav_favorite -> setCurrentFragment(favFragment)


            }
        }


        cartBtn.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        searchBar.setOnClickListener {
            val intent=Intent(this,SearchResultsActivity::class.java)
            startActivity(intent)
        }

    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }




}