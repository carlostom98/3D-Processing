package com.androidar.example.domain

import com.androidar.example.data.RestAPI

class Repository(private val requestAPI: RestAPI): IRepository {
    override fun printAPI() {
        requestAPI.requestAPI()
    }
}