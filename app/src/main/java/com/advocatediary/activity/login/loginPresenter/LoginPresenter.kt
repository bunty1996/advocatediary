package com.advocatediary.activity.login.loginPresenter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.activity.login.loginView.LoginView
import com.advocatediary.handler.LoginHandler
import com.advocatediary.model.login.LoginExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices

class LoginPresenter(var activity: Activity, var loginView: LoginView) {
    lateinit var webservice: WebServices
    lateinit var phoneNumber: EditText
    lateinit var password: EditText
    lateinit var codeNumber: TextView;

    // var loginView: LoginView? = null

    fun loginMethod(codeNumber: TextView, phoneNumber: EditText, password: EditText) {

        this.phoneNumber = phoneNumber;
        this.password = password;
        this.codeNumber = codeNumber;

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)
            /* Toast.makeText(activity, "Successfully Login", Toast.LENGTH_LONG).show()
             var intent = Intent(activity, HomeActivity::class.java)
             activity.startActivity(intent)*/
            loginView!!.showDialog(activity)
            WebServices.Factory1.getInstance()?.loginCallMethod(
                codeNumber.text.toString().trim() + "" + phoneNumber.text.toString().trim(),
                password.text.toString().trim(),
                "1",
                "",
                object : LoginHandler {
                    override fun onSuccess(loginExample: LoginExample) {
                        loginView!!.hideDialog()
                        if (loginExample != null) {
                            if (loginExample.response?.status.equals("1")) {
                                Log.e("Success", loginExample.response?.message.toString())

                                CsPreferences.Factory.putString(
                                    activity,
                                    Constants.USER_ID,
                                    loginExample.response!!.data!!.id!!
                                )
                                CsPreferences.Factory.putString(
                                    activity,
                                    Constants.USER_NAME,
                                    loginExample.response!!.data!!.fullName!!
                                )
                                CsPreferences.Factory.putString(
                                    activity,
                                    Constants.USER_PHONE,
                                    loginExample.response!!.data!!.phone!!
                                )

                                CsPreferences.Factory.putString(
                                    activity,
                                    Constants.USER_STATE,
                                    loginExample.response!!.data!!.stateId!!
                                )
                                CsPreferences.Factory.putString(
                                    activity,
                                    Constants.USER_DISTRICT,
                                    loginExample.response!!.data!!.districtId!!
                                )

                                var intent = Intent(activity, HomeActivity::class.java)
                                activity?.startActivity(intent)
                                activity.finishAffinity()
                            }
                            loginView.showMessage(activity,loginExample.response?.message!!)
                        }
                    }

                    override fun onError(message: String) {
                        Log.e("error", message)
                        loginView!!.hideDialog()
                    }
                })
        }
    }


    fun checkValidation(): Boolean {
        if (phoneNumber.text.toString().trim().length == 0) {
            //loginView?.showMessage(activity, "Please enter phone number")
            phoneNumber.error = "Please enter phone number"
            return false
        } else
            if (password.text.toString().trim().length == 0) {
                // loginView!!.showMessage(activity, "Please enter password")
                password.error = "Please enter password"
                return false
            } else
                if (password.text.toString().trim().length < 6) {
                    //loginView!!.showMessage(activity, "Password must be 6 digits")
                    password.error = "Password must be 6 digits"
                    return false
                }
        return true;
    }
}