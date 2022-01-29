package com.example.tanamgroceryapp.Activity

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

     val TAG= "HomeActivity"

    private var homeFragment = HomeFragment()
    private  var firebaseAuth : FirebaseAuth? = null
    private var firebaseDatabase : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val cartBtn = findViewById<ImageButton>(R.id.cartBtn)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val searchBar = findViewById<EditText>(R.id.searchBar)
        val intentValue = intent.getStringExtra("Username")
        setCurrentFragment(homeFragment)


        findViewById<TextView>(R.id.user).apply {
            text = intentValue.toString().removeRange(6,26)
        }
    /*    firebaseAuth = FirebaseAuth.getInstance()
        val myRefernce = firebaseDatabase?.getReference(firebaseAuth?.uid.toString())
            myRefernce?.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    val userProfile:UserProfile? = p0.getValue(UserProfile::class.java)
                    user.text = intent.getStringExtra("Username")
                }

                override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(this@HomeActivity,p0.code,Toast.LENGTH_LONG).show()
                }

            })
*/
        //   val favFragment=FavFragment()

        cartBtn.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
            return@setOnClickListener
        }

        searchBar.setOnClickListener {
            val intent = Intent(this, SearchResultsActivity::class.java)
            startActivity(intent)
           return@setOnClickListener
        }

        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.nav_home -> setCurrentFragment(homeFragment)
              //  R.id.nav_favorite -> setCurrentFragment(favFragment)
            }
        }

    }

    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}