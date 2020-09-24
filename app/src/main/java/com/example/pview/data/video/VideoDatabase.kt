package com.example.pview.data.video

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VideoModel::class], version = 2, exportSchema = false)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun userDao(): VideoDao

    companion object {
        @Volatile
        private var INSTANCE: VideoDatabase? = null
        fun getInstance(context: Context): VideoDatabase {
            var temp = INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}