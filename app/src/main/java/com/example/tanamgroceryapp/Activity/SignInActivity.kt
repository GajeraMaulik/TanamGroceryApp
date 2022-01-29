package com.example.tanamgroceryapp.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.getSpans
import com.example.tanamgroceryapp.Data.UserProfile
import com.example.tanamgroceryapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class SignInActivity() : AppCompatActivity() {

    lateinit var etUserName: EditText
    lateinit var etPassword: EditText
    lateinit var sp: SharedPreferences
    lateinit var user: FirebaseUser
    private var isVisiblePassword = false
    private var firebaseAuth: FirebaseAuth? = null
    private var prg: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etUserName = findViewById<EditText>(R.id.user)
        etPassword = findViewById<EditText>(R.id.etPassword)

        val ivEye = findViewById<ImageView>(R.id.ivEye)
        val btn_signin: Button = findViewById(R.id.signinBtn)
        val btn_signup: Button = findViewById(R.id.btn_signup)
        val cbSingin: CheckBox = findViewById(R.id.cbSignin)


        sp = getSharedPreferences("signin", MODE_PRIVATE)

        firebaseAuth = FirebaseAuth.getInstance()
        prg = ProgressDialog(this)


        etUserName.setBackgroundResource(R.drawable.edittext_selector)
        etPassword.setBackgroundResource(R.drawable.edittext_selector)

        btn_signin.setOnClickListener {
            isValid()

        }

        btn_signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()

        }

        ivEye.setOnClickListener {
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
        /*      if (cbSingin.isChecked){
            if (user!=null){
                val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
                startActivity(i)
            }else{
                return
            }
        }*/
    }


    private fun isValid(): Boolean {
        var invalid = true
        val userName: String = etUserName.text.toString()
        val password: String = etPassword.text.toString()
        prg?.setMessage("Please wait...")
        prg?.show()
        if (userName.isEmpty()) {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Username", Toast.LENGTH_SHORT).show()
            etUserName.requestFocus()
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

        }
        /*else if(etUserName.text.toString() "admin"&& etPassword.text.toString()"admin@123")
            {
                Toast.makeText(this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
                val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
                startActivity(i)

            }*/
        else {
            invalid = true
            etUserName.error = null
            etPassword.error = null
            firebaseAuth!!.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        prg?.dismiss()
                        user = firebaseAuth?.currentUser!!
                        updateUI(user)
                    } else {
                        prg?.dismiss()
                        Toast.makeText(this, "User Not found", Toast.LENGTH_LONG).show()
                    }
                }
        }
        return invalid

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            if (user.isEmailVerified) {
                val i = Intent(this, HomeActivity::class.java).apply {
                    putExtra(
                        "Username",
                        etUserName.text.toString()
                    )
                }
                startActivity(i)
            } else {
                Toast.makeText(this, "Please Verifed Email", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Login Faild", Toast.LENGTH_LONG).show()
        }
    }
}
/*
    fun VerifiyEmail(){
        val firebaseUser : FirebaseUser? = firebaseAuth?.currentUser
        val vemail : Boolean? =firebaseUser?.isEmailVerified
        startActivity(Intent(this,HomeActivity::class.java))
            if (vemail!!){
                val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
                startActivity(i)
                finish()

            }else{
                Toast.makeText(this,"Please Verifed Email ",Toast.LENGTH_LONG).show()
               // firebaseAuth?.signOut()
            }*/






