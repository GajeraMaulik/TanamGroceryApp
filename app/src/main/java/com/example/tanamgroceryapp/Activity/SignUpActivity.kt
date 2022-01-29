package com.example.tanamgroceryapp.Activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.*
import com.example.tanamgroceryapp.Data.UserProfile
import com.example.tanamgroceryapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var etUserName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var userName: String
    lateinit var email : String
    lateinit var password: String
    lateinit var user :String

    private var isVisiblePassword = false
    private  var firebaseAuth : FirebaseAuth? = null
    private  var prg : ProgressDialog? = null

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

        firebaseAuth = FirebaseAuth.getInstance()
        prg = ProgressDialog(this)
        user= firebaseAuth?.currentUser.toString()

        btn_signin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            //  setValidation()
        }
        btn_register.setOnClickListener {
           if (isValid()) {
               startActivity(Intent(this, SignInActivity::class.java))
           }else{
               return@setOnClickListener
           }
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
         userName = etUserName.text.toString().trim()
        email= etEmail.text.toString().trim()
        password = etPassword.text.toString().trim()
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
        else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
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
            etPassword.error="Password must be One Upercase Letter or number"
            etPassword.requestFocus()
            prg?.dismiss()

        }
        else {
            invalid = true
            etUserName.error = null
            etEmail.error =null
            etPassword.error= null
            firebaseAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful ){
                    prg?.dismiss()
                    checkEmail()
                    Senddata()
                    Toast.makeText(this, "Successful " + etUserName.text.toString(), Toast.LENGTH_SHORT).show()
           /*         startActivity(Intent(this, SignInActivity::class.java))
                    finish()*/

                }else{
                    prg?.dismiss()
                    Toast.makeText(this,"Error Occur",Toast.LENGTH_LONG).show()
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

    fun  checkEmail(){
        val firebaseUser : FirebaseUser? = firebaseAuth?.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Verification mail sent ",Toast.LENGTH_LONG).show()
              //  firebaseAuth?.signOut()
                finish()
            //    startActivity(Intent(this,SignInActivity::class.java))

            }else{
                Toast.makeText(this,"Error Occur ",Toast.LENGTH_LONG).show()
            }
        }
    }

    private  fun Senddata(){
        val firebaseDatabase = FirebaseDatabase.getInstance()
          val myRefernce = firebaseDatabase.getReference(firebaseAuth?.uid.toString())
          val userProfile = UserProfile(userName,email,password)
          myRefernce.setValue(userProfile)
    }



}



