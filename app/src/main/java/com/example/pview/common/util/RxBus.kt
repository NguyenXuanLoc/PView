package com.example.pview.common.util

import android.util.Log
import com.example.pview.data.video.VideoEventModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {
    private val publisher = PublishSubject.create<Any?>()

    private fun publish(event: Any?) {
        event?.let {
            publisher.onNext(it)
        }
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    private fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

    fun publishVideoEventModel(model: VideoEventModel) {
        publish(model)
    }

    fun listenVideoEventModel(): Observable<VideoEventModel> {
        return listen(VideoEventModel::class.java)
    }

    fun publishTest(model: String) {
        publish(model)
    }

    fun listenTest(): Observable<String> {
        return listen(String::class.java)
    }
}