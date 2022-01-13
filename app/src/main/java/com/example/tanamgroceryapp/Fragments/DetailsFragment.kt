package com.example.tanamgroceryapp.Fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.tanamgroceryapp.R


class DetailsFragment() : Fragment() {

    private  lateinit var eFullName: EditText
    private  lateinit var eEmail:EditText
    private  lateinit var ePhone:EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eFullName=view.findViewById(R.id.etFullName)
        eEmail=view.findViewById(R.id.etEmail)
        ePhone=view.findViewById(R.id.etPhone)

        isValid()
    }
    private fun isValid():Boolean{
        var invalid = true
        val fullname : String = eFullName.text.toString().trim()
        val email: String =eEmail.text.toString().trim()
        val phone : CharSequence =ePhone.text.trim()
        //  val spinner: View = vSpinner[0]
        if (fullname.isEmpty() ){
            invalid = false
            Toast.makeText(context, "Enter your Name", Toast.LENGTH_SHORT).show()
            eFullName.requestFocus()
        }   else if (email.isEmpty()){
            invalid=false
            Toast.makeText(context, "Enter your Email", Toast.LENGTH_SHORT).show()
            eEmail.requestFocus()
            //  etEmail.setError(getResources().getString(R.string.email_error));
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(eEmail.text.toString()).matches()) {
            invalid=false
            eEmail.error = resources.getString(R.string.error_invalid_email);
            eEmail.requestFocus()

        }else if (phone.isEmpty()){
            invalid=false
            Toast.makeText(context, "Invalid Date", Toast.LENGTH_SHORT).show()
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

}