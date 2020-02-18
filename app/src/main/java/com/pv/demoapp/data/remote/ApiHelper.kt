package com.pv.demoapp.data.remote

import com.google.gson.JsonObject
import io.reactivex.Single

interface ApiHelper {

    fun getCity(): Single<JsonObject>

    fun getDistrict(): Single<JsonObject>
}