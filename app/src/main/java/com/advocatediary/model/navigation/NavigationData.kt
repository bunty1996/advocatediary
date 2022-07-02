package com.advocatediary.model.navigation

class NavigationData {
    private var name = ""
    private var image: Int = 0
    private var checkStatus = ""

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int) {
        this.image = image
    }

    fun getCheckStatus(): String {
        return checkStatus
    }

    fun setCheckStatus(checkStatus: String) {
        this.checkStatus = checkStatus
    }
}