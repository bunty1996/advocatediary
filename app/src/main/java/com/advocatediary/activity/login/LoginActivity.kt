package com.advocatediary.activity.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import com.advocatediary.activity.forgotPassword.ForgotPasswordActivity
import com.advocatediary.activity.login.loginPresenter.LoginPresenter
import com.advocatediary.activity.login.loginView.LoginView
import com.advocatediary.activity.phoneVerification.PhoneVerificationActivity
import com.advocatediary.activity.register.RegisterActivity
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_login_22.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginView {

    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        activity = this;
        initUI()
    }

    private lateinit var presenter: LoginPresenter

    private fun initUI() {
//WebServices.Factory.create()
      /*  Glide.with(this).load(Constants.Factory.FLAG_URL + "in.png").into(login_image)*/
        tv_code.text = "+91"

        presenter = LoginPresenter(activity, this)
      //  imgBack.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
       // rel_done_button.setOnClickListener(this)
        btnlogin.setOnClickListener(this)
     //   rel_done_button11.setOnClickListener(this)
     //   imgBack.setOnClickListener(this)
        //tv_phone_number.clearFocus()
        //et_passwordFirst.clearFocus()
        // tv_phone_number.requestLayout()

        tv_register.setOnClickListener(this)
        ll_login_layout.setOnClickListener(this)

        editTextChangeListener()


        rel_top_login.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            rel_top_login.getWindowVisibleDisplayFrame(r)

            val screenHeight = rel_top_login.getRootView().getHeight()
            Log.e("screenHeight", screenHeight.toString())
            val heightDiff = screenHeight - (r.bottom - r.top)
            Log.e("heightDiff", heightDiff.toString())
            val visible = heightDiff > screenHeight / 3
            Log.e("visible", visible.toString())
            if (visible) {
                linear_bottom_login.visibility = View.VISIBLE
                //linear_topp.visibility = View.GONE
            } else {
                linear_bottom_login.visibility = View.GONE
               // linear_topp.visibility = View.VISIBLE
            }
        });
    }

    private fun editTextChangeListener() {

        tv_phone_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               /* if (tv_phone_number.text.toString().trim().length == 0) {
                    tv_phone_login_title.visibility = View.INVISIBLE
                } else {
                    tv_phone_login_title.visibility = View.VISIBLE
                }*/
            }
        })

        et_passwordFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              /*  if (et_passwordFirst.text.toString().trim().length == 0) {
                    tv_password_login_title.visibility = View.INVISIBLE
                } else {
                    tv_password_login_title.visibility = View.VISIBLE
                }*/
            }
        })
    }

    // for back button fuctionality
    fun backButton() {
        finish()
    }

    override fun onClick(v: View?) {
        when (v) {
         //   imgBack -> backButton()
            rel_done_button -> doneButtonMethod()
            btnlogin -> doneButtonMethod()
           // rel_done_button11 -> doneButtonMethod()
            tv_forgot_password -> forgotPasswordMethod()
            tv_register -> registerScreenMethod()
            ll_login_layout->Utils.hideSoftKeyboard(activity)
          //  imgBack -> finish()
         //   linear_loginn -> Utils.hideSoftKeyboard(activity)
        }
    }

    // for submit button fuctionality
    fun doneButtonMethod() {
        if (Utils.Factory.isNetworkAvailable(activity)) {
            var codeNumber = tv_code.text.toString().trim()
            var phoneNumber = tv_phone_number.text.toString()
            presenter.loginMethod(tv_code, tv_phone_number, et_passwordFirst)
        } else {
            Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    // for forgot password method
    fun forgotPasswordMethod() {
        var intent = Intent(activity, ForgotPasswordActivity::class.java)
        startActivity(intent)
        Utils.screenOpenCloseAnimation(activity)
    }

    // for Register Screen Method
    fun registerScreenMethod() {
       var intent = Intent(activity, RegisterActivity::class.java)
     //   var intent = Intent(activity, PhoneVerificationActivity::class.java)
        startActivity(intent)
        Utils.screenOpenCloseAnimation(activity)
    }

    /* fun Activity.isKeyboardOpen(): Boolean {
         val visibleBounds = Rect()
         this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
         val heightDiff = getRootView().height - visibleBounds.height()
         val marginOfError = Math.round(this.convertDpToPx(50F))
         return heightDiff > marginOfError
     }

     fun Activity.isKeyboardClosed(): Boolean {
         return !this.isKeyboardOpen()
     }

    fun Activity.getRootView(): View {
         return findViewById<View>(android.R.id.content)
     }
 */
    fun Context.convertDpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            this.resources.displayMetrics
        )
    }

    /* val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
         // Keep a reference to the last state of the keyboard
         private var lastState: Boolean = isKeyboardOpen()

         */
    /**
     * Something in the layout has changed
     * so check if the keyboard is open or closed
     * and if the keyboard state has changed
     * save the new state and invoke the callback
     *//*
        override fun onGlobalLayout() {
            val isOpen = isKeyboardOpen()
            if (isOpen == lastState) {
                linear_topp.visibility = View.GONE
                linear_bottom_login.visibility = View.VISIBLE
                return
            } else {
                // dispatchKeyboardEvent(isOpen)
                linear_topp.visibility = View.VISIBLE
                linear_bottom_login.visibility = View.GONE
                lastState = isOpen
            }
        }
    }*/

    override fun onResume() {
        super.onResume()
        /*val view = getRootView()
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)*/
    }

    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
