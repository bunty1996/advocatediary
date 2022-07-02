package com.advocatediary.handler

import com.advocatediary.model.editClient.EditClientExample

interface EditClientHandler {
    fun onSuccess(example:EditClientExample)
    fun onError(message:String)
}