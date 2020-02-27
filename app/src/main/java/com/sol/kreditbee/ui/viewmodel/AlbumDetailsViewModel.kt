package com.sol.kreditbee.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sol.kreditbee.api.ApiInterface
import com.sol.kreditbee.data.Result
import com.sol.kreditbee.data.repository.AlbumDetailRepository
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import com.sol.kreditbee.data.resultLiveData
import com.sol.kreditbee.di.CoroutineScropeIO
import kotlinx.coroutines.*
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(private val apiInterface: ApiInterface,
    private val repository: AlbumDetailRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {
    var connectivityAvailable: Boolean = false
    var albumId: Int? = null
    val getAlbumsDetailsById by lazy {
       repository.observeAlbumsById(albumId)

    }
    val albumDetails by lazy {
        repository.observePagedAlbums(albumId)
    }

    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}