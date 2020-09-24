package com.example.pview.ui.list

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pview.R
import com.example.pview.common.ext.setOnSafeClickListener
import com.example.pview.data.video.VideoModel
import kotlinx.android.synthetic.main.item_video.view.*

class ListVideoAdapter(
    var ctx: Activity,
    var list: ArrayList<VideoModel>,
    var onClickDelete: (VideoModel) -> Unit
) : RecyclerView.Adapter<ListVideoAdapter.ItemHolder>() {
    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblName = itemView.findViewById<TextView>(R.id.lbl_name)
        fun bind(model: VideoModel) {
            lblName.text = model.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_video, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        if (itemCount > 0) {
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}