package com.sol.kreditbee.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sol.kreditbee.ui.viewmodel.AlbumDetailsViewModel
import com.sol.kreditbee.ui.viewmodel.MainViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel::class)
    abstract fun bindAlbumDetailsViewModel(viewModel: AlbumDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
