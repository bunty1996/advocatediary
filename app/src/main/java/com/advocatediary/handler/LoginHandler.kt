package com.advocatediary.handler

import com.advocatediary.model.login.LoginExample

interface LoginHandler {
    fun onSuccess(loginExample:LoginExample)
    fun onError(message:String)
}