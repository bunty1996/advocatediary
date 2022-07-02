package com.advocatediary.handler

import com.advocatediary.model.particularClient.ParticularClientExample

interface ParticularClientHandler {
    fun onSuccess(example: ParticularClientExample)
    fun onError(message: String)
}