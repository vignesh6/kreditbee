package com.sol.kreditbee.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetailDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class AlbumDetailPageDataSourceFactory @Inject constructor(
    private val albumId: Int,
    private val albumDetailRemoteDataSource: AlbumDetailRemoteDataSource,
    private val dao: AlbumDetailDao,
    private val coroutineScope: CoroutineScope
) :
    DataSource.Factory<Int, AlbumDetail>() {
    private val liveData = MutableLiveData<AlbumDetailPageDataSource>()
    override fun create(): DataSource<Int, AlbumDetail> {
        val source = AlbumDetailPageDataSource(albumId,albumDetailRemoteDataSource,dao,coroutineScope)
        liveData.postValue(source)
        return source
    }
    companion object {
        private const val PAGE_SIZE = 50

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}