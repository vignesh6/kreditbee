package com.sol.kreditbee.data.repository.local.albumdetail

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDetailDao {
    @Query("SELECT * FROM ALBUM_DETAIL WHERE albumId = :albumId")
    fun getAlbumDetails(albumId:Int): DataSource.Factory<Int, AlbumDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albumDetail: List<AlbumDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albumDetail: AlbumDetail)

    @Query("SELECT * FROM ALBUM_DETAIL WHERE albumId = :albumId  ORDER BY ID  ASC limit :pageSize"  )
    fun getAlbumsByIdInitial(albumId: Int?,pageSize:Int): List<AlbumDetail>
    @Query("SELECT * FROM ALBUM_DETAIL WHERE albumId = :albumId AND id> :key  ORDER BY ID  ASC limit :pageSize"  )
    fun getAlbumsByIdAfter(albumId: Int?,key:Int,pageSize: Int): List<AlbumDetail>
    @Query("SELECT * FROM ALBUM_DETAIL WHERE albumId = :albumId")
    fun getAlbumsById(albumId: Int?): LiveData<List<AlbumDetail>>

}