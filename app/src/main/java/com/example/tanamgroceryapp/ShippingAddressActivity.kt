package com.example.tanamgroceryapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

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
    private lateinit var eFullName: EditText
    private lateinit var eEmail: EditText
    private lateinit var ePhone: EditText
    private lateinit var eAddress: EditText
    private lateinit var eZipcode: EditText
    private lateinit var eCity: EditText

    private lateinit var spinner: Spinner
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

    /*    eFullName=findViewById<EditText>(R.id.etFullName)
        eEmail=findViewById<EditText>(R.id.etEmail)
        ePhone=findViewById<EditText>(R.id.etPhone)
        eAddress=findViewById<EditText>(R.id.etAddress)
        eZipcode=findViewById<EditText>(R.id.etZipcode)
        eCity =findViewById<EditText>(R.id.etCity)


*/

        sabackBtn.setOnClickListener {
            onBackPressed()
            Log.d("maulik","back")
            if (nextBtn.visibility == View.INVISIBLE ){
                nextBtn.visibility=View.VISIBLE


            }else{
                return@setOnClickListener

            }
        }


        selectedItem(1)
        nextBtn.setOnClickListener {

            if (isFragmentLoader ){
                Log.d("maulik","address")

                selectedItem(2)
            }
            else if(!isFragmentLoader ){
                Log.d("maulik","payment")
                nextBtn.visibility = View.INVISIBLE
                    selectedItem(3)
                return@setOnClickListener

            }
            else{
                selectedItem(1)
            }
        }
        SetupSteper()
    }

    private fun SetupSteper(){
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

    private fun isvalid(selected: Int): Boolean {
        when(selected){
            1-> {


                var invalid = true
                    val fullname : String = eFullName.text.toString().trim()
                    val email: String =eEmail.text.toString().trim()
                    val phone : CharSequence =ePhone.text.trim()
                    //  val spinner: View = vSpinner[0]
                    if (fullname.isEmpty() ){
                        invalid = false
                        Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show()
                        eFullName.requestFocus()
                    }   else if (email.isEmpty()){
                        invalid=false
                        Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show()
                        eEmail.requestFocus()
                        //  etEmail.setError(getResources().getString(R.string.email_error));
                    }
                    else if (!Patterns.EMAIL_ADDRESS.matcher(eEmail.text.toString()).matches()) {
                        invalid=false
                        eEmail.error = resources.getString(R.string.error_invalid_email);
                        eEmail.requestFocus()

                    }else if (phone.isEmpty()){
                        invalid=false
                        Toast.makeText(this, "Enter Your Number", Toast.LENGTH_SHORT).show()
                        ePhone.requestFocus()
                    }else if (phone.length <= 10){
                        ePhone.error="Please enter a valid Number"
                        ePhone.requestFocus()
                    }
                    else{
                        invalid = true
                        eFullName.error=null
                        eEmail.error=null
                        ePhone.error=null
                    }
                    return invalid
                }
            2 ->{

                var invalid = true
                val address: String = eAddress.text.toString().trim()
                val zipcode: String = eZipcode.text.toString().trim()
                val city: CharSequence = eCity.text.trim()
                //  val spinner: View = vSpinner[0]
                if (address.isEmpty()) {
                    invalid = false
                    Toast.makeText(this, "Enter your Address", Toast.LENGTH_SHORT).show()
                    eAddress.requestFocus()
                } else if (zipcode.isEmpty()) {
                    invalid = false
                    Toast.makeText(this, "Invalid Zipcode", Toast.LENGTH_SHORT).show()
                    eZipcode.requestFocus()
                } else if (city.isEmpty()) {
                    eCity.error = "Please Enter Your City"
                    eCity.requestFocus()
                } else {
                    invalid = true
                    eAddress.error = null
                    eZipcode.error = null
                    eCity.error = null
                }
                return invalid
            }
        }
        return true
    }


    @SuppressLint("ResourceType")
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

