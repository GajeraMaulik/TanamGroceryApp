package com.example.tanamgroceryapp.Fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter
import com.example.tanamgroceryapp.R


class AddressFragment(): Fragment() {
    private  lateinit var eAddress: EditText
    private  lateinit var eZipcode: EditText
    private  lateinit var eCity:EditText
    private  lateinit var spinner:Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?=inflater.inflate(R.layout.fragment_address, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eAddress=view.findViewById(R.id.etAddress)
        eZipcode=view.findViewById(R.id.etZipcode)
        eCity=view.findViewById(R.id.etCity)
        isValid()
    }
    private fun isValid():Boolean{
        var invalid = true
        val address : String = eAddress.text.toString().trim()
        val zipcode: String =eZipcode.text.toString().trim()
        val city : CharSequence =eCity.text.trim()
        //  val spinner: View = vSpinner[0]
        if (address.isEmpty()){
            invalid = false
            Toast.makeText(context, "Enter your Address", Toast.LENGTH_SHORT).show()
            eAddress.requestFocus()
        } else if (zipcode.isEmpty()){
            invalid=false
            Toast.makeText(context, "Invalid Zipcode", Toast.LENGTH_SHORT).show()
            eZipcode.requestFocus()
        }else if (city.isEmpty()){
            eCity.error="Please enter a valid value"
            eCity.requestFocus()
        }
        else{
            invalid = true
            eAddress.error=null
            eZipcode.error=null
            eCity.error=null
        }
        return invalid
    }

}