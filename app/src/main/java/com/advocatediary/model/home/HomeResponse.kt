package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeResponse
{
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("ios_version")
    @Expose
    private var iosVersion: String? = null
    @SerializedName("android_version")
    @Expose
    private var androidVersion: String? = null
    @SerializedName("today")
    @Expose
    private var today: String? = null
    @SerializedName("next_day")
    @Expose
    private var nextDay: String? = null
    @SerializedName("data")
    @Expose
    private var data: HomeData? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getIosVersion(): String? {
        return iosVersion
    }

    fun setIosVersion(iosVersion: String) {
        this.iosVersion = iosVersion
    }

    fun getAndroidVersion(): String? {
        return androidVersion
    }

    fun setAndroidVersion(androidVersion: String) {
        this.androidVersion = androidVersion
    }

    fun getToday(): String? {
        return today
    }

    fun setToday(today: String) {
        this.today = today
    }

    fun getNextDay(): String? {
        return nextDay
    }

    fun setNextDay(nextDay: String) {
        this.nextDay = nextDay
    }

    fun getData(): HomeData? {
        return data
    }

    fun setData(data: HomeData) {
        this.data = data
    }
}