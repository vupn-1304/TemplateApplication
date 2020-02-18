package com.pv.demoapp.ui.city

import com.base.BaseNavigator
import com.pv.demoapp.data.model.City

interface CityNavigator : BaseNavigator {

    fun getListCitySuccess(list: List<City>)

    fun getListCityFailed(msg: String)
}