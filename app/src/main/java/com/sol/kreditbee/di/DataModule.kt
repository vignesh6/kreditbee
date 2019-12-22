package com.sol.kreditbee.di

import android.app.Application
import com.sol.kreditbee.data.repository.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideAlbumDao(db: AppDatabase) = db.albumDao()

    @Singleton
    @Provides
    fun provideAlbumDetailDao(db: AppDatabase) = db.albumDetailDao()
}