package com.advocatediary.handler

import com.advocatediary.model.deleteClients.DeleteClientsExample

interface DeleteClientHandler {
    fun onSuccess(example:DeleteClientsExample)
    fun onError(message:String)
}