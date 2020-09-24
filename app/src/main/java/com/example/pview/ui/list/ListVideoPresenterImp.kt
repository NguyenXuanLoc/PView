package com.example.pview.ui.list

import android.content.Context
import android.util.Log
import com.example.pview.common.ext.addToCompositeDisposable
import com.example.pview.common.ext.applyIOWithAndroidMainThread
import com.example.pview.common.util.RxBus
import com.example.pview.data.video.VideoDatabase
import com.example.pview.data.video.VideoModel
import com.example.pview.ui.base.BasePresenterImp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListVideoPresenterImp(var ctx: Context) : BasePresenterImp<ListVideoView>(ctx) {

    fun insertData(model: VideoModel, database: VideoDatabase) {
        view?.also { v ->
            Observable.just(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        database.userDao().insert(it)
                    },
                    {

                    },
                    {
                        v.insertSuccess(model)
                    }
                ).addToCompositeDisposable(compositeDisposable)
        }

    }
    fun readAllVideo(database: VideoDatabase) {
        view?.also { v ->
            var list = database.userDao().readAllData()
            v.loadVideoSuccess(list as ArrayList<VideoModel>)
        }
    }
   /* fun getData() {
        view?.also { v ->
            RxBus.listenVideoEventModel()
                .applyIOWithAndroidMainThread()
                .subscribe({
                    v.loadVideoSuccess(it.videos)
                }, {
                    Timber.e(it.message)
                })
        }
    }*/

   /* fun test() {
        view?.also { v ->
            RxBus.listenTest()
                .applyIOWithAndroidMainThread()
                .subscribe(
                    {

                        Log.e("TAG", it)
                    }, {
                        Log.e("TAG", "ERroR: ${it.message}")
                    }
                ).addToCompositeDisposable(compositeDisposable)
        }
    }*/
}