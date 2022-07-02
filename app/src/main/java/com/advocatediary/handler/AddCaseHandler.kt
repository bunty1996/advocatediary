package com.advocatediary.handler

import com.advocatediary.model.addCases.AddCaseExample

interface AddCaseHandler {
    fun onSuccess(addCaseExample: AddCaseExample)
    fun onError(message: String)
}