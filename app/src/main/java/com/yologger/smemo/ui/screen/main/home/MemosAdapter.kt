package com.yologger.smemo.ui.screen.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yologger.smemo.R
import com.yologger.smemo.ui.dto.MemoDto

class MemosAdapter(
    private var memos: MutableList<MemoDto> = mutableListOf()
): RecyclerView.Adapter<MemosAdapter.MemoViewHolder>() {

    inner class MemoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.fragment_home_item_tv_title)
        fun bind(memo: MemoDto) {
            textViewTitle.text = memo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item, parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) = holder.bind(memos[position])

    override fun getItemCount(): Int = memos.size

    fun updateAll(newMemos: List<MemoDto>) {
        memos = newMemos.toMutableList()
        notifyDataSetChanged()
    }
}