package com.yologger.smemo.ui.screen.main.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.yologger.smemo.R
import com.yologger.smemo.common.ThemeManager
import com.yologger.smemo.ui.screen.main.settings.theme.ThemeActivity

class SettingsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: SettingsAdapter

    private val onItemClick = { position: Int ->
        when(position) {
            0 -> startActivity(Intent(requireActivity(), ThemeActivity::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        recyclerView = rootView.findViewById(R.id.fragment_settings_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = SettingsAdapter(onItemClick)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment().apply {}
    }
}