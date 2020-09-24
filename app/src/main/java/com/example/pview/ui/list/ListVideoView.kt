package com.example.pview.ui.list

import com.example.pview.data.video.VideoModel
import com.example.pview.ui.base.BaseView

interface ListVideoView : BaseView {
    fun loadVideoSuccess(list: List<VideoModel>)

    fun insertSuccess(model: VideoModel)
}