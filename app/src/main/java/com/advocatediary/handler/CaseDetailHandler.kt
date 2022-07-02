package com.advocatediary.handler

import com.advocatediary.model.caseDetail.CaseDetailExample

interface CaseDetailHandler {
    fun onSuccess(example: CaseDetailExample)
    fun onError(mesage: String)
}