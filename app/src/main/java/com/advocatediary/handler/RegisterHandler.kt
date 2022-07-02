package com.advocatediary.handler

import com.advocatediary.model.register.RegisterExample

interface RegisterHandler {
    fun onSuccess(registerExample: RegisterExample)
    fun onError(message: String)
}