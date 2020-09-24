package com.example.pview.ui.main

import com.example.pview.data.video.VideoModel
import com.example.pview.ui.base.BaseView

interface MainView : BaseView {
    fun loadAllVideosSuccess(list: ArrayList<VideoModel>)
}