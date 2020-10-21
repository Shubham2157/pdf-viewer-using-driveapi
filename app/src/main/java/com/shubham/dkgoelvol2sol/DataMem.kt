package com.shubham.dkgoelvol2sol

class DataMem {

    private var title: String? = null
    private var link: String? = null

    fun NotificationData(title: String?, link: String?) {
        this.title = title
        this.link = link
    }

    fun getTitle(): String? {
        return title
    }

    fun getLink(): String? {
        return link
    }
}