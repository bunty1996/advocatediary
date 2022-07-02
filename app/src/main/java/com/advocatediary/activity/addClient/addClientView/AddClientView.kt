package com.advocatediary.activity.addClient.addClientView

import android.app.Activity
import com.advocatediary.model.clientDeatail.ClientDetailData

interface AddClientView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
    fun setClientData(data: ClientDetailData?)
}