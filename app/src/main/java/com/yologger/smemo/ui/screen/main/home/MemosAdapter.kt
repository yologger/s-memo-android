package com.yologger.smemo.ui.screen.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.yologger.smemo.R
import com.yologger.smemo.ui.dto.MemoDto

class MemosAdapter(
    private val context: Context,
    private var memos: MutableList<MemoDto> = mutableListOf(),
    private val onItemClickListener: (MemoDto) -> Unit,
    private val onItemRemoveListener: (Int) -> Unit,
): RecyclerView.Adapter<MemosAdapter.MemoViewHolder>() {

    inner class MemoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.fragment_home_item_tv_title)
        private val buttonMenu: ImageButton = itemView.findViewById(R.id.fragment_home_item_ibtn_more)
        fun bind(holder: MemoViewHolder, memo: MemoDto) {
            textViewTitle.text = memo.title
            buttonMenu.setOnClickListener { 
                val popupMenu = PopupMenu(context, holder.buttonMenu)
                popupMenu.inflate(R.menu.activity_main_menu_option)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.activity_main_menu_option_delete -> {
                            onItemRemoveListener(adapterPosition)
                            return@setOnMenuItemClickListener true
                        }
                    }
                    return@setOnMenuItemClickListener false
                }
                popupMenu.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item, parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = memos[position]
        holder.bind(holder, memo)
        holder.itemView.setOnClickListener { onItemClickListener(memo) }
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

    fun deleteAt(index: Int) {
        memos.removeAt(index)
        notifyItemRemoved(index)
    }
}