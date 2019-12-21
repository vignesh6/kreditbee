package com.sol.kreditbee.data.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)