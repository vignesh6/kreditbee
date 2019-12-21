package com.sol.kreditbee

import android.app.Activity
import android.app.Application
import com.sol.kreditbee.di.AppComponent
import com.sol.kreditbee.di.AppInjector
import com.sol.kreditbee.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}