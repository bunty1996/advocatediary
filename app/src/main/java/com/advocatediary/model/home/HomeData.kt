package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeData {
    @SerializedName("todayCasesArr")
    @Expose
    private var todayCasesArr: List<HomeTodayCasesArr>? = null
    @SerializedName("pendingCasesArr")
    @Expose
    private var pendingCasesArr: List<HomePendingCasesArr>? = null
    @SerializedName("nextDayCasesArr")
    @Expose
    private var nextDayCasesArr: List<HomeNextDayCasesArr>? = null

    fun getTodayCasesArr(): List<HomeTodayCasesArr>? {
        return todayCasesArr
    }

    fun setTodayCasesArr(todayCasesArr: List<HomeTodayCasesArr>) {
        this.todayCasesArr = todayCasesArr
    }

    fun getPendingCasesArr(): List<HomePendingCasesArr>? {
        return pendingCasesArr
    }

    fun setPendingCasesArr(pendingCasesArr: List<HomePendingCasesArr>) {
        this.pendingCasesArr = pendingCasesArr
    }

    fun getNextDayCasesArr(): List<HomeNextDayCasesArr>? {
        return nextDayCasesArr
    }

    fun setNextDayCasesArr(nextDayCasesArr: List<HomeNextDayCasesArr>) {
        this.nextDayCasesArr = nextDayCasesArr
    }
}