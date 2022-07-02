package com.advocatediary.handler

import com.advocatediary.model.myCases.MyCasesExample

interface MyCasesHandler {
    fun onSuccess(myCasesExample: MyCasesExample)
    fun onError(message:String)
}