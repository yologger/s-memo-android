package com.yologger.smemo.ui.screen.main.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yologger.smemo.R

class SettingsRVAdapter: RecyclerView.Adapter<SettingsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_settings_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.fragment_settings_item_name)
        private val textViewValue: TextView = itemView.findViewById(R.id.fragment_settings_item_value)

        fun bind() {

        }
    }
}