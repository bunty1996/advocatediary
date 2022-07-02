package com.advocatediary.activity.addClient.addClientPresenter

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Toast
import com.advocatediary.activity.addCaseInfo.AddCaseInfoActivity
import com.advocatediary.activity.addClient.addClientView.AddClientView
import com.advocatediary.handler.AddClientHandler
import com.advocatediary.handler.ClientDetailHandler
import com.advocatediary.handler.EditClientHandler
import com.advocatediary.model.addClient.AddClientExample
import com.advocatediary.model.clientDeatail.ClientDetailExample
import com.advocatediary.model.editClient.EditClientExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices

class AddClientPresenter(var activity: Activity, var addClientView: AddClientView,var scrollview_add_client:ScrollView) {

    private lateinit var et_name_client: EditText

    private lateinit var et_phone_client: EditText

    private lateinit var et_email_client: EditText

    private lateinit var et_address_client: EditText

    fun addClientMethod2(
        et_name_client: EditText,
        et_phone_client: EditText,
        et_email_client: EditText,
        et_address_client: EditText, gender: String
    ) {
        this.et_name_client = et_name_client
        this.et_phone_client = et_phone_client
        this.et_email_client = et_email_client
        this.et_address_client = et_address_client

        if (cheechValidation()) {
            Utils.hideSoftKeyboard(activity)
            addClientView.showDialog(activity)
            var userId = CsPreferences.readString(activity, Constants.USER_ID)
            WebServices.Factory1.getInstance()!!.addClientMethod(userId, et_name_client.text.toString().trim(),
                et_phone_client.text.toString().trim(),
                et_email_client.text.toString().trim(),
                et_address_client.text.toString().trim(), gender, object : AddClientHandler {
                    override fun onSuccess(example: AddClientExample) {
                        addClientView.hideDialog()
                        if (example != null) {
                            if (example.getResponse()!!.getStatus().equals("1")) {

                                var intent = Intent(activity, AddCaseInfoActivity::class.java)
                                intent.putExtra("name", example.getResponse()!!.getData()!!.getFullName()!!.toString())
                                intent.putExtra("email", example.getResponse()!!.getData()!!.getEmail().toString())
                                intent.putExtra("client_id", example.getResponse()!!.getData()!!.getId().toString())
                                intent.putExtra("case_id", "")

                                activity.startActivity(intent)


                                var intent1 = Intent()
                                intent1.action = "case_update"
                                activity.sendBroadcast(intent1)


                                var intent2 = Intent()
                                intent2.action = Constants.CASE_DETAIL_UPDATE
                                activity.sendBroadcast(intent2)

                                var intent3 = Intent()
                                intent3.action = Constants.HOME_UPDATE
                                activity.sendBroadcast(intent3)

                                var intent4 = Intent()
                                intent4.action = Constants.MY_CASE_UPDATE
                                activity.sendBroadcast(intent4)

                                var intent5 = Intent()
                                intent5.action = Constants.MY_CLIENT_UPDATE
                                activity.sendBroadcast(intent5)

                                var intent6 = Intent()
                                intent6.action = Constants.CLIENT_DETAIL_UPDATE
                                activity.sendBroadcast(intent6)


                                activity.finish()
                                Utils.screenOpenCloseAnimation(activity)
                            }
                            addClientView.showMessage(activity, example.getResponse()!!.getMessage().toString())
                        }
                    }

                    override fun onError(message: String) {
                        addClientView.hideDialog()
                    }
                }
            )
        }
    }

    fun cheechValidation(): Boolean {
        if (et_name_client.text.toString().trim().length == 0) {
            et_name_client.setError("Please enter fullname")
            return false
        } else
            if (et_phone_client.text.toString().trim().length == 0) {
                et_phone_client.setError("Please enter phone")
                return false
            }
        return true
    }


    fun getClientDetail(client_id: String) {
        addClientView.showDialog(activity)
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getClientDetailsMethod(userId, client_id, object : ClientDetailHandler {
            override fun onSuccess(example: ClientDetailExample) {
                addClientView.hideDialog()
                if (example != null) {
                    addClientView.setClientData(example.getResponse()!!.getData())
                }
            }

            override fun onError(message: String) {
                addClientView.hideDialog()
            }
        })
    }

    // for update client
    fun editClient(
        et_name_client: EditText,
        et_phone_client: EditText,
        et_email_client: EditText,
        et_address_client: EditText, gender: String, clientId: String
    ) {

        this.et_name_client = et_name_client
        this.et_phone_client = et_phone_client
        this.et_email_client = et_email_client
        this.et_address_client = et_address_client

        if (cheechValidation()) {
            Utils.hideSoftKeyboard(activity)
            addClientView.showDialog(activity)
            Utils.hideSoftKeyboard(activity)
            var userId = CsPreferences.readString(activity, Constants.USER_ID)
            WebServices.Factory1.getInstance()!!.editClientMEthod(clientId,
                userId,
                et_name_client.text.toString(),
                et_phone_client.text.toString(),
                et_email_client.text.toString(),
                et_address_client.text.toString(),
                gender,
                object : EditClientHandler {
                    override fun onSuccess(example: EditClientExample) {
                        addClientView.hideDialog()
                        if (example != null) {

                            var intent1 = Intent()
                            intent1.action = "case_update"
                            activity.sendBroadcast(intent1)

                            var intent2 = Intent()
                            intent2.action = Constants.CASE_DETAIL_UPDATE
                            activity.sendBroadcast(intent2)

                            var intent3 = Intent()
                            intent3.action = Constants.HOME_UPDATE
                            activity.sendBroadcast(intent3)

                            var intent4 = Intent()
                            intent4.action = Constants.MY_CASE_UPDATE
                            activity.sendBroadcast(intent4)

                            var intent5 = Intent()
                            intent5.action = Constants.MY_CLIENT_UPDATE
                            activity.sendBroadcast(intent5)

                            var intent6 = Intent()
                            intent6.action = Constants.CLIENT_DETAIL_UPDATE
                            activity.sendBroadcast(intent6)

                            activity.finish()
                            Utils.screenOpenCloseAnimation(activity)
                        }
                    }

                    override fun onError(message: String) {
                        addClientView.hideDialog()
                    }

                })

        }
    }
}