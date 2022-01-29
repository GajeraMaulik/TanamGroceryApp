package com.example.tanamgroceryapp.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

import com.example.tanamgroceryapp.Fragments.AddressFragment
import com.example.tanamgroceryapp.Fragments.DetailsFragment
import com.example.tanamgroceryapp.Fragments.PaymentFragment
import com.example.tanamgroceryapp.R
import com.shuhart.stepview.StepView

import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.activity_shipping_address.*
import kotlinx.android.synthetic.main.fragment_payment.*

class ShippingAddressActivity : AppCompatActivity(){

    lateinit var stepview: StepView
    private var detailsFragment =  DetailsFragment()
    private var addressFragment = AddressFragment()


    private var isFragmentLoader = true
    private val manager= supportFragmentManager

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)
        setSupportActionBar(findViewById(R.id.saToolbar))

        val sabackBtn = findViewById<ImageButton>(R.id.saBackBtn)
        stepview=findViewById(R.id.stepView)

        sabackBtn.setOnClickListener {
            onBackPressed()

            if (nextBtn.visibility == View.INVISIBLE ){
                nextBtn.visibility=View.VISIBLE
            }
            else
            {
                return@setOnClickListener

            }
            Log.d("maulik","back")
        }



        selectedItem(1)
        nextBtn.setOnClickListener {

            if (isFragmentLoader && detailsFragment.isvalid() ){
                Log.d("maulik","address")
                selectedItem(2)
            }
            else if(!isFragmentLoader && addressFragment.isvalid() ){
                Log.d("maulik","payment")
                nextBtn.visibility = View.INVISIBLE
                selectedItem(3)

            }
        }

        SetupSteper()
    }

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

    private fun selectedItem(selected: Int): Boolean {
        when (selected) {
            1 -> {

                    Log.d("maulik", "details")
                    val transation = manager.beginTransaction()
                    transation.replace(R.id.fragmentHolder, detailsFragment)
                    transation.addToBackStack(null)
                    stepview.go(0,true)
                    isFragmentLoader = true
                    transation.commit()

                }
            2 -> {

                    val transation = manager.beginTransaction()
                    transation.replace(R.id.fragmentHolder, addressFragment)
                    transation.addToBackStack(null)
                    stepview.go(1,true)
                    isFragmentLoader = false
                    transation.commit()



            }

            3 -> {
                    val transation = manager.beginTransaction()
                    val paymentFragment = PaymentFragment()
                    transation.replace(R.id.fragmentHolder, paymentFragment)
                    transation.addToBackStack(null)
                    transation.commit()
                    stepview.go(2,true)
                    isFragmentLoader = false
                }

            }
        return true
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

