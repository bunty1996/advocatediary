package com.advocatediary.activity.register.registerPresenter

import android.app.Activity
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.advocatediary.activity.register.registerView.RegisterView
import com.advocatediary.handler.StateHandler
import com.advocatediary.handler.UserExistsHandler
import com.advocatediary.model.state.StateExample
import com.advocatediary.model.userExists.UserExistsExample
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices
import java.util.regex.Pattern

class RegisterPresenter(val activity: Activity, val registerView: RegisterView) {

    private lateinit var tv_user_name: EditText

    private lateinit var tv_phone: EditText

    private lateinit var et_passwordFirst: EditText

    private lateinit var et_emailFirst: EditText

    private lateinit var tv_state: TextView

    private lateinit var tv_district: TextView

    fun hitRegister(
        tv_user_name: EditText,
        tv_phone: EditText,
        et_passwordFirst: EditText,
        et_emailFirst: EditText,
        tv_state: TextView,
        tv_district: TextView
    ) {
        this.tv_user_name = tv_user_name
        this.tv_phone = tv_phone
        this.et_passwordFirst = et_passwordFirst
        this.et_emailFirst = et_emailFirst
        this.tv_state = tv_state
        this.tv_district = tv_district

        if (checkValidation()) {
            Toast.makeText(activity, "Register Successfully", Toast.LENGTH_LONG).show()
        }
    }


    fun checkValidation(): Boolean {
        if (tv_user_name.text.toString().trim().length == 0) {
            tv_user_name.error = "Please enter fullname"
            return false
        } else
            if (tv_phone.text.toString().trim().length == 0) {
                tv_phone.error = "Please enter phone number"
                return false
            } else if (et_passwordFirst.text.toString().trim().length == 0) {
                et_passwordFirst.error = "Please enter password"
                return false
            } else if (et_passwordFirst.text.toString().trim().length < 6) {
                et_passwordFirst.error = "Password must be 6 digits"
                return false
            }/* else if (et_emailFirst.text.toString().trim().length == 0) {
                et_emailFirst.error = "Please enter email"
                return false
            }*/ else if (tv_state.text.toString().trim().length == 0) {
                tv_state.error = "Please select state"
                return false
            } else if (tv_district.text.toString().trim().length == 0) {
                tv_district.error = "Please select district"
                return false
            } else
                if (!(checkbox.isChecked)) {
                    registerView.showMessage(activity, "Please select terms & conditions.")
                    return false
                }
        return true
    }

    fun getStateList() {
        registerView.showDialog(activity)
        WebServices.Factory1.getInstance()!!.getStateListMethod(object : StateHandler {
            override fun onSuccess(stateExample: StateExample?) {
                registerView.hideDialog()
                if (stateExample != null) {
                    registerView.setStateList(stateExample.getStateResponse()!!.getData())

                }
            }

            override fun onError(message: String) {
                registerView.hideDialog()
            }

        })
    }

    private lateinit var checkbox: CheckBox

    fun checkUserExistsMethod(
        phone: String, tv_user_name: EditText,
        tv_phone: EditText,
        et_passwordFirst: EditText,
        et_emailFirst: EditText,
        tv_state: TextView,
        tv_district: TextView, checkbox: CheckBox
    ) {

        this.tv_user_name = tv_user_name
        this.tv_phone = tv_phone
        this.et_passwordFirst = et_passwordFirst
        this.et_emailFirst = et_emailFirst
        this.tv_state = tv_state
        this.tv_district = tv_district
        this.checkbox = checkbox

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)
            registerView.showDialog(activity)
            WebServices.Factory1.getInstance()?.checkUserExists(phone, object : UserExistsHandler {
                override fun onSuccess(example: UserExistsExample) {
                    registerView.hideDialog()
                    if (example != null) {
                        if (example.getResponse().getStatus().equals("1")) {
                            registerView.callForPhone(phone)
                            //registerView.showMessage(activity, example.getResponse().getMessage()!!)
                        } else {
                            registerView.showMessage(activity, example.getResponse().getMessage()!!)
                        }
                    }
                }

                override fun onError(messsage: String) {
                    registerView.hideDialog()
                }
            })
        }
    }

    fun String.isEmailValid() =
        Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        ).matcher(this).matches()
}