package com.dicoding.picodiploma.sejiwaproject.commons.api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}