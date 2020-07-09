package com.example.hubinorecording.ui.ui

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hubinorecording.Model.CallRecordDatabase
import com.example.hubinorecording.Model.UserDetail
import com.example.hubinorecording.R
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.btn_signup
import kotlinx.android.synthetic.main.activity_signup.et_password

class SignupActivity : AppCompatActivity() {

    val database = CallRecordDatabase.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)



        btn_signup.setOnClickListener {
           if (checkValidUser()){
               Toast.makeText(this,getString(R.string.signup_success), Toast.LENGTH_SHORT).show()
               saveUserDetail()
              moveToHomeScreen()
           }
        }


    }

    private fun saveUserDetail() {
        var name = username.text.toString()
        var number = usernumber.text.toString()
        var password = et_password.text.toString()

        var data = UserDetail(name,number,password)
        AsyncTask.execute {
            // Insert Data
            database.UserDao().insertUserData(data)
        }
    }

    private fun moveToHomeScreen() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun checkValidUser(): Boolean {
        if (username.text.isNullOrEmpty()){
            username.error
            Toast.makeText(this,getString(R.string.invalid_username), Toast.LENGTH_SHORT).show()
            return false
        }
        if (usernumber.text.isNullOrEmpty()){
            usernumber.error
            Toast.makeText(this,getString(R.string.invalid_usernumber), Toast.LENGTH_SHORT).show()
            return false
        }

        if (et_password.text.isNullOrEmpty()){
            et_password.error
            Toast.makeText(this,getString(R.string.invalid_password_char), Toast.LENGTH_SHORT).show()
            return false
        }

        if (!et_password.text.isNullOrEmpty()){
            val password = et_password.text.toString()
            if (isPasswordValid(password)) {
                return true
            } else{
                et_password.error
                Toast.makeText(this,getString(R.string.invalid_password), Toast.LENGTH_SHORT).show()
                return false
            }
        }

       return true

    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
