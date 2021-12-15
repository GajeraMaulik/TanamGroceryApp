package com.example.tanamgroceryapp.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.categories.HomeActivity


class SignInActivity() : AppCompatActivity() {

    lateinit var etUserName: EditText
    lateinit var etPassword: EditText

    private var isVisiblePassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etUserName = findViewById<EditText>(R.id.user)
        etPassword = findViewById<EditText>(R.id.etPassword)

        val ivEye = findViewById<ImageView>(R.id.ivEye)
        val btn_signin: Button = findViewById(R.id.signinBtn)
        val btn_signup: Button = findViewById(R.id.btn_signup)

        etUserName.setBackgroundResource(R.drawable.edittext_selector)
        etPassword.setBackgroundResource(R.drawable.edittext_selector)

        btn_signin.setOnClickListener {
            val userName: String = etUserName.text.toString().trim()
            val password: String = etPassword.text.toString().trim()
            val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
            startActivity(i)
            /*     if(isValid())
                 {
                     Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show();
                     val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
                     startActivity(i)
                     finish()


                 }*/
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
    }


    private fun isValid(): Boolean {
        var invalid = true
        val userName: String = etUserName.text.toString().trim()
        val password: String = etPassword.text.toString().trim()

        if (userName == "") {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Username", Toast.LENGTH_SHORT).show()
        }else if (password == "") {
            invalid = false
            Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_SHORT).show()
            //  etPassword.error = resources.getString(R.string.password_error)
        } else if (password.length <= 7) {
            invalid = false
            etPassword.error = resources.getString(R.string.error_invalid_password)

        }
        /*    else if(etUserName.text.toString() "admin"&& etPassword.text.toString()"admin@123")
            {
                Toast.makeText(this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
                val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
                startActivity(i)

            }*/
        else {
            invalid = true
            etUserName.error = null
            etPassword.error = null
            /* Toast.makeText(this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
             val i= Intent(this, HomeActivity::class.java).apply { putExtra("Username",etUserName.text.toString())}
             startActivity(i)
 */
        }
        return invalid

    }
}




