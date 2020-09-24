package com.example.pview.ui.list

import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pview.R
import com.example.pview.common.ext.gone
import com.example.pview.common.ext.setOnSafeClickListener
import com.example.pview.common.ext.visible
import com.example.pview.data.video.VideoModel
import com.example.pview.ui.base.BaseActivity
import com.example.pview.widget.progressbar.DiaLogInsertVideo
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.toast

class ListVideoActivity : BaseActivity<ListVideoView, ListVideoPresenterImp>(), ListVideoView,
    DiaLogInsertVideo.InsertVideoListener {
    private var videos = ArrayList<VideoModel>()
    private val dialogInsert by lazy { DiaLogInsertVideo(self) }
    private val adapter by lazy {
        ListVideoAdapter(self, videos) { it -> onClickDelete(it) }
    }

    override fun initView(): ListVideoView {
        return this
    }

    override fun initPresenter(): ListVideoPresenterImp {
        return ListVideoPresenterImp(self)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list
    }

    override fun initWidgets() {
        applyToolbar()
        showTitle(R.string.list_video)
        enableHomeAsUp(backArrowColorResId = R.color.white) { finish() }
        rclVideo.adapter = adapter
        rclVideo.layoutManager = LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false)
        rclVideo.setHasFixedSize(true)

        presenter.readAllVideo(database)
        dialogInsert.setInsertVideoListener(this)
        btnAdd.setOnSafeClickListener {
            dialogInsert.show()
        }

    }

    override fun loadVideoSuccess(list: List<VideoModel>) {
        if (list.isNotEmpty()) {
            videos.addAll(list)
            adapter.notifyDataSetChanged()
            imgNotify.gone()
            rclVideo.visible()
        } else {
            imgNotify.visible()
            rclVideo.gone()
        }
    }


    override fun insertSuccess(model: VideoModel) {
        imgNotify.gone()
        rclVideo.visible()
        videos.add(model)
        adapter.notifyDataSetChanged()
        toast(R.string.insert_success)
    }

    private fun onClickDelete(model: VideoModel) {
        videos.remove(model)
        adapter.notifyDataSetChanged()
    }

    override fun insert(model: VideoModel) {
        presenter.insertData(model, database)
    }

    override fun exitDialog() {
        dialogInsert.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> {
                database.userDao().deleteAll()
                videos.clear()
                adapter.notifyDataSetChanged()
                toast(R.string.delete_all_success)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}