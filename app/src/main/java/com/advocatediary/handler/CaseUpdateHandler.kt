package com.advocatediary.handler

import com.advocatediary.model.caseUpdate.CaseUpdateExample

interface CaseUpdateHandler {
    fun onSuccess(caseUpdateExample: CaseUpdateExample)
    fun onError(message:String)
}