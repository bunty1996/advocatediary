package com.advocatediary.activity.resetPassword.resetPaswordPresenter

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import com.advocatediary.activity.login.LoginActivity
import com.advocatediary.activity.resetPassword.resetPasswordView.ResetPasswordView
import com.advocatediary.handler.ResetPasswordHandler
import com.advocatediary.model.resetPassword.ResetPasswordExample
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices

class ResetPasswordPresenter(var activity: Activity, var resetView: ResetPasswordView) {

    private lateinit var et_passwordFirst: EditText

    private lateinit var et_confirm_passwordFirst: EditText

    fun resetPasswordMethod(phoneNumber: String, et_passwordFirst: EditText, et_confirm_passwordFirst: EditText) {
        this.et_passwordFirst = et_passwordFirst
        this.et_confirm_passwordFirst = et_confirm_passwordFirst

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)
            resetView.showDialog(activity)
            WebServices.Factory1.getInstance()!!.resetPassword(phoneNumber, et_passwordFirst.text.toString().trim(),
                object : ResetPasswordHandler {
                    override fun onSuccess(example: ResetPasswordExample) {
                        resetView.hideDialog()
                        if (example != null) {
                            resetView.showMessage(activity, example.getResponse()!!.getMessage()!!)
                            if (example.getResponse()!!.getStatus().equals("1")) {
                                var intent = Intent(activity, LoginActivity::class.java)
                                activity.startActivity(intent)
                                activity.finishAffinity()
                                Utils.screenOpenCloseAnimation(activity)
                            }
                        }
                    }

                    override fun onError(message: String) {
                        resetView.hideDialog()
                    }

                })
        }
    }

    fun checkValidation(): Boolean {
        if (et_passwordFirst.text.toString().trim().length == 0) {
            et_passwordFirst.setError("Please enter new password")
            return false
        } else if (et_confirm_passwordFirst.text.toString().trim().length == 0) {
            et_confirm_passwordFirst.setError("Please enter confirm password")
            return false
        } else
            if (!(et_passwordFirst.text.toString().trim().equals(et_confirm_passwordFirst.text.toString().trim()))) {
                et_confirm_passwordFirst.setError("New and Confirm password must be same")
                return false
            }
        return true
    }
}