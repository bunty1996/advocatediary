package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailCaseAdditionalPaymentDetail {@SerializedName("current_page")
@Expose
private lateinit var currentPage:Integer
    @SerializedName("from")
    @Expose
    private lateinit var from:String
    @SerializedName("last_page")
    @Expose
    private lateinit var lastPage:Integer
    @SerializedName("next_page_url")
    @Expose
    private lateinit var nextPageUrl:String
    @SerializedName("path")
    @Expose
    private lateinit var path:String
    @SerializedName("per_page")
    @Expose
    private lateinit var perPage:Integer
    @SerializedName("prev_page_url")
    @Expose
    private lateinit var prevPageUrl:String
    @SerializedName("to")
    @Expose
    private lateinit var to:String
    @SerializedName("total")
    @Expose
    private lateinit var total:Integer

    fun getCurrentPage():Integer? {
        return currentPage;
    }

    fun setCurrentPage(currentPage:Integer) {
        this.currentPage = currentPage;
    }

    fun getFrom():String? {
        return from;
    }

    fun setFrom(from:String) {
        this.from = from;
    }

    fun  getLastPage():Integer? {
        return lastPage;
    }

    fun setLastPage(lastPage:Integer) {
        this.lastPage = lastPage;
    }

    fun getNextPageUrl():String? {
        return nextPageUrl;
    }

    fun setNextPageUrl(nextPageUrl:String) {
        this.nextPageUrl = nextPageUrl;
    }

    fun getPath():String? {
        return path;
    }

    fun setPath(path:String) {
        this.path = path;
    }

    fun  getPerPage():Integer? {
        return perPage;
    }

    fun setPerPage(perPage:Integer) {
        this.perPage = perPage;
    }

    fun getPrevPageUrl():String? {
        return prevPageUrl;
    }

    fun setPrevPageUrl(prevPageUrl:String) {
        this.prevPageUrl = prevPageUrl;
    }

    fun getTo():String? {
        return to;
    }

    fun setTo(to:String) {
        this.to = to;
    }

    fun  getTotal():Integer? {
        return total;
    }

    fun setTotal(total:Integer) {
        this.total = total;
    }

}