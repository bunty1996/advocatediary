package com.advocatediary.handler

import com.advocatediary.model.state.StateExample

interface StateHandler {
    fun onSuccess(stateExample: StateExample?)
    fun onError(message:String)

}