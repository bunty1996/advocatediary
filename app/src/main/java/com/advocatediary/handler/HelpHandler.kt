package com.advocatediary.handler

import com.advocatediary.model.help.HelpExample

interface HelpHandler {
    fun onSuccess(example:HelpExample)
    fun onError(message:String)
}