package com.example.pview.ui.main

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MenuItem
import com.example.pview.R
import com.example.pview.common.ext.*
import com.example.pview.common.util.RxBus
import com.example.pview.data.video.VideoEventModel
import com.example.pview.data.video.VideoModel
import com.example.pview.ui.base.BaseActivity
import com.example.pview.ui.list.ListVideoActivity
import com.example.pview.ui.login.LoginActivity
import com.example.pview.ui.test.TestActivity
import com.example.pview.widget.progressbar.DialogFinish
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import timber.log.Timber

class MainActivity : BaseActivity<MainView, MainPresenterImp>(), MainView,
    BottomNavigationView.OnNavigationItemSelectedListener, WebViewInterface {
    private val dialogFinish by lazy { DialogFinish(self) }
    private var index = 0
    private var videos = ArrayList<VideoModel>()
    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database.userDao().deleteAll()
    }
    override fun initView(): MainView {
        return this
    }

    override fun initPresenter(): MainPresenterImp {
        return MainPresenterImp(self)
    }

    override fun getLayoutId(): Int? {
        return R.layout.activity_main
    }

    override fun initWidgets() {
        hideToolbarBase()
        wvContent.settings()
        presenter.readAllVideo(database)
        bnvOptions.setOnNavigationItemSelectedListener(this)
        btnStart.setOnSafeClickListener {
            if (videos.size > 0) {
                time = edtTime.text.toString().toInt()
                edtTime.gone()
                imgNotify.gone()
                pbLoading.visible()
                setTextCount("${1 + index}/${videos.size}")
                wvContent.loadUrlAutoPlay(
                    videos[index].url, this
                )
            } else {
                toast(R.string.not_video)
            }
        }
        edtTime.setOnSafeClickListener {
            edtTime.setText("")
        }
    }


    private fun countDowTimber(time: Int) {
        var tTime = (time * 1000).toLong()
        var step = 1000.toLong()
        object : CountDownTimer(tTime, step), WebViewInterface {
            override fun onTick(millisUntilFinished: Long) {
                lblCount.visible()
                lblCount.text = "${millisUntilFinished.toInt() / 1000}s"
            }

            override fun onFinish() {
                if (index < videos.size) {
                    lblCount.text = "..."
                    pbLoading.visible()
                    setTextCount("${1 + index}/${videos.size}")
                    wvContent.loadUrlAutoPlay(
                        videos[index].url, this
                    )
                    index++
                } else {
                    index = 0;
                    dialogFinish.show()
                }
            }

            override fun readyPlayVideo() {
                pbLoading.gone()
                countDowTimber(time)
            }

            override fun loadPageSuccess() {
            }
        }.start()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_login -> {
                openActivity(LoginActivity::class.java)
            }
            R.id.menu_insert -> {
                openActivity(ListVideoActivity::class.java)
            }
        }
        return false
    }

    override fun readyPlayVideo() {
        countDowTimber(time)
    }

    override fun loadPageSuccess() {

    }

    override fun loadAllVideosSuccess(list: ArrayList<VideoModel>) {
        if (list.size > 0) {
            wvContent.visible()
            imgNotify.setImage(R.drawable.ic_launch)
            videos.addAll(list)
        } else {
            wvContent.gone()
            imgNotify.visible()
        }
        Log.e("TAG", list.size.toString())
    }

    override fun onResume() {
        super.onResume()
        if (videos.isEmpty()) {
            presenter.readAllVideo(database)
        }
    }

    fun setTextCount(text: String) {
        btnStart.text = text
    }

}