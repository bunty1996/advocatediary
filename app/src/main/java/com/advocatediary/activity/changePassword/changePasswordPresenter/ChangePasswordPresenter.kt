package com.advocatediary.activity.changePassword.changePasswordPresenter

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import com.advocatediary.activity.changePassword.changeView.ChangeView
import com.advocatediary.activity.login.LoginActivity
import com.advocatediary.handler.ChangePasswordHandler
import com.advocatediary.model.changePassword.ChangePasswordExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices

class ChangePasswordPresenter(var activity: Activity, var view: ChangeView) {

    private lateinit var etOldPassword: EditText

    private lateinit var etNewPassword: EditText

    private lateinit var etConfirmPassword: EditText

    fun changePasswordCall(etOldPassword: EditText, etNewPassword: EditText, etConfirmPassword: EditText) {
        this.etOldPassword = etOldPassword;
        this.etNewPassword = etNewPassword;
        this.etConfirmPassword = etConfirmPassword;
        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)
            view.showDialog(activity)
            var userId = CsPreferences.readString(activity, Constants.USER_ID)
            WebServices.Factory1.getInstance()!!.changePasswordMethod(userId, etOldPassword.text.toString().trim(),
                etNewPassword.text.toString().trim(), object : ChangePasswordHandler {
                    override fun onSuccess(example: ChangePasswordExample) {
                        view.hideDialog()
                        if (example != null) {
                            if (example.getResponse()?.getStatus().equals("1")) {
                                CsPreferences.clearPreferences(activity)
                                var intent = Intent(activity, LoginActivity::class.java)
                                activity.startActivity(intent)
                                activity.finishAffinity()
                            }
                            view.showMessage(activity, example.getResponse()!!.getMessage()!!)
                        }
                    }

                    override fun onError(message: String) {
                        view.hideDialog()
                    }
                })
        }
    }


    fun checkValidation(): Boolean {
        if (etOldPassword.text.toString().trim().length == 0) {
            etOldPassword.setError("Please enter old password")
            return false
        } else
            if (etNewPassword.text.toString().trim().length == 0) {
                etNewPassword.setError("Please enter new password")
                return false
            } else
                if (etConfirmPassword.text.toString().trim().length == 0) {
                    etConfirmPassword.setError("Please enter confirm password")
                    return false
                } else
                    if (!(etNewPassword.text.toString().trim().contentEquals(etConfirmPassword.text.toString().trim()))) {
                        etConfirmPassword.setError("New and Confirm password must be same")
                        return false
                    }
        return true
    }
}