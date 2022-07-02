package com.advocatediary.handler

import com.advocatediary.model.userExists.UserExistsExample

interface UserExistsHandler {

    fun onSuccess(eample:UserExistsExample)
    fun onError(messsage:String)
}