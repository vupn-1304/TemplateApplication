package com.pv.demoapp.utils.widget

import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import com.pv.demoapp.data.DataManager
import org.koin.core.KoinComponent
import org.koin.core.inject

object AppUtils : KoinComponent {

    private val dataManager by inject<DataManager>()

    fun isConnectedInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnected
        } else {
            false
        }
    }

    fun isMyServiceRunning(serviceClass: Class<*>, context: Context): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}