package com.advocatediary.handler

import com.advocatediary.model.addClient.AddClientExample

interface AddClientHandler {
    fun onSuccess(example:AddClientExample)
    fun onError(message:String)
}