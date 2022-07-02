package com.advocatediary.handler

import com.advocatediary.model.deleteCase.DeleteCaseExample

interface DeleteCaseHandler {
    fun onSuccess(example:DeleteCaseExample)
    fun onError(message:String)
}