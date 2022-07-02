package com.advocatediary.handler

import com.advocatediary.model.stateDistricts.StateDistrictExample

interface StateDistrictHandler {
    fun onSuccess(stateDistrictExample: StateDistrictExample)
    fun onError(message:String)
}