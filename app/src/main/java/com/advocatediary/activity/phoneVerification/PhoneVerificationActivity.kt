package com.advocatediary.activity.phoneVerification

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.advocatediary.activity.phoneVerification.phoneVerificationPresenter.PhoneVerificationPresenter
import com.advocatediary.activity.phoneVerification.phoneVerifyView.PhoneVerifyView
import com.advocatediary.activity.resetPassword.ResetPasswordActivity
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.content_phone_verification_22.*
import java.util.concurrent.TimeUnit

class PhoneVerificationActivity : AppCompatActivity(), View.OnClickListener, PhoneVerifyView {
    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            bt_verify-> submit()
            linear_verifications -> Utils.hideSoftKeyboard(activity)
            tv_resend_code -> resendCodeMethod()
        }
    }

    // for resend code method
    fun resendCodeMethod() {
        Utils.showDialog(activity)
        startPhoneNumberVerification(phoneNumber!!)
    }

    private lateinit var activity: Activity
    private lateinit var authFirebase: FirebaseAuth

    private lateinit var presenter: PhoneVerificationPresenter
    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)
        activity = this
        authFirebase = FirebaseAuth.getInstance()
        presenter = PhoneVerificationPresenter(activity, this)
        initUI()

    }

    private lateinit var verificationId: String

    private var phoneNumber: String? = null

    private var reg_data: String? = null

    private var screen_type: String? = null

    private fun initUI() {
        verificationId = intent.getStringExtra("verificationId")
         phoneNumber = intent.getStringExtra("phone")

        bt_verify.setOnClickListener(this)
         linear_verifications.setOnClickListener(this)

      //   tv_title_value.text = "Enter the 6-digit code sent to you \n at " + phoneNumber
         tv_title_value.text = "A Verification code has been sent to " + phoneNumber

         screen_type = intent.getStringExtra("screen_type")
         if (screen_type.equals("register")) {
             reg_data = intent.getStringExtra("reg_data")
         }

        tv_resend_code.setOnClickListener(this)
        edittextChangeListener()

        gif_image_verify.setImageResource(R.raw.verify_gif)

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
                verificationId1: String?,
                token: PhoneAuthProvider.ForceResendingToken?
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                showSnackbar("onCodeSent:" + verificationId1)
                // Save verification ID and resending token so we can use them later
                var mVerificationId = verificationId1
                var mResendToken = token

                Utils.hideDialog()
                verificationId = verificationId1!!
                //   phoneNumber = intent.getStringExtra("phone")
                /*var intent = Intent(activity, PhoneVerificationActivity::class.java)
                intent.putExtra("verificationId", verificationId)
                intent.putExtra("screen_type", "forgot")
                intent.putExtra(
                    "phone",
                    tv_code.text.toString().trim() + "" + tv_phone_number.text.toString().trim()
                )
                startActivity(intent)
                Utils.screenOpenCloseAnimation(activity)*/

            }

            override fun onCodeAutoRetrievalTimeOut(verificationId: String?) {
                // called when the timeout duration has passed without triggering onVerificationCompleted
                super.onCodeAutoRetrievalTimeOut(verificationId)
                Utils.hideDialog()
            }
        }


    }

    private fun edittextChangeListener() {

        et_first_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_first_text.text.toString().trim().length > 0) {
                    et_second_text.requestFocus()
                }
            }
        })

        et_second_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_second_text.text.toString().trim().length == 0) {
                    et_first_text.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_second_text.text.toString().trim().length > 0) {
                    et_third_text.requestFocus()
                }
            }
        })

        et_third_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_third_text.text.toString().trim().length == 0) {
                    et_second_text.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_third_text.text.toString().trim().length > 0) {
                    et_fourth_text.requestFocus()
                }
            }
        })

        et_fourth_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_fourth_text.text.toString().trim().length == 0) {
                    et_third_text.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_fourth_text.text.toString().trim().length > 0) {
                    et_fifth_text.requestFocus()
                }
            }
        })

        et_fifth_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_fifth_text.text.toString().trim().length == 0) {
                    et_fourth_text.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_fifth_text.text.toString().trim().length > 0) {
                    et_sixth_text.requestFocus()
                }
            }
        })

        et_sixth_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_sixth_text.text.toString().trim().length == 0) {
                    et_fifth_text.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun submit() {
        if (Utils.Factory.isNetworkAvailable(activity)) {

            var code =
                et_first_text.text.toString().trim() + "" + et_second_text.text.toString().trim() + "" + et_third_text.text.toString().trim() + "" +
                        et_fourth_text.text.toString().trim() + "" +
                        et_fifth_text.text.toString().trim() + "" +
                        et_sixth_text.text.toString().trim()

            if (code.length == 6) {
                Utils.showDialog(activity)
                Utils.hideSoftKeyboard(activity)
                val credential = PhoneAuthProvider.getCredential(verificationId, code)

                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(activity, "Please enter 6 digit code", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        authFirebase.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    showSnackbar("signInWithCredential:success")

                    if (screen_type.equals("register")) {
                        // for Register
                        presenter.phoneVerifyMethod(phoneNumber!!, reg_data!!)
                    } else {
                        Utils.hideDialog()
                        // for Forgot Password
                        var intent = Intent(activity, ResetPasswordActivity::class.java)
                        intent.putExtra("phone", phoneNumber)
                        activity.startActivity(intent)
                        Utils.screenOpenCloseAnimation(activity)
                    }

                } else {
                    Utils.hideDialog()
                    // Sign in failed, display a message and update the UI
                    showSnackbar("signInWithCredential:failure")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        showSnackbar("Invalid code was entered")
                        Toast.makeText(activity, "Please enter correct code", Toast.LENGTH_LONG).show()
                    }
                    // Sign in failed
                }
            }
    }

    fun showSnackbar(message: String) {
        // Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60,             // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            this,           // Activity (for callback binding)
            mCallbacks
        )        // OnVerificationStateChangedCallbacks
    }
}
