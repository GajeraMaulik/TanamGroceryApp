package com.example.tanamgroceryapp.Fragments

import android.content.Context
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.ShippingAddressActivity


class AddressFragment(): Fragment() {
    private lateinit var eAddress: EditText
    private lateinit var eZipcode: EditText
    private lateinit var eCity: EditText
    private lateinit var vspinner: Spinner
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


    }
