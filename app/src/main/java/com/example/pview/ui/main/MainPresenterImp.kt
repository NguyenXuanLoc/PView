package com.example.pview.ui.main

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pview.data.video.VideoDatabase
import com.example.pview.data.video.VideoModel
import com.example.pview.ui.base.BasePresenterImp

class MainPresenterImp(var ctx: Context) : BasePresenterImp<MainView>(ctx) {
    fun readAllVideo(database: VideoDatabase) {
        showProgressDialog()
        view?.also { v ->
            var list = database.userDao().readAllData()
            v.loadAllVideosSuccess(list as ArrayList<VideoModel>)
            dismissProgressDialog()
        }
    }

    fun loadHtml(url: String, ctx: Context) {
        var stringRequest = StringRequest(Request.Method.GET, url,
            {
                Log.e("TAG", it.toString())
            }, {
//                Log.e("TAG", it.message)
            })
        Volley.newRequestQueue(ctx).add(stringRequest)
    }
}