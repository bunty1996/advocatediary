package com.advocatediary.activity.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.advocatediary.activity.FirstScreen.FirstScreenActivity
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.activity.introScreen.IntroScreenActivity
import com.advocatediary.utils.*
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.splash_activity.*

class SplashActivity : AppCompatActivity() {

    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        hideStatusBar();
        setContentView(R.layout.activity_splash)
        activity = this
        WebServices()
        startAnimation()
    }

    private fun inIt() {
        var handler = Handler()
        handler.postDelayed(Runnable { postDataMethod() }, 1500)
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun postDataMethod() {

        var user_id = CsPreferences.readString(this, Constants.USER_ID)

        if (!(user_id.equals(""))) {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            Utils.screenOpenCloseAnimation(activity)
        } else {
            //   var intent = Intent(this, FirstScreenActivity::class.java)
            var intent = Intent(this, IntroScreenActivity::class.java)
            startActivity(intent)
            finish()
            Utils.screenOpenCloseAnimation(activity)
        }

    }

    fun startAnimation() {
        val myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val interpolator = MyBounceInterpolator()
        myAnim.interpolator = interpolator

        iv_splash_image.startAnimation(myAnim)
        myAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                inIt()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}