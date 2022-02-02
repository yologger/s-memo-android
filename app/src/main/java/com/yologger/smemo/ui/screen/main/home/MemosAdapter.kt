package com.yologger.smemo.ui.screen.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.yologger.smemo.R
import com.yologger.smemo.ui.dto.MemoDto

class MemosAdapter(
    private var memos: MutableList<MemoDto> = mutableListOf(),
    private val listener: (MemoDto) -> Unit
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

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = memos[position]
        holder.bind(memo)
        holder.itemView.setOnClickListener { listener(memo) }
    }

    override fun getItemCount(): Int = memos.size

    fun updateAll(newMemos: List<MemoDto>) {
        memos = newMemos.toMutableList()
        notifyDataSetChanged()
    }

    fun addLast(newMemo: MemoDto) {
        memos.add(newMemo)
        notifyItemInserted(memos.size + 1)
    }

    fun update(memo: MemoDto) {
        for ((i, m) in memos.withIndex()) {
            if (m.id == memo.id) {
                memos.removeAt(i)
                memos.add(i, memo)
                notifyItemChanged(i)
                break
            }
        }
    }
}