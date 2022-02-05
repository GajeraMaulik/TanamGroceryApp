package com.example.tanamgroceryapp.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tanamgroceryapp.Data.UserProfile
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.Fragments.HomeFragment
import com.example.tanamgroceryapp.databinding.ActivityHomeBinding
import com.example.tanamgroceryapp.databinding.ActivityProductsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class HomeActivity : AppCompatActivity() {

     val TAG= "HomeActivity"

    private var homeFragment = HomeFragment()
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var databaseReference : DatabaseReference
    private lateinit var  user : UserProfile
    private lateinit var binding: ActivityHomeBinding
    private  lateinit var uid:String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
         setContentView(view)




        setCurrentFragment(homeFragment)

        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("profile")


        binding.userName.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
            Toast.makeText(this,"singOut ${etUser?.text}",Toast.LENGTH_LONG).show()


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

        binding.bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.nav_home -> setCurrentFragment(homeFragment)
              //  R.id.nav_favorite -> setCurrentFragment(favFragment)
            }
        }

    }
    private  fun checkUser(){
        val user: FirebaseUser? = firebaseAuth.currentUser
        val username = this.intent.getStringExtra("Username")

        if (user != null){
            databaseReference.child("User").addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    binding.userName.text = username
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        }else{
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
    }



    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }

}