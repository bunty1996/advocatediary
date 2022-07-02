package com.advocatediary.handler

import com.advocatediary.model.changePassword.ChangePasswordExample

interface ChangePasswordHandler {
    fun onSuccess(example:ChangePasswordExample)
    fun onError(message:String)
}