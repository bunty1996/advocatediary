package com.advocatediary.activity.common.commonPresenter

import android.app.Activity
import android.content.Intent
import com.advocatediary.activity.common.commonView.CommonView
import com.advocatediary.handler.DeleteCaseHandler
import com.advocatediary.handler.DeleteClientHandler
import com.advocatediary.model.deleteCase.DeleteCaseExample
import com.advocatediary.model.deleteClients.DeleteClientsExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices

class CommonPresenter(var activity: Activity, var commonView: CommonView) {

    fun deleteClient(client_id: String) {

        commonView.showDialog(activity)
        var user_id = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.deleteClientMethod(client_id, user_id, object : DeleteClientHandler {
            override fun onSuccess(example: DeleteClientsExample) {
                commonView.hideDialog()
                if (example != null) {
                    if (example.getResponse().getStatus().equals("1")) {

                        var intent = Intent()
                        intent.action = "client_update"
                        intent.putExtra("search_value", "")
                        activity.sendBroadcast(intent)

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

                       /* var intent6 = Intent()
                        intent6.action = Constants.CLIENT_DETAIL_UPDATE
                        activity.sendBroadcast(intent6)*/


                        activity.finish()
                    }
                    commonView.showMessage(activity, example.getResponse().getMessage()!!)
                }
            }

            override fun onError(message: String) {
                commonView.hideDialog()
            }

        })
    }

    // For Delete Case
    fun deleteCase(caseId: String) {
        commonView.showDialog(activity)
        var user_id = CsPreferences.readString(activity, Constants.USER_ID)

        WebServices.Factory1.getInstance()!!.deleteCaseMethod(caseId, user_id, object : DeleteCaseHandler {
            override fun onSuccess(example: DeleteCaseExample) {
                commonView.hideDialog()
                if (example != null) {
                    commonView.showMessage(activity, example.getResponse()!!.getMessage()!!)
                    if (example.getResponse()!!.getStatus().equals("1")) {

                        /*var intent = Intent()
                        intent.action = "case_update"
                        activity.sendBroadcast(intent)

                        var intent2 = Intent()
                        intent2.action = "home_update"
                        activity.sendBroadcast(intent2)

                        var intent3 = Intent()
                        intent3.action = "my_case_update"
                        activity.sendBroadcast(intent3)*/


                      /*  var intent2 = Intent()
                        intent2.action = Constants.CASE_DETAIL_UPDATE
                        activity.sendBroadcast(intent2)*/

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

                    }
                }
            }

            override fun onError(message: String) {
                commonView.hideDialog()
            }
        })
    }
}