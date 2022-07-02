package com.advocatediary.activity.phoneVerification.phoneVerificationPresenter

import android.app.Activity
import android.content.Intent
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.activity.phoneVerification.phoneVerifyView.PhoneVerifyView
import com.advocatediary.handler.RegisterHandler
import com.advocatediary.model.register.RegisterExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices
import com.google.gson.JsonObject
import org.json.JSONObject

class PhoneVerificationPresenter(var activity: Activity, var phoneView: PhoneVerifyView) {

    fun phoneVerifyMethod(phoneNumber: String, reg_data: String) {

        var jsonObject = JSONObject(reg_data)

        var user_name = jsonObject.getString("user_name")
        var password = jsonObject.getString("password")
        var email = jsonObject.getString("email")
        var state = jsonObject.getString("state")
        var district = jsonObject.getString("district")


        WebServices.Factory1.getInstance()!!.registerMethod(
            user_name, "", user_name, phoneNumber, email, password, "", "", "1", "",
            state, district, object : RegisterHandler {
                override fun onSuccess(registerExample: RegisterExample) {
                    phoneView.hideDialog()
                    if (registerExample != null) {
                        if (registerExample.getResponse().getStatus().equals("1")) {

                            CsPreferences.putString(
                                activity,
                                Constants.USER_ID,
                                registerExample.getResponse().getData()!!.getId().toString()
                            )
                            CsPreferences.putString(
                                activity,
                                Constants.USER_NAME,
                                registerExample.getResponse().getData()!!.getFullName().toString()
                            )
                            CsPreferences.putString(
                                activity,
                                Constants.USER_PHONE,
                                registerExample.getResponse().getData()!!.getPhone().toString()
                            )

                            CsPreferences.Factory.putString(
                                activity,
                                Constants.USER_STATE,
                                registerExample.getResponse().getData()!!.getStateId()!!
                            )
                            CsPreferences.Factory.putString(
                                activity,
                                Constants.USER_DISTRICT,
                                registerExample.getResponse().getData()!!.getDistrictId()!!
                            )


                            var intent = Intent(activity, HomeActivity::class.java)
                            activity.startActivity(intent)
                            activity.finishAffinity()
                        } else {

                        }
                    }
                }

                override fun onError(message: String) {
                    phoneView.hideDialog()
                }

            }
        )

    }
}


