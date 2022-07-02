package com.advocatediary.model.particularClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ParticularClienData {
    @SerializedName("current_page")
    @Expose
    private lateinit var currentPage: String
    @SerializedName("data")
    @Expose
    private var data: ArrayList<ParticularClientDatum>? = null
    @SerializedName("from")
    @Expose
    private lateinit var from: String
    @SerializedName("last_page")
    @Expose
    private lateinit var lastPage: String
    @SerializedName("next_page_url")
    @Expose
    private lateinit var nextPageUrl: String
    @SerializedName("path")
    @Expose
    private lateinit var path: String
    @SerializedName("per_page")
    @Expose
    private lateinit var perPage: String
    @SerializedName("prev_page_url")
    @Expose
    private lateinit var prevPageUrl: String
    @SerializedName("to")
    @Expose
    private lateinit var to: String
    @SerializedName("total")
    @Expose
    private lateinit var total: String

    fun getCurrentPage(): String? {
        return currentPage;
    }

    fun setCurrentPage(currentPage: String) {
        this.currentPage = currentPage;
    }

    fun getData(): ArrayList<ParticularClientDatum>? {
        return data;
    }

    fun setData(data: ArrayList<ParticularClientDatum>) {
        this.data = data;
    }

    fun getFrom(): String? {
        return from;
    }

    fun setFrom(from: String) {
        this.from = from;
    }

    fun getLastPage(): String? {
        return lastPage;
    }

    fun setLastPage(lastPage: String) {
        this.lastPage = lastPage;
    }

    fun getNextPageUrl(): String? {
        return nextPageUrl;
    }

    fun setNextPageUrl(nextPageUrl: String) {
        this.nextPageUrl = nextPageUrl;
    }

    fun getPath(): String? {
        return path;
    }

    fun setPath(path: String) {
        this.path = path;
    }

    fun getPerPage(): String? {
        return perPage;
    }

    fun setPerPage(perPage: String) {
        this.perPage = perPage;
    }

    fun getPrevPageUrl(): String? {
        return prevPageUrl;
    }

    fun setPrevPageUrl(prevPageUrl: String) {
        this.prevPageUrl = prevPageUrl;
    }

    fun getTo(): String? {
        return to;
    }

    fun setTo(to: String) {
        this.to = to;
    }

    fun getTotal(): String? {
        return total;
    }

    fun setTotal(total: String) {
        this.total = total;
    }

}