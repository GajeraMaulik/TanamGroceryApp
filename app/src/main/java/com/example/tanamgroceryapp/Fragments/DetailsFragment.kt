package com.example.tanamgroceryapp.Fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.tanamgroceryapp.R
import android.R.string.no





class DetailsFragment() : Fragment() {

     private lateinit var eFullName: EditText
    lateinit var eEmail: EditText
    lateinit var ePhone: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eFullName = view.findViewById(R.id.etFullName)
        eEmail = view.findViewById(R.id.etEmail)
        ePhone = view.findViewById(R.id.etPhone)

    }

    fun isvalid(): Boolean {
        var invalid = true
        val fullname: String = eFullName.editableText.toString().trim()
        val email: String = eEmail.editableText.toString().trim()
        val phone: String = ePhone.editableText.toString()
        val regexStr:String = "^[0-9]$"

        if (fullname.isEmpty()) {
            invalid = false
            Log.d("maulik", "name")
            eFullName.error = "Enter your  Name"
            Toast.makeText(context, "Enter your  Name", Toast.LENGTH_SHORT).show()
            eFullName.requestFocus()
        } else if (email.isEmpty()) {
            invalid = false
            Log.d("maulik", "email")
            eEmail.error = "Enter your Email Id"
            Toast.makeText(context, "Enter your Email Id", Toast.LENGTH_SHORT).show()
            eEmail.requestFocus()
        }  else if (!Patterns.EMAIL_ADDRESS.matcher(eEmail.text.toString()).matches()) {
            invalid=false
            eEmail.error = resources.getString(R.string.error_invalid_email)
            Toast.makeText(context, "Invalid Email Id", Toast.LENGTH_SHORT).show()
            eEmail.requestFocus()

        } else if (phone.isEmpty()) {
            invalid = false
            Log.d("maulik", "phone")
            ePhone.error = "Enter Your Phone Number"
            Toast.makeText(context, "Enter Your Phone Number", Toast.LENGTH_SHORT).show()
            ePhone.requestFocus()
        }else if (phone.length <= 9){
            invalid = false
            ePhone.error = "Please Enter 10 Digite"
            ePhone.requestFocus()
        }
        else if (!Patterns.PHONE.matcher(ePhone.text.toString()).matches()){
            invalid = false
            Log.d("maulik", "Invalid phone ")
            ePhone.error = "Invalid Phone Number"
            ePhone.requestFocus()
        }
        else {
            invalid = true
            eFullName.error = null
            eEmail.error = null
            ePhone.error = null
        }
        return invalid
    }
}




