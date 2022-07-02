package com.advocatediary.handler

import com.advocatediary.model.allClients.AllClientExample

interface AllClientHandler {
    fun onSuccess(example:AllClientExample)
    fun onError(message:String)
}