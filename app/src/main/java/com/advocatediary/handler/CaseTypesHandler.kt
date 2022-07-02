package com.advocatediary.handler

import com.advocatediary.model.caseType.CaseTypeExample

interface CaseTypesHandler {
    fun onSuccess(example:CaseTypeExample)
    fun onError(message:String)
}