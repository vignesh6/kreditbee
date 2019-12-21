package com.sol.kreditbee.data.repository.local.albumdetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_detail")
data class AlbumDetail(
    @PrimaryKey
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)