package com.example.pview.data.video

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_model")
data class VideoModel(
    @ColumnInfo(name = "name")
    var name: String?="",
    @ColumnInfo(name = "name_channel")
    var nameChannel: String?="",
    @ColumnInfo(name = "url")
    var url: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}