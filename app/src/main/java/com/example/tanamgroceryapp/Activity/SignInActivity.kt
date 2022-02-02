package com.example.tanamgroceryapp.Activity

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.getSpans
import com.example.tanamgroceryapp.Data.UserProfile
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.databinding.ActivityHomeBinding
import com.example.tanamgroceryapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.etPassword
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuthException





class SignInActivity() : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private  var firebaseAuth: FirebaseAuth? = null
    private  var currentUser:FirebaseUser? = null
    private var firebaseDatabase : FirebaseDatabase? = null
    var databaseReference : DatabaseReference? = null
    private var prg: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.reference!!.child("profile")


        prg = ProgressDialog(this)

        binding.etUser.setBackgroundResource(R.drawable.edittext_selector)
        binding.etPassword.setBackgroundResource(R.drawable.edittext_selector)

        binding.signinBtn.setOnClickListener {
            isValid()
        }

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()

        }
        var isVisiblePassword = false
        binding.ivEye.setOnClickListener {

            if (!isVisiblePassword) {
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.ivEye.setImageResource(R.drawable.ic_visibility_on_eye)

                isVisiblePassword = true
            } else {
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.ivEye.setImageResource(R.drawable.ic_visibility_off_eye)
                isVisiblePassword = false
            }
            binding.etPassword.setSelection(etPassword.text.length)
        }
        binding.forgotPassword.setOnClickListener {
           val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
            if (etUser.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Enter your Email", Toast.LENGTH_SHORT).show()
            }else{
                firebaseAuth?.sendPasswordResetEmail(etUser.text.toString())?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                         currentUser = firebaseAuth?.currentUser
                        Toast.makeText(applicationContext, "Reset Password Link in mailed", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(applicationContext, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
      //  val myRefernce = firebaseDatabase?.getReference(currentUser?.uid!!)
     /*   if (currentUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }else{
              Toast.makeText(this, "Please Verified Email ", Toast.LENGTH_LONG).show()
         }*/


    }

    public override fun onStart() {
        super.onStart()
        currentUser= firebaseAuth?.currentUser
        updateUI(currentUser)
    }
    private fun isValid(): Boolean {
        var invalid = true
        val userName: String = etUser.text.toString()
        val password: String = etPassword.text.toString()
        prg?.setMessage("Please wait...")
        prg?.show()
        if (userName.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Username", Toast.LENGTH_SHORT).show()
            etUser.requestFocus()
            prg?.dismiss()
        }
        else if (password.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_SHORT).show()
            etPassword.requestFocus()
            prg?.dismiss()
            //  etPassword.error = resources.getString(R.string.password_error)
        } else if (password.length <= 7) {
            invalid = false
            etPassword.error = resources.getString(R.string.error_invalid_password)
            prg?.dismiss()

        }
        else {
            invalid = true
            etUser.error = null
            etPassword.error = null
            firebaseAuth?.signInWithEmailAndPassword(userName, password)!!.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                        prg?.dismiss()
                    val user: FirebaseUser? = firebaseAuth?.currentUser!!

                    if (user!!.isEmailVerified ){
                        startActivity(Intent(this@SignInActivity, HomeActivity::class.java).putExtra("userName",etUser.text.toString()))
                        finish()

                        Log.d("TAG", "signInWithCustomToken:success")

                        Toast.makeText(this@SignInActivity, "Successfully login", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(this@SignInActivity, "Please Verified Email ", Toast.LENGTH_LONG).show()
                    }
                } else {
                        prg?.dismiss()
                    Toast.makeText(this@SignInActivity, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        return invalid

    }

     private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, HomeActivity::class.java).putExtra("userName",etUser.text.toString()))
                finish()
                Log.d("TAG", "verify")
            }
         }

     }
}











