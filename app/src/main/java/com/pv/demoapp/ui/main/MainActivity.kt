package com.pv.demoapp.ui.main

import android.os.Bundle
import android.os.Handler
import com.base.BaseActivity
import com.pv.demoapp.BR
import com.pv.demoapp.R
import com.pv.demoapp.databinding.ActivityMainBinding
import com.pv.demoapp.ui.register.RegisterFragment
import com.pv.demoapp.utils.event.EventNextMain
import com.pv.demoapp.utils.register
import com.pv.demoapp.utils.unregister
import org.greenrobot.eventbus.Subscribe
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun getBindingVariable(): Int = BR.mainModel

    override fun getViewModel(): MainViewModel = mainViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun updateUI(savedInstanceState: Bundle?) {
        mainViewModel.setNavigator(this)
        openFragment(R.id.container_main, RegisterFragment::class.java, null, true)
    }

    override fun onStart() {
        super.onStart()
        register(this)
    }

    override fun onStop() {
        super.onStop()
        unregister(this)
    }

    @Subscribe
    fun onEventNextFragment(eventNextMain: EventNextMain) {
        Handler().post {
            openFragment(R.id.container_main, eventNextMain.clazz, eventNextMain.bundle, eventNextMain.isAddToBackStack)
        }
    }

    override fun onBackPressed() {
        val curr = supportFragmentManager.findFragmentById(R.id.container_main)
//        if (curr is WelcomeFragment) {
//            finish()
//            return
//        }
        super.onBackPressed()
    }
}
