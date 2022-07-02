package com.advocatediary.activity.help.helpPresenter

import android.app.Activity
import android.widget.EditText
import com.advocatediary.activity.help.helpView.HelpView
import com.advocatediary.handler.HelpHandler
import com.advocatediary.model.help.HelpExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices

class HelpPresenter(var activity: Activity, var helpView: HelpView) {

    private lateinit var et_query: EditText

    fun hitHelp(et_query: EditText) {
        this.et_query = et_query
        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)
            helpView.showDialog(activity)
            var user_id = CsPreferences.readString(activity, Constants.USER_ID)
            WebServices.Factory1.getInstance()!!.helpMethod(
                user_id,
                et_query.text.toString().trim(),
                object : HelpHandler {
                    override fun onSuccess(example: HelpExample) {
                        helpView.hideDialog()
                        if (example != null) {
                            helpView.showMessage(activity, example.getResponse()!!.getMessage()!!)
                            activity.finish()
                            Utils.screenOpenCloseAnimation(activity)
                        }
                    }

                    override fun onError(message: String) {
                        helpView.hideDialog()
                    }
                })
        }
    }

    fun checkValidation(): Boolean {
        if (et_query.text.toString().trim().length == 0) {
            helpView.showMessage(activity, "Please enter query")
            return false
        }
        return true
    }
}