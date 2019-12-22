package com.sol.kreditbee.api


import com.sol.kreditbee.data.repository.local.Album
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        const val URL_ENDPOINT = "https://jsonplaceholder.typicode.com/"
    }
    @GET("albums")
    suspend fun getAlbums():Response<List<Album>>

    @GET("photos")
    suspend fun getPhotosByAlbumId(@Query("albumId")id:Int?=null):Response<List<AlbumDetail>>
}