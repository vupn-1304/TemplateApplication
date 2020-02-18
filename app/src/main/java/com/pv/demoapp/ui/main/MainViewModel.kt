package com.pv.demoapp.ui.main

import com.pv.demoapp.base.BaseViewModel
import com.pv.demoapp.data.DataManager
import com.utils.SchedulerProvider

class MainViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {
}