package com.example.tanamgroceryapp.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Log.d
import android.util.Patterns
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.tanamgroceryapp.Data.UserProfile
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.databinding.ActivitySignInBinding
import com.example.tanamgroceryapp.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.etPassword
import kotlinx.android.synthetic.main.activity_sign_up.ivEye
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.etEmail
import java.util.Calendar.getInstance

open class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private  var prg : ProgressDialog? = null
    private lateinit var binding : ActivitySignUpBinding
    lateinit var userName :String
    lateinit var email: String
    lateinit var password : String
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference :DatabaseReference? = null
    var user:FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.etEmail.setBackgroundResource(R.drawable.edittext_selector)
        binding.etUserName.setBackgroundResource(R.drawable.edittext_selector)
        binding.etPassword.setBackgroundResource(R.drawable.edittext_selector)

        firebaseAuth = FirebaseAuth.getInstance()
        prg = ProgressDialog(this)

        binding.signinBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        binding.registerBtn.setOnClickListener {
            isValid()
        }

        var isVisiblePassword = false

        binding.ivEye.setOnClickListener {
            if (!isVisiblePassword) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivEye.setImageResource(R.drawable.ic_visibility_on_eye)

                isVisiblePassword = true
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ivEye.setImageResource(R.drawable.ic_visibility_off_eye)
                isVisiblePassword = false
            }
            etPassword.setSelection(etPassword.text.length)
        }

    }

    private fun isValid(): Boolean {
        var invalid = true
         userName = etUserName.text.toString().trim()
         email = etEmail.text.toString().trim()
         password  = etPassword.text.toString().trim()
        prg?.setMessage("Please wait...")
        prg?.show()

        if (userName.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Username", Toast.LENGTH_SHORT).show()
            etUserName.requestFocus()
            prg?.dismiss()
        } else if (userName.length <= 8) {
            invalid = false
            prg?.dismiss()
            etUserName.error ="Please enter a minimum 8 characters"

        }
        else if (email.isEmpty()){
            invalid=false
            Toast.makeText(applicationContext, "Enter your Email", Toast.LENGTH_SHORT).show()
            etEmail.requestFocus()
            prg?.dismiss()
            //  etEmail.setError(getResources().getString(R.string.email_error));
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches() ) {
            invalid=false
            etEmail.error = resources.getString(R.string.error_invalid_email)
            etEmail.requestFocus()
            prg?.dismiss()

        }
        else if (password.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_SHORT).show()
            etPassword.requestFocus()
            prg?.dismiss()
            //  etPassword.error = resources.getString(R.string.password_error)
        }
        else if (password.length <= 7){
            invalid=false
            etPassword.error=resources.getString(R.string.error_invalid_password)
            etPassword.requestFocus()
            prg?.dismiss()

        }
        else if (!checkString(password)){
            invalid=false
            etPassword.error="Password must contain at least 8 characters;must contain alphanumeric;must contain One Capital alphabet."
            etPassword.requestFocus()
            prg?.dismiss()

        }
        else {
            invalid = true
            etUserName.error = null
            etEmail.error =null
            etPassword.error= null
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener  { task ->

                if (task.isSuccessful ) {
                        prg?.dismiss()
                    user =firebaseAuth.currentUser
                    user!!.sendEmailVerification().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                firebaseAuth.fetchSignInMethodsForEmail(etEmail.text.toString()).addOnCompleteListener { task ->
                                    if (task.isSuccessful){
                                        VerifyEmail()
                                        Toast.makeText(this, "${task.exception?.message}" + etUserName.text.toString(), Toast.LENGTH_LONG).show()
                                        finish()
                                        startActivity(Intent(this,SignInActivity::class.java ))
                                        Senddata()
                                        Log.d("TAG","Successfully Registration")
                                        Log.d("TAG","Email verified")
                                    }else{
                                        Log.d("TAG","Email Exits")
                                        // startActivity(Intent(this, SignUpActivity::class.java))
                                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                                    }
                                }

                            }else{
                                Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                        prg?.dismiss()
                    Log.d("TAG","Email Exits")
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        return invalid
    }

    private fun checkString(str: String): Boolean {
        var ch: Char
        var capitalFlag = false
        var lowerCaseFlag = false
        var numberFlag = false
        for (element in str) {
            ch = element

            when {
                Character.isDigit(ch) -> numberFlag = true
                Character.isUpperCase(ch) -> capitalFlag = true
                Character.isLowerCase(ch) -> lowerCaseFlag = true
            }

            if (numberFlag && capitalFlag && lowerCaseFlag) return true
        }
        return false
    }

     fun  VerifyEmail(){
        val firebaseUser : FirebaseUser? = firebaseAuth.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (!task.isComplete){
                Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                finish()
                Senddata()
                startActivity(Intent(this,SignInActivity::class.java ))

            }else{
                Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()

            }
        }
    }

      fun Senddata(){
          val currentuser = firebaseAuth.currentUser
          firebaseDatabase = FirebaseDatabase.getInstance()
          databaseReference = firebaseDatabase?.reference!!.child("profile")
          val myRefernce = databaseReference?.child(currentuser?.uid!!)
         val userProfile = UserProfile(userName,email,password)
          myRefernce?.child("userName")?.setValue(binding.etUserName.text.toString())
          myRefernce?.child("email ")?.setValue(binding.etEmail.text.toString())
          myRefernce?.child("password ")?.setValue(binding.etPassword.text.toString())

         Log.d("TAG","senddata")
    }
}



