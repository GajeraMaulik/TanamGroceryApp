package com.example.tanamgroceryapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tanamgroceryapp.R


class AddressFragment(): Fragment() {
    private lateinit var eAddress: EditText
     lateinit var eZipcode: EditText
     lateinit var eCity: EditText
     lateinit var vspinner: Spinner
    val items= arrayOf("Choose your country","India","Japan","China")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?= inflater.inflate(R.layout.fragment_address, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            eAddress = view.findViewById(R.id.etAddress)
            eZipcode = view.findViewById(R.id.etZipcode)
            eCity = view.findViewById(R.id.etCity)
            vspinner=view.findViewById(R.id.faSpinner)

          /*  val adapter= context?.let { ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,items) }
                vspinner.adapter=adapter*/
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

        }

         fun isvalid():Boolean{
             var invalid = true
             val address:String = eAddress.editableText.toString().trim()
             val zipcode: String = eZipcode.editableText.toString().trim()
             val city:String= eCity.editableText.toString().trim()
             if (address.isEmpty()) {
                 invalid = false
                 Log.d("maulik", "address")
                 Toast.makeText(context, "Enter your Address", Toast.LENGTH_SHORT).show()
                 eAddress.requestFocus()
             }else if (address.length >= 15){
                 invalid = false
                 Log.d("maulik", "address l")
                 eAddress.error = "Address to much length"

             }
             else if (zipcode.isEmpty()) {
                 invalid = false
                 Log.d("maulik", "code")
                 eZipcode.error = "Invalid Zipcode"
                 Toast.makeText(context, "Invalid Zipcode", Toast.LENGTH_SHORT).show()
                 eZipcode.requestFocus()
             } else if (city.isEmpty()) {
                 invalid = false
                 Log.d("maulik", "city")
                 eCity.error = "Please Enter Your City"
                 eCity.requestFocus()
             }else if (vspinner.selectedItem.toString().trim() == "Choose your country"){
                 invalid = false
                 Log.d("maulik", "spinner")
                 Toast.makeText(context, "Please Select Country", Toast.LENGTH_SHORT).show();
                 vspinner.requestFocus()

             }
             else {
                 invalid = true
                 eAddress.error = null
                 eZipcode.error = null
                 eCity.error = null
             }
             return invalid
         }
    }
