package com.advocatediary.activity.FirstScreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import com.advocatediary.activity.login.LoginActivity
import com.advocatediary.activity.register.RegisterActivity
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_first_screen.*

class FirstScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            relative_sign -> signIn();
            relative_register -> registerScreen()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
        initUI();
    }

    // for Initilizing
    private fun initUI() {
        relative_sign.setOnClickListener(this)
        relative_register.setOnClickListener(this)
    }

    // for sign In screen
    fun signIn() {
        var loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        Utils.screenOpenCloseAnimation(this)
    }

    // for Register  screen
    fun registerScreen() {
        var loginIntent = Intent(this, RegisterActivity::class.java)
        startActivity(loginIntent)
        Utils.screenOpenCloseAnimation(this)
    }
}
