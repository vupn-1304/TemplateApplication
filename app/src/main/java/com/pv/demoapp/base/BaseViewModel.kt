package com.pv.demoapp.base

import com.base.ViewModelB
import com.pv.demoapp.data.DataManager
import com.utils.SchedulerProvider

open class BaseViewModel<N>(
        var dataManager: DataManager,
        var schedulerProvider: SchedulerProvider
) : ViewModelB<N>(schedulerProvider) {
}