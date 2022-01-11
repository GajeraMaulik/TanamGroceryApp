package com.example.tanamgroceryapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tanamgroceryapp.Fragments.AddressFragment
import com.example.tanamgroceryapp.Fragments.DetailsFragment
import com.example.tanamgroceryapp.Fragments.PaymentFragment
import kotlinx.android.synthetic.main.activity_shipping_address.*
import com.shuhart.stepview.StepView
import kotlinx.android.synthetic.main.fragment_payment.*

class ShippingAddressActivity : AppCompatActivity(){
    val fragment: Fragment = Fragment()
    lateinit var stepview:StepView
    private var isFragmentLoader = true
    private val manager= supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)
        setSupportActionBar(findViewById(R.id.saToolbar))

        val sabackBtn = findViewById<ImageButton>(R.id.saBackBtn)
        stepview=findViewById(R.id.stepView)

        sabackBtn.setOnClickListener {
            onBackPressed()
            Log.d("maulik","back")
            return@setOnClickListener
        }

        ShowDetailsFragment()
        SetupSteper()

        fdNextBtn.setOnClickListener {
            if (isFragmentLoader){
                Log.d("maulik","address")
                ShowAddressFragment()
            }
            else if(!isFragmentLoader){
                Log.d("maulik","payment")
                fdNextBtn.visibility = View.INVISIBLE
                ShowPaymentFragment()
            }
            else{
                ShowDetailsFragment()
                finish()
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    fun SetupSteper(){
        stepview.state
         // You should specify only stepsNumber or steps array of strings.
            // In case you specify both steps array is chosen.
            .steps(object : ArrayList<String?>() {
                init {
                    add("Details")
                    add("Address")
                    add("Payment")
                }
            }) // You should specify only steps number or steps array of strings.
            // In case you specify both steps array is chosen.
            .stepsNumber(3)
        //    .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()
    }
    fun  ShowDetailsFragment(){
        Log.d("maulik","details")
        val transation = manager.beginTransaction()
        val fragment = DetailsFragment()
        transation.replace(R.id.fragmentHolder,fragment)
        transation.addToBackStack(null)
        transation.commit()
        stepview.go(0,true)
        isFragmentLoader = true
    }

    fun  ShowAddressFragment(){
        val transation = manager.beginTransaction()
        val fragment = AddressFragment()
        transation.replace(R.id.fragmentHolder,fragment)
        transation.addToBackStack(null)
        stepview.go(1,true)
        transation.commit()
        isFragmentLoader = false
    }

    fun  ShowPaymentFragment(){
        val transation = manager.beginTransaction()
        val fragment = PaymentFragment()
        transation.replace(R.id.fragmentHolder,fragment)
        transation.addToBackStack(null)
        stepview.go(2,true)
        transation.commit()
        isFragmentLoader = false
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shippingaddress, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saItem1 -> Toast.makeText(this, "Item 1 seleted", Toast.LENGTH_LONG).show()
            R.id.saItem2 -> Toast.makeText(this, "Item 2 seleted", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

}