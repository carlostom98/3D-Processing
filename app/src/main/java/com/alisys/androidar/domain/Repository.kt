package com.alisys.androidar.domain

import com.alisys.androidar.data.RequestAPIRetrofit
import com.alisys.androidar.data.RestAPI

class Repository(private val requestAPI: RestAPI): IRepository {
    override fun printAPI() {
        requestAPI.requestAPI()
    }
}