package com.example.pview.ui.main

import android.os.CountDownTimer
import android.util.Log
import android.view.MenuItem
import com.example.pview.R
import com.example.pview.common.ext.WebViewInterface
import com.example.pview.common.ext.loadUrlAutoPlay
import com.example.pview.common.ext.openActivity
import com.example.pview.common.ext.settings
import com.example.pview.ui.base.BaseActivity
import com.example.pview.ui.list.ListVideoActivity
import com.example.pview.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainView, MainPresenterImp>(), MainView,
    BottomNavigationView.OnNavigationItemSelectedListener, WebViewInterface {
    private var index = 0
    private var list: ArrayList<String> = ArrayList()
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
        test()
        wvContent.settings()
        wvContent.loadUrlAutoPlay(
            list[index], this
        )
//        playVideo("http://onmobi.vn/video/detail/26588/cua-da-nong-noc-au-trung-chuon-chuon-lam-tat-voi-la-chua-rung-bang-ong-nua-doi-song-tay-bac?click_source=default&click_medium=video_new")
        bnvOptions.setOnNavigationItemSelectedListener(this)
    }


    private fun countDowTimber(time: Int) {
        var tTime = (time * 1000).toLong()
        var step = 1000.toLong()
        object : CountDownTimer(tTime, step), WebViewInterface {
            override fun onTick(millisUntilFinished: Long) {
                Log.e("TAG", millisUntilFinished.toString())
            }

            override fun onFinish() {
                if (index < list.size) {
                    wvContent.loadUrlAutoPlay(
                        list[index], this
                    )
                    index++
                    Log.e("TAG", "play: $index")
                } else {
                    Log.e("TAG", "play xong")
                }
            }

            override fun readyPlayVideo() {
                countDowTimber(10)
                Log.e("TAG", "READY")
                Timber.e("READY")
            }
        }.start()
    }

    private fun test() {
        list.add("http://onmobi.vn/video/detail/27566/danh-tiet-canh-ngan-bang-nuoc-khe-suoi-bua-trua-giua-rung-cua-anh-em-doi-song-tay-bac?click_source=default&click_medium=related_of_video_26588")
        list.add("http://onmobi.vn/video/detail/28020/dua-dau-ve-que-huong-yen-chau-son-la-p2-tai-tay-bac?click_source=default&click_medium=related_of_video_27566")
        list.add("http://onmobi.vn/video/detail/28017/doi-song-tay-bac-dua-dau-ve-que-huong-yen-chau-son-la-p2?click_source=default&click_medium=related_of_video_28020")
        list.add("http://onmobi.vn/video/detail/23950/du-khach-tay-cung-lay-dien-thoai-de-quay-khi-quang-binh-noi-tieng-anh-thong-minh?click_source=default&click_medium=related_of_video_28017")

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
        countDowTimber(10)
        Log.e("TAG", "READY")
        Timber.e("READY")
    }
}