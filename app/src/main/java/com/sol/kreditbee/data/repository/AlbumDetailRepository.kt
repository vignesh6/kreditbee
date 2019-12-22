package com.sol.kreditbee.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sol.kreditbee.data.Result
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetailDao
import com.sol.kreditbee.data.resultLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(
    private val dao: AlbumDetailDao,
    private val albumDetailRemoteDataSource: AlbumDetailRemoteDataSource
) {
    fun observePagedAlbums(themeId: Int? = null) =
        observeLocalPagedAbums(themeId)

    private fun observeLocalPagedAbums(albumId: Int? = null): LiveData<PagedList<AlbumDetail>> {
        val dataSourceFactory = dao.getAlbumDetails(albumId!!)
        return LivePagedListBuilder(dataSourceFactory,
            AlbumDetailPageDataSourceFactory.pagedListConfig()).build()
    }

    fun observeAlbumsById(albumId: Int?) =
        resultLiveData(
            databaseQuery = { dao.getAlbumsById(albumId) },
            networkCall = { albumDetailRemoteDataSource.fetchAlbumDetails(albumId!!) },
            saveCallResult = { dao.insertAll(it) })

    private fun observeRemotePagedAlbums(themeId: Int? = null, ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<AlbumDetail>> {
        val dataSourceFactory = AlbumDetailPageDataSourceFactory(themeId!!, albumDetailRemoteDataSource,
            dao, ioCoroutineScope)
        return LivePagedListBuilder(dataSourceFactory,
            AlbumDetailPageDataSourceFactory.pagedListConfig()).build()
    }
}