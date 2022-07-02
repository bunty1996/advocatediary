package com.advocatediary.activity.introScreen

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity;
import android.view.View
import com.advocatediary.activity.login.LoginActivity
import com.advocatediary.activity.register.RegisterActivity
import com.advocatediary.adapter.RVIntroViewPagerAdapter
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_intro_screen.*
import io.blackbox_vision.wheelview.view.WheelView

class IntroScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
              tv_signup_title -> gotoSignupScreen()
            tv_login_title -> gotoLoginScreen()
        }
    }

    fun gotoSignupScreen() {
        tv_signup_title.setTextColor(Color.parseColor("#ffffff"))
        tv_signup_title.setBackgroundResource(R.drawable.selected_intro_screen)

        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                var signupIntent = Intent(activity, RegisterActivity::class.java)
                startActivity(signupIntent)
                Utils.screenOpenCloseAnimation(activity)
            }
        }, 1000)
    }

    fun gotoLoginScreen() {
        tv_login_title.setTextColor(Color.parseColor("#ffffff"))
        tv_login_title.setBackgroundResource(R.drawable.selected_intro_screen)

        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                var loginIntent = Intent(activity, LoginActivity::class.java)
                startActivity(loginIntent)
                Utils.screenOpenCloseAnimation(activity)
            }
        }, 1000)
    }

    private lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screen)
        activity = this

        tv_signup_title.setOnClickListener(this)
        tv_login_title.setOnClickListener(this)
        initUI()
    }

    private fun initUI() {
        var imageList = ArrayList<Int>()
        imageList.add(R.drawable.screen_first)
        imageList.add(R.drawable.screen_second)
        imageList.add(R.drawable.screen_third)

        var adapter = RVIntroViewPagerAdapter(activity, imageList);
        pager.adapter = adapter

        gif_image_view.setImageResource(R.raw.intro_imagess)
    }

    private lateinit var wheelView: WheelView

    override fun onResume() {
        super.onResume()
        tv_signup_title.setTextColor(getColor(R.color.colorPrimary))
        tv_signup_title.setBackgroundResource(R.drawable.intro_state_default)

        tv_login_title.setTextColor(getColor(R.color.colorPrimary))
        tv_login_title.setBackgroundResource(R.drawable.intro_state_default)
    }
}
