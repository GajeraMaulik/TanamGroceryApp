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

    }
}