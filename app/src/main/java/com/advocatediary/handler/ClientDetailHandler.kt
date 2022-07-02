package com.advocatediary.handler

import com.advocatediary.model.clientDeatail.ClientDetailExample

interface ClientDetailHandler {
    fun onSuccess(example:ClientDetailExample)
    fun onError(message:String)
}