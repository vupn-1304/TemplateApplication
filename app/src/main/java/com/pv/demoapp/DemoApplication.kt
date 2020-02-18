package com.pv.demoapp

import androidx.multidex.MultiDexApplication
import com.pv.demoapp.di.demoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class DemoApplication : MultiDexApplication() {

    companion object {

        lateinit var demoApplication: DemoApplication

        @JvmStatic
        @Synchronized
        fun getInstance(): DemoApplication {
            return demoApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        demoApplication = this

        startKoin {
            androidContext(this@DemoApplication)
            modules(demoModule)
            logger(EmptyLogger())
        }
    }
}