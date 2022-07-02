package com.advocatediary.activity.forgotPassword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.advocatediary.utils.Constants
import com.bumptech.glide.Glide
import com.e.advocatediary.R
import android.widget.Toast
import com.advocatediary.activity.forgotPassword.forgotPresenter.ForgotPresenter
import com.advocatediary.activity.forgotPassword.forgotView.ForgotView
import com.advocatediary.activity.phoneVerification.PhoneVerificationActivity
import com.advocatediary.utils.Utils
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.content_forgot_password_22.*

import java.util.concurrent.TimeUnit

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener, ForgotView {
    override fun callMessageData(phone: String) {
        startPhoneNumberVerification(phone)
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

    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        activity = this
        initUI()
    }

    private lateinit var presenter: ForgotPresenter

    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }


    private fun initUI() {
        presenter = ForgotPresenter(activity, this)
        signOut()
        tv_code.text = "+91"
        btn_forgot_password.setOnClickListener(this)
      //  rel_forgot_btn1.setOnClickListener(this)
        imgBack_forgot.setOnClickListener(this)
        linear_forgot.setOnClickListener(this)

        gif_image.setImageResource(R.raw.forgot_password_icon_gif)

      //  Glide.with(activity).load(Constants.Factory.FLAG_URL + "in.png").into(login_image_forgot)

       /* rel_forgot_password.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            rel_forgot_password.getWindowVisibleDisplayFrame(r)

            val screenHeight = rel_forgot_password.getRootView().getHeight()
            Log.e("screenHeight", screenHeight.toString())
            val heightDiff = screenHeight - (r.bottom - r.top)
            Log.e("heightDiff", heightDiff.toString())
            val visible = heightDiff > screenHeight / 3
            Log.e("visible", visible.toString())
            if (visible) {
                rel_forgot_btn1.visibility = View.VISIBLE
                rel_forgot_btn.visibility = View.GONE
            } else {
                rel_forgot_btn1.visibility = View.GONE
                rel_forgot_btn.visibility = View.VISIBLE
            }
        });*/




        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // verification completed
                showSnackbar("onVerificationCompleted:" + credential)
                Utils.hideDialog()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked if an invalid request for verification is made,
                // for instance if the the phone number format is invalid.
                //showSnackbar("onVerificationFailed")
                Utils.hideDialog()
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request

                    showSnackbar("Invalid phone number.")
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    showSnackbar("Quota exceeded.")
                }

            }

            override fun onCodeSent(
                verificationId: String?,
                token: PhoneAuthProvider.ForceResendingToken?
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                showSnackbar("onCodeSent:" + verificationId)
                // Save verification ID and resending token so we can use them later
                var mVerificationId = verificationId
                var mResendToken = token

                Utils.hideDialog()
                var intent = Intent(activity, PhoneVerificationActivity::class.java)
                intent.putExtra("verificationId", verificationId)
                intent.putExtra("screen_type", "forgot")
                intent.putExtra(
                    "phone",
                    tv_code.text.toString().trim() + "" + tv_phone_number.text.toString().trim()
                )
                startActivity(intent)
                Utils.screenOpenCloseAnimation(activity)

            }

            override fun onCodeAutoRetrievalTimeOut(verificationId: String?) {
                // called when the timeout duration has passed without triggering onVerificationCompleted
                super.onCodeAutoRetrievalTimeOut(verificationId)
                Utils.hideDialog()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
          //  rel_forgot_btn -> submitForgot()
            btn_forgot_password -> submitForgot()
            imgBack_forgot -> finish()
            linear_forgot -> Utils.hideSoftKeyboard(activity)
        }
    }

    fun submitForgot() {
        if (Utils.Factory.isNetworkAvailable(activity)) {
            presenter.hitForgotPassword(tv_code, tv_phone_number)
        } else {
            Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    fun showSnackbar(message: String) {
        //  Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
           30,             // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            this,           // Activity (for callback binding)
            mCallbacks
        )        // OnVerificationStateChangedCallbacks
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
