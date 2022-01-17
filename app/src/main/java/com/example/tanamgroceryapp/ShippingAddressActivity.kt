package com.example.tanamgroceryapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.fragment.app.Fragment

import com.example.tanamgroceryapp.Fragments.AddressFragment
import com.example.tanamgroceryapp.Fragments.DetailsFragment
import com.example.tanamgroceryapp.Fragments.PaymentFragment
import com.shuhart.stepview.StepView

import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.activity_shipping_address.*
import kotlinx.android.synthetic.main.fragment_payment.*

class ShippingAddressActivity : AppCompatActivity(){
    val fragment: Fragment = Fragment()
    lateinit var stepview: StepView
    private var isFragmentLoader = true
    private val manager= supportFragmentManager
    private var mCurrentStepIndex: Int = 0
   // private var mCurrentStepState =Step.State.NOT_COMPLETED


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)
        setSupportActionBar(findViewById(R.id.saToolbar))

        val sabackBtn = findViewById<ImageButton>(R.id.saBackBtn)
        stepview=findViewById(R.id.stepView)




        sabackBtn.setOnClickListener {
            onBackPressed()
            Log.d("maulik","back")
            if (fdNextBtn.visibility == View.INVISIBLE ){
                fdNextBtn.visibility=View.VISIBLE
            }else{
                return@setOnClickListener

            }
        }


        selectedItem(1)
        DetailsFragment()
        fdNextBtn.setOnClickListener {
            if (isFragmentLoader){
                Log.d("maulik","address")
                selectedItem(2)
                AddressFragment()
                return@setOnClickListener

            }
            else if(!isFragmentLoader){
                Log.d("maulik","payment")
                fdNextBtn.visibility = View.INVISIBLE
                    selectedItem(3)
                PaymentFragment()
                return@setOnClickListener

            }
            else{
                selectedItem(1)
                DetailsFragment()
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

/*    @SuppressLint("UseCompatLoadingForDrawables")
    private fun stepper() {
        val steps:MutableList<Step> = ArrayList()

        steps.add(Step("Detail",Step.State.CURRENT))
        steps.add(Step("Adress"))
        steps.add(Step("Payment"))

        stepview.setSteps(steps)
            .setNotCompletedStepIcon(getDrawable(R.drawable.ic_check_unseleted_circle)!!)
            .setCompletedStepIcon(getDrawable(R.drawable.ic_check_done_circle)!!)
            .setCurrentStepIcon(getDrawable(R.drawable.ic_check__seleted_circle)!!)

            .setCompletedStepTextColor(R.color.main)
            .setCurrentStepTextColor(R.color.main)
            .setNotCompletedStepTextColor(R.color.soft)

            .setCompletedLineColor(R.color.main)
            .notCompletedLineColor = R.color.grey02

    }*/

    private fun selectedItem(selected: Int): Boolean {
        when (selected) {
            1 -> {

                Log.d("maulik", "details")
                val transation = manager.beginTransaction()
                val fragment = DetailsFragment()
                transation.replace(R.id.fragmentHolder, fragment)
                transation.addToBackStack(null)
                transation.commit()
                stepview.go(0,true)
                isFragmentLoader = true

                /*    stepview.setStepState(Step.State.CURRENT, mCurrentStepIndex)
                    mCurrentStepIndex++
                    stepview.getStep(mCurrentStepIndex).state
                    stepper()*/

            }
            2 -> {

                val transation = manager.beginTransaction()
                val fragment = AddressFragment()
                transation.replace(R.id.fragmentHolder, fragment)
                transation.addToBackStack(null)
                transation.commit()
                stepview.go(1,true)
                isFragmentLoader = false

                /*  stepview.setStepState(mCurrentStepState, mCurrentStepIndex)
                  mCurrentStepIndex++
                  stepview.getStep(mCurrentStepIndex).state
                  stepper()*/


            }
            3 -> {
                val transation = manager.beginTransaction()
                val fragment = PaymentFragment()
                transation.replace(R.id.fragmentHolder, fragment)
                transation.addToBackStack(null)
                transation.commit()
                stepview.go(2,true)
                isFragmentLoader = false

                /*    stepview.setStepState(mCurrentStepState, mCurrentStepIndex)
                    stepview.getStep(mCurrentStepIndex).state
                    stepview.setStepState(Step.State.CURRENT, mCurrentStepIndex)
                    stepper()*/


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

