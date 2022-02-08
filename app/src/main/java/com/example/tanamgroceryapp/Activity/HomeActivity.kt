package com.example.tanamgroceryapp.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tanamgroceryapp.Constants.capitalizeWords
import com.example.tanamgroceryapp.Data.UserProfile
import com.example.tanamgroceryapp.Fragments.HomeFragment
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.SharePref
import com.example.tanamgroceryapp.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class HomeActivity : AppCompatActivity() {

    val TAG = "HomeActivity"

    private var homeFragment = HomeFragment()
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: UserProfile
    private lateinit var binding: ActivityHomeBinding
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        val username = SharePref.getStringValue(this, "username")
        binding.userName.text = capitalizeWords("$username")
        //username?.let { Log.d("Home", it) }
        if (username != null) {
            binding.userName.text = capitalizeWords("$username")

            Log.d("TAG", "Homeusername:$username")

        } /*else {
            val i = Intent(this@HomeActivity, SignInActivity::class.java)
            startActivity(i)
            Log.d("TAG", "SignIn")

        }*/

        setCurrentFragment(homeFragment)
        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("profile")

        binding.notification.setOnClickListener {
/*
                SharePref.removeSharePref(this)
                val i = Intent(this, SignInActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)*/
            Toast.makeText(this, "singOut $username", Toast.LENGTH_LONG).show()
             // finish()

            d("TAG", "logout")
            return@setOnClickListener
        }


        //   val favFragment=FavFragment()

        binding.cartBtn.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
            return@setOnClickListener
        }

        binding.searchBar.setOnClickListener {
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
            return@setOnClickListener
        }

        binding.bottomNav.setOnNavigationItemReselectedListener {it.itemId
            when (it.itemId) {
                R.id.nav_home -> { Toast.makeText(this,"Home",Toast.LENGTH_LONG).show()
                                    setCurrentFragment(homeFragment)
                }

                R.id.nav_tranfer -> Toast.makeText(this,"TransferView",Toast.LENGTH_LONG).show()
                R.id.nav_favorite -> Toast.makeText(this,"Favorite",Toast.LENGTH_LONG).show()
                R.id.nav_placeholder -> Toast.makeText(this,"Placeholder",Toast.LENGTH_LONG).show()
            }
        }

        setContentView(binding.root)
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}