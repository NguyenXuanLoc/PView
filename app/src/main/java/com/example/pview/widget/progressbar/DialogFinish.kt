package com.example.pview.widget.progressbar

import android.app.Dialog
import android.content.Context
import com.example.pview.R
import com.example.pview.common.ext.setOnSafeClickListener
import kotlinx.android.synthetic.main.layout_dialog_finish.*

class DialogFinish(var ctx: Context) : Dialog(ctx) {
    init {
        setContentView(R.layout.layout_dialog_finish)
        setCanceledOnTouchOutside(false)
        imgFinish.setOnSafeClickListener {
            dismiss()
        }
    }
}