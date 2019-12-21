package com.sol.kreditbee.di

import com.sol.kreditbee.ui.AlbumDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule{
@ContributesAndroidInjector
abstract fun contributeAlbumDetailsFragment(): AlbumDetailsFragment
}