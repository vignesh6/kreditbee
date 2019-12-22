package com.sol.kreditbee.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetail
import com.sol.kreditbee.data.repository.local.albumdetail.AlbumDetailDao

/**
 * The Room database for this app
 */
@Database(entities = [Album::class,AlbumDetail::class],
        version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun albumDetailDao(): AlbumDetailDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "album-db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                           // val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                           // WorkManager.getInstance(context).enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
