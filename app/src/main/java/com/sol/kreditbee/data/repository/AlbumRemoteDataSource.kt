package com.sol.kreditbee.data.repository

import com.sol.kreditbee.api.ApiInterface
import com.sol.kreditbee.api.BaseDataSource
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(private val apiInterface: ApiInterface):
    BaseDataSource() {
    suspend fun fetchAlbum() = getResult{apiInterface.getAlbums()}
}