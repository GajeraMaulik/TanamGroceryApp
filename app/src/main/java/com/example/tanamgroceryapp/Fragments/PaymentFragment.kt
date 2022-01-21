package com.example.tanamgroceryapp.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Rect
import android.icu.text.SelectFormat
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.MonthDisplayHelper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresPermission
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.ShippingAddressActivity
import com.shuhart.stepview.StepView
import kotlinx.android.synthetic.main.fragment_payment.*
import java.util.*
import kotlin.math.log
import android.preference.PreferenceManager
import com.example.tanamgroceryapp.login.SignInActivity
import java.io.DataOutputStream
import java.io.IOException


class PaymentFragment : Fragment() {
    lateinit var stepview: StepView
    private var shippingAddressActivity = ShippingAddressActivity()
       private lateinit var paymetMethods:LinearLayout
    private  lateinit var cvCodBtn:CardView
    private  lateinit var codIcon:ImageView
    private  lateinit var cvCreditCardBtn:CardView
    private  lateinit var cdIcon:ImageView
    private  lateinit var cvPaipalBtn:CardView
    private  lateinit var pmIcon:ImageView
    private  lateinit var creditCardView:LinearLayout
    private  lateinit var eHolderName:EditText
    private  lateinit var tholderName:TextView
    private  lateinit var eCardno:EditText
    private  lateinit var tCardNo:TextView
    private  lateinit var eDate:EditText
    private  lateinit var tDate:TextView
    private  lateinit var ecvv:EditText
    private  lateinit var vspinner:Spinner
    private  lateinit var cPayment:CheckBox
    private  lateinit var btnPayment:Button
    private  lateinit var sharedPreferences:SharedPreferences

    val items= arrayOf("Choose your country","India","Japan","China")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_payment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         cvCodBtn = view.findViewById<CardView>(R.id.cvCodBtn)
         cvCreditCardBtn = view.findViewById<CardView>(R.id.cvCreditCardBtn)
         cvPaipalBtn = view.findViewById<CardView>(R.id.cvPaipalBtn)
         creditCardView = view.findViewById<LinearLayout>(R.id.creditCardView)
         paymetMethods = view.findViewById<LinearLayout>(R.id.paymetMethods)
         codIcon = view.findViewById<ImageView>(R.id.codIcon)
         cdIcon = view.findViewById<ImageView>(R.id.cdIcon)
         pmIcon = view.findViewById<ImageView>(R.id.pmIcon)
         eHolderName = view.findViewById<EditText>(R.id.etHolderName)
         tholderName = view.findViewById<TextView>(R.id.tvHolderName)
         eCardno = view.findViewById<EditText>(R.id.etCardno)
         tCardNo = view.findViewById<TextView>(R.id.tvCardNo)
         eDate = view.findViewById<EditText>(R.id.etDate)
         tDate = view.findViewById<TextView>(R.id.tvDate)
        ecvv=view.findViewById(R.id.etCvv)
        vspinner=view.findViewById(R.id.fpSpinner)
        btnPayment=view.findViewById(R.id.btnPayment)
        cPayment=view.findViewById(R.id.cbPayment)

        val checked:Boolean= cPayment.isChecked

        when(view.id){
            R.id.cbPayment -> {
                if (checked){
                    restorePrefData()
                }
            }
        }

        vspinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (adapterView?.getItemAtPosition(position)?.equals("Choose your country") == true)
                {

                }
                else
                {
                    val items:String=adapterView?.getItemAtPosition(position).toString()
                    Toast.makeText(context,"You seleted $items",Toast.LENGTH_LONG).show()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        selectedItem(2)

        cvCodBtn.setOnClickListener {
            Log.d("card", "cod")
            selectedItem(1)
        }
        cvCreditCardBtn.setOnClickListener {
            Log.d("card", "Creditcard")
         selectedItem(2)
        }
        cvPaipalBtn.setOnClickListener {
            Log.d("card", "Paipal")
            selectedItem(3)
        }

        onTextChanged()

        btnPayment.setOnClickListener {
            if(isValid())
            {
                Log.d("maulik", "Payment Successfully")
                Toast.makeText(context,"Payment Successfully",Toast.LENGTH_LONG).show()
                savePrefData()
            }
   /*         else{
                Toast.makeText(context,"Information Invalid",Toast.LENGTH_LONG).show();
            }*/
        }
        isValid()
        restorePrefData()
    }



