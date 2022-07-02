package com.advocatediary.activity.forgotPassword.forgotPresenter

import android.app.Activity
import android.widget.EditText
import android.widget.TextView
import com.advocatediary.activity.forgotPassword.forgotView.ForgotView
import com.advocatediary.handler.UserExistsHandler
import com.advocatediary.model.userExists.UserExistsExample
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices

class ForgotPresenter(var activity: Activity, var viewForgot: ForgotView) {
    private lateinit var tv_phone: EditText

    private lateinit var tv_code: TextView

    fun hitForgotPassword(tv_code: TextView, tv_phone: EditText) {
        this.tv_phone = tv_phone
        this.tv_code = tv_code
        if (checkValidation()) {
            checkUserExistsMethod(tv_code.text.toString().trim() + "" + tv_phone.text.toString().trim())
        }
    }

    fun checkValidation(): Boolean {
        if (tv_phone.text.toString().trim().length == 0) {
            tv_phone.error = "Please enter phone number"
            return false
        }
        return true
    }

    fun checkUserExistsMethod(phone: String) {

        this.tv_phone = tv_phone

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)
            viewForgot.showDialog(activity)
            WebServices.Factory1.getInstance()?.checkUserExists(phone, object : UserExistsHandler {
                override fun onSuccess(example: UserExistsExample) {

                    if (example != null) {
                        if (example.getResponse().getStatus().equals("0")) {
                            //viewForgot.showMessage(activity, example.getResponse().getMessage()!!)
                            viewForgot.callMessageData(phone)

                        } else {
                            viewForgot.hideDialog()
                            viewForgot.showMessage(activity, example.getResponse().getMessage()!!)
                        }
                    }
                }

                override fun onError(messsage: String) {
                    viewForgot.hideDialog()
                }
            })
        }
    }
}