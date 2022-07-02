package com.advocatediary.fragment.clientDetail.clientDetailView

import android.app.Activity
import com.advocatediary.model.clientDeatail.ClientDetailData

interface ClientDetailView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
    fun setClientData(data: ClientDetailData)
}