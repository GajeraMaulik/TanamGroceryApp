package com.example.tanamgroceryapp.Activity

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Log.d
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.etPassword
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.widget.Toast
import com.example.tanamgroceryapp.Data.UserProfile
import com.google.firebase.database.ktx.getValue

import kotlinx.android.synthetic.main.fragment_details.*


class SignInActivity() : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser:FirebaseUser
    private lateinit var ref : DatabaseReference
    private var prg: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val firebaseDatabase = FirebaseDatabase.getInstance()

        ref= firebaseDatabase.reference

        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tanamgroceryapp-default-rtdb.firebaseio.com/")

        prg = ProgressDialog(this)

        binding.etUser.setBackgroundResource(R.drawable.edittext_selector)
        binding.etPassword.setBackgroundResource(R.drawable.edittext_selector)

        binding.signinBtn.setOnClickListener {
       /*     username = binding.etUser.text.toString()
            password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Enter Username & password",Toast.LENGTH_LONG).show()
            }else{
                ref.child("Users").addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.hasChild(username)){

                            val getpassword:String = snapshot.child(username).child("Password").value as String

                            if (getpassword == password){
                                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                                finish()
                                Toast.makeText(this@SignInActivity,"Successfully login",Toast.LENGTH_LONG).show()
                            }else{
                                Log.d("TAG", "Wrong: $password\nusername1: $username")
                                Toast.makeText(this@SignInActivity,"Wrong password",Toast.LENGTH_LONG).show()
                            }
                        }else{
                            Log.d("TAG", "email2: $username\n pass2: $username")
                            Toast.makeText(this@SignInActivity,"User Not Found",Toast.LENGTH_LONG).show()
                        }
                       // Log.d("TAG", "email: $username\n pass: $password")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        error.toException().message
                    }

                })
            }
*/
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
//            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
            if (etUser.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Enter your Email", Toast.LENGTH_SHORT).show()
                etUser.requestFocus()
            }else{
                firebaseAuth.sendPasswordResetEmail(etUser.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        currentUser = firebaseAuth.currentUser!!
                        Toast.makeText(applicationContext, "Reset Password Link Sent ${etUser.text}", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(applicationContext, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        
      //  val myRefernce = firebaseDatabase?.getReference(currentUser?.uid!!)
    /*    val currentUser= firebaseAuth.currentUser
        if (currentUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }else{
              Toast.makeText(this, "Please Verified Email ", Toast.LENGTH_LONG).show()
         }
*/

    }
/*
    override fun  onStart(){
        super.onStart()
        val currentUser= firebaseAuth.currentUser
        if (currentUser == null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
*/



    private fun isValid(): Boolean {
        var invalid = true
         username = binding.etUser.text.toString()
         password = binding.etPassword.text.toString()

       // val myRefernce = databaseReference?.child(currentuser?.uid!!)
        prg?.setMessage("Please wait...")
        prg?.show()
        if (username.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Username", Toast.LENGTH_SHORT).show()
            etUser.requestFocus()
            prg?.dismiss()
        } else if (password.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_SHORT).show()
            etPassword.requestFocus()
            prg?.dismiss()
            //  etPassword.error = resources.getString(R.string.password_error)
        } else if (password.length <= 7) {
            invalid = false
            etPassword.error = resources.getString(R.string.error_invalid_password)
            prg?.dismiss()

        } else {
            invalid = true
            etUser.error = null
            etPassword.error = null

            // with database varification
           ref.child("Users").addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(username)){

                        val getpassword:String = snapshot.child(username).child("Password").value as String
                        if (getpassword == password ){
                            startActivity(Intent(this@SignInActivity, HomeActivity::class.java).putExtra("Username",username))
                            Toast.makeText(this@SignInActivity,"Successfully login",Toast.LENGTH_LONG).show()
                            finish()

                        }else{
                            Log.d("TAG", "Wrong: $password\nusername1: $username")
                            Toast.makeText(this@SignInActivity,"Wrong password",Toast.LENGTH_LONG).show()
                            etPassword.error = "Incorrect Password"
                            etPassword.requestFocus()
                            prg?.dismiss()
                        }
                    }else{
                        Log.d("TAG", "email2: $username\n pass2: $username")
                        Toast.makeText(this@SignInActivity,"User Not Found",Toast.LENGTH_LONG).show()
                        etUser.error ="User Not Found"
                        etUser.requestFocus()
                        prg?.dismiss()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().message
                }

            })

            // with email varifition

          /*  firebaseAuth = FirebaseAuth.getInstance()
           firebaseAuth.fetchSignInMethodsForEmail(username).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                     firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        prg?.dismiss()
                        val user: FirebaseUser? = firebaseAuth.currentUser!!

                                    if (user!!.isEmailVerified) {
                                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        intent.putExtra("username", FirebaseAuth.getInstance().currentUser!!.displayName)
                                       intent.putExtra("email", username)
                                        startActivity(intent)
                                        finish()
                                        Log.d("TAG", "email: $username\n password: $password")
                                        Log.d("TAG", "signInWithCustomToken:success")

                                        Toast.makeText(this@SignInActivity, "Successfully login", Toast.LENGTH_LONG).show()

                                    } else {
                                        Log.d("TAG", "Please Verified Email")

                                        Toast.makeText(this@SignInActivity, "Please Verified Email ", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                     prg?.dismiss()
                                       Log.d("TAG", "${task.exception?.message}")

                                     Toast.makeText(this@SignInActivity, task.exception?.message, Toast.LENGTH_LONG).show()
                                }
                            }
                    }else{
                        prg?.dismiss()
                        Toast.makeText(this@SignInActivity, "user not ", Toast.LENGTH_LONG).show()
                    }
                }*/
        }
        return invalid

    }


}











