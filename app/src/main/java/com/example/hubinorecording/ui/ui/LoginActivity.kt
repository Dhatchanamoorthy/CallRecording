package com.example.hubinorecording.ui.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hubinorecording.Model.CallRecordDatabase
import com.example.hubinorecording.Model.UserDetail
import com.example.hubinorecording.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity2 : AppCompatActivity() {

    val database = CallRecordDatabase.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            if (isValidUser()) {
                moveToHomeScreen()
            } else {
                Toast.makeText(this, getString(R.string.login_invalid), Toast.LENGTH_LONG).show()
            }

        }

        btn_signup.setOnClickListener {
            moveToSignUpScreen()
        }


    }

    private fun isValidUser(): Boolean {

        val number = et_mblNumber.text.toString()
        val password = et_password.text.toString()
        var userAccount = UserDetail()
        AsyncTask.execute {
            userAccount = database.UserDao().getAccount(number)
        }
        if (number.isEmpty()) {
            et_mblNumber.error
            Toast.makeText(this, getString(R.string.invalid_usernumber), Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty()) {
            et_password.error
            Toast.makeText(this, getString(R.string.invalid_password_char), Toast.LENGTH_SHORT)
                .show()
            return false
        }


        if (userAccount.password.equals(password)) {
            return true
        }

        return false

    }

    private fun moveToSignUpScreen() {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    private fun moveToHomeScreen() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
