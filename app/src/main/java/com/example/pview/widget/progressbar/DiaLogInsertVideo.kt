package com.example.pview.widget.progressbar

import android.content.Context
import com.example.pview.R
import com.example.pview.common.ext.setOnSafeClickListener
import com.example.pview.data.video.VideoModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.layout_insert.*

class DiaLogInsertVideo(var ctx: Context) : BottomSheetDialog(ctx, R.style.BottomSheepDialogTheme) {
    private var listener: InsertVideoListener? = null

    init {
        setContentView(R.layout.layout_insert)
        btnInsert.setOnSafeClickListener {
            var model = VideoModel(
                url = edtUrl.text.toString()
            )
            listener?.insert(model)
            edtUrl.setText("")
        }
        btnExit.setOnSafeClickListener {
            listener?.exitDialog()
        }
    }

    fun setInsertVideoListener(view: InsertVideoListener) {
        listener = view
    }

    interface InsertVideoListener {
        fun insert(model: VideoModel)
        fun exitDialog()
    }
}