package com.advocatediary.handler

import com.advocatediary.model.getJudges.GetJudgeExample

interface GetJudgesHandler {
    fun onSuccess(handler:GetJudgeExample)
    fun onError(message:String)
}