package com.advocatediary.handler

import com.advocatediary.model.myClient.MyClientExample

interface MyClientHandler {
    fun onSuccess(myClientExample: MyClientExample)
    fun onError(message:String)
}