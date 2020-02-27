package com.sol.kreditbee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sol.kreditbee.api.ApiInterface
import com.sol.kreditbee.data.repository.AlbumRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val apiInterface: ApiInterface,private val albumRepository: AlbumRepository) :ViewModel() {

    val getAlbums by lazy { albumRepository.observeAlbums() }
}