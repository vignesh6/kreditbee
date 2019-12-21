package com.sol.kreditbee.data.repository

import com.sol.kreditbee.data.repository.local.AlbumDao
import com.sol.kreditbee.data.resultLiveData
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val albumDao: AlbumDao, private  val albumDataSource: AlbumRemoteDataSource) {
    fun observeAlbums() = resultLiveData(
        databaseQuery = { albumDao.getAlbums() },
        networkCall = { albumDataSource.fetchAlbum() },
        saveCallResult = { albumDao.insertAll(it) })

}