package com.advocatediary.handler

import com.advocatediary.model.casePurposes.CasePurposesExample

interface CasePurposesHandler {
    fun onSuccess(example:CasePurposesExample)
    fun onError(message:String)
}