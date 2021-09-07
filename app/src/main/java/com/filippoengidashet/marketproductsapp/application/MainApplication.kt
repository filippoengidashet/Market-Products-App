package com.filippoengidashet.marketproductsapp.application

import android.app.Application
import com.filippoengidashet.marketproductsapp.mvvm.model.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * @author Filippo 17/08/2021
 */
@HiltAndroidApp
class MainApplication : Application() {

    @Inject lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {

        lateinit var instance: MainApplication
            private set
    }
}