    private fun savePrefData(){
        val name:String = eHolderName.text.toString()
        val no:String = eCardno.text.toString()
        val date:String =eDate.text.toString()
        val cvv:CharSequence=ecvv.text.toString()
        val spinner: CharSequence =vspinner.prompt

        sharedPreferences = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)!!
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("str",name)
        editor.putString("no",no)
        editor.putString("date",date)
        editor.putString("cvv", cvv.toString())
        editor.putString("spinner", spinner.toString())
        editor.apply()
        eHolderName.setText(name)
        eCardno.setText(no)
        eDate.setText(date)
        ecvv.setText(cvv)
        vspinner.prompt

    }
    private fun restorePrefData(): Boolean {
        sharedPreferences = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)!!
        val name : String? = sharedPreferences.getString("str",null)
        val no:String? = sharedPreferences.getString("no",null)
        val date:String? =sharedPreferences.getString("date",null)
        val cvv:CharSequence? = sharedPreferences.getString("cvv",null)
        val spinner: CharSequence? =sharedPreferences.getString("spinner",null)
        eHolderName.setText(name)
        eCardno.setText(no)
        eDate.setText(date)
        ecvv.setText(cvv)
        vspinner.prompt
        return true

    }

    private fun isValid():Boolean{
        var invalid = true
        val holdername : String = eHolderName.text.toString().trim()
        val cardno: String =eCardno.text.toString()
        val date : String = eDate.text.toString()
        val cvv: CharSequence =ecvv.text.toString().trim()

        if (holdername.isEmpty()){
            invalid = false
            Log.d("maulik", "name")
            Toast.makeText(context, "Enter your Card Holder Name", Toast.LENGTH_SHORT).show()
            eHolderName.error= "Enter your Card Holder Name"
            eHolderName.requestFocus()
        }
        else if(cardno.isEmpty()){
            invalid =false
            Log.d("maulik", "no")
            Toast.makeText(context, "Enter your Card Number", Toast.LENGTH_SHORT).show()
            eCardno.error="Enter your Card Number"
            eCardno.requestFocus()
        }
        else if (cardno.length <= 11){
            invalid = false
            Log.d("maulik", "lenge")
            eCardno.error="Please Enter valid Number"
            eCardno.requestFocus()

        }
        else if (date.isEmpty()){
            invalid=false
            Log.d("maulik", "date")
            Toast.makeText(context, "Invalid Month/Year", Toast.LENGTH_SHORT).show()
            eDate.error="Invalid Month/Year"
            eDate.requestFocus()

        }
        else if (cvv.isEmpty()){
            invalid=false
            Log.d("maulik", "cvv")
            Toast.makeText(context, "Enter Cvv", Toast.LENGTH_SHORT).show()
            ecvv.error = "Enter Cvv"
            ecvv.requestFocus()
        }
        else if (vspinner.selectedItem.toString().trim() == "Choose your country"){
            invalid = false
            Log.d("maulik", "spinner")
            Toast.makeText(context, "Please Select Country", Toast.LENGTH_SHORT).show();
            vspinner.requestFocus()

        }
        else{
            invalid = true
            eHolderName.error=null
            eCardno.error=null
            eDate.error=null
            ecvv.error=null
        }
        return invalid
    }

    private  fun onTextChanged(){

        eHolderName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tholderName.text = s
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        eCardno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tCardNo.text = s
                tCardNo.transformationMethod.getTransformation(AsteriskPasswordTransformationMethod().toString(),view)
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        eDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tDate.text = s
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun selectedItem(selected: Int) {
        when (selected) {
            1 -> {
                cvCodBtn.setBackgroundResource(R.drawable.payment_seleted)
                cvCreditCardBtn.setBackgroundResource(R.drawable.payment_unseleted)
                cvPaipalBtn.setBackgroundResource(R.drawable.payment_unseleted)
                codIcon.setColorFilter(Color.parseColor("#027335"))
                cdIcon.clearColorFilter()
                pmIcon.clearColorFilter()
                creditCardView.visibility = View.INVISIBLE


            }
            2 -> {
                cvCodBtn.setBackgroundResource(R.drawable.payment_unseleted)
                cvCreditCardBtn.setBackgroundResource(R.drawable.payment_seleted)
                cvPaipalBtn.setBackgroundResource(R.drawable.payment_unseleted)
                cdIcon.setColorFilter(Color.parseColor("#027335"))
                codIcon.clearColorFilter()
                pmIcon.clearColorFilter()
                creditCardView.visibility = View.VISIBLE



            }
            3 -> {
                cvCodBtn.setBackgroundResource(R.drawable.payment_unseleted)
                cvCreditCardBtn.setBackgroundResource(R.drawable.payment_unseleted)
                cvPaipalBtn.setBackgroundResource(R.drawable.payment_seleted)
                pmIcon.setColorFilter(Color.parseColor("#027335"))
                cdIcon.clearColorFilter()
                codIcon.clearColorFilter()
                creditCardView.visibility = View.INVISIBLE


            }

        }
    }

    class AsteriskPasswordTransformationMethod : PasswordTransformationMethod() {

        override fun getTransformation(source: CharSequence, view: View): CharSequence {
            return PasswordCharSequence(source)
        }

        inner class PasswordCharSequence (private val source: CharSequence) : CharSequence {

            override val length: Int
                get() = source.length

            override fun get(index: Int): Char = '*'

            override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                return source.subSequence(startIndex, endIndex)
            }

        }

    }
}

