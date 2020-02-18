package com.pv.demoapp.ui.register

import com.pv.demoapp.base.BaseViewModel
import com.pv.demoapp.data.DataManager
import com.utils.SchedulerProvider

class RegisterViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) : BaseViewModel<RegisterNavigator>(dataManager, schedulerProvider) {

    fun next() = getNavigator().next()
}