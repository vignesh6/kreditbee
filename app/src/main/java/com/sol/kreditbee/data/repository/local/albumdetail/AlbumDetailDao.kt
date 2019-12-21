package com.sol.kreditbee.data.repository.local.albumdetail

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


}