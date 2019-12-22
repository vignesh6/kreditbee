package com.sol.kreditbee.data.repository

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetailDao
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.sol.kreditbee.data.Result
import javax.inject.Inject

class AlbumDetailPageDataSource @Inject constructor(
    private val albumId: Int,
    private val dataSource: AlbumDetailRemoteDataSource,
    private val dao: AlbumDetailDao,
    private val coroutineScope: CoroutineScope
) :ItemKeyedDataSource<Int,AlbumDetail>(){
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<AlbumDetail>
    ) {
        fetchData(0) {
            callback.onResult(it)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<AlbumDetail>) {
        fetchData( params.key) {
            callback.onResult(it)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<AlbumDetail>) {

    }
    private fun fetchData(key: Int, callback: (List<AlbumDetail>) -> Unit) {
        coroutineScope.launch(getJobErrorHandler()) {
            val response = if(key==0)dao.getAlbumsByIdInitial(albumId,50)else dao.getAlbumsByIdAfter(albumId,
                key,50)
            if (response.isNotEmpty()) {
                callback(response)
            } else  {
                postError("No Data Found")
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Log.e("An error happened:", "$message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }

    override fun getKey(item: AlbumDetail): Int {
       return item.id
    }
}