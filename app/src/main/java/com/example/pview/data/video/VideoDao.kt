package com.example.pview.data.video

import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(model: VideoModel)

    @Delete
    fun delete(model: VideoModel)

    @Delete
    fun deleteAll(list: List<VideoModel>)


    @Query("SELECT * FROM video_model")
    fun readAllData(): List<VideoModel>

    @Query("DELETE FROM video_model")
    fun deleteAll()
}