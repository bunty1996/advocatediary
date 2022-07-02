package com.advocatediary.handler

import com.advocatediary.model.resetPassword.ResetPasswordExample

interface ResetPasswordHandler {
    fun onSuccess(example:ResetPasswordExample)
    fun onError(message:String)
}