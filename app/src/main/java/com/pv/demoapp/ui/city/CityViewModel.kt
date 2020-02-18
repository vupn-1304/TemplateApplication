package com.pv.demoapp.ui.city

import com.google.gson.Gson
import com.pv.demoapp.base.BaseViewModel
import com.pv.demoapp.data.DataManager
import com.pv.demoapp.data.model.City
import com.pv.demoapp.data.remote.ApiConstant.CITIES
import com.pv.demoapp.utils.getErrorMsg
import com.utils.SchedulerProvider
import com.utils.ext.toList

class CityViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider,
        private val gson: Gson) : BaseViewModel<CityNavigator>(dataManager, schedulerProvider) {

    fun getListCity() {
        launch {
            getNavigator().showDialog()
            dataManager.getCity()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({
                        getNavigator().hideDialog()
                        val listCity: List<City> = gson.toList(it.getAsJsonArray(CITIES))
                        getNavigator().getListCitySuccess(listCity)
                    }, {
                        getNavigator().hideDialog()
                        getNavigator().getListCityFailed(it.getErrorMsg())
                    })
        }
    }
}