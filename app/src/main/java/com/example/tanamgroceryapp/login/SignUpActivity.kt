package com.example.tanamgroceryapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.*
import com.example.tanamgroceryapp.R

class SignUpActivity : AppCompatActivity() {
    lateinit var etUserName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText

    private var isVisiblePassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val ivEye = findViewById<ImageView>(R.id.ivEye)
        etUserName = findViewById<EditText>(R.id.eduser)
        etEmail=findViewById<EditText>(R.id.edMail)
        etPassword = findViewById<EditText>(R.id.etPassword)


        val btn_signin: Button =findViewById(R.id.btn_signin)
        val btn_register:Button=findViewById(R.id.btn_register)

        etEmail.setBackgroundResource(R.drawable.edittext_selector)
        etUserName.setBackgroundResource(R.drawable.edittext_selector)
        etPassword.setBackgroundResource(R.drawable.edittext_selector)


        btn_signin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            //  setValidation()
        }
        btn_register.setOnClickListener {
            if (isValid()) {
                startActivity(Intent(this, SignInActivity::class.java))
                Toast.makeText(this, "Register " + etUserName.getText().toString(), Toast.LENGTH_SHORT).show();
                finish()

            }
        }

        ivEye.setOnClickListener {
            if (!isVisiblePassword) {
                etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
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
        val userName: String = etUserName.text.toString().trim()
        val email : String=etEmail.text.toString().trim()
        val password: String = etPassword.text.toString().trim()

        // Password should contain at least one number
        val passwordPattern = "^(?=.*[0-9])"+"(?=.*[a-z])"+"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$).{4,}$";



        if (userName == "") {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Username", Toast.LENGTH_SHORT).show()
        } else if (userName.length <= 8) {
            invalid = false
            etUserName.error ="Please enter a minimum 8 characters"

        }
        else if (email== ""){
            invalid=false
            Toast.makeText(applicationContext, "Enter your Email", Toast.LENGTH_SHORT).show()
            //  etEmail.setError(getResources().getString(R.string.email_error));
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            invalid=false
            etEmail.error = resources.getString(R.string.error_invalid_email);

        }
        else if (password == "") {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_SHORT).show()
            //  etPassword.error = resources.getString(R.string.password_error)
        }
        else if (password.length <= 8){
            invalid=false
            etPassword.error=resources.getString(R.string.error_invalid_password)

        }

        else {
            invalid = true
            etUserName.error = null
            etEmail.error =null
            etPassword.error= null

        }
        return invalid

    }



}



