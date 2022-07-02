package com.advocatediary.handler

import com.advocatediary.model.home.HomeExample

interface HomeHandler {
    fun onSuccess(homeExample:HomeExample)
    fun onError(message:String)
}