package com.yologger.smemo.ui.screen.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.orhanobut.logger.Logger
import com.yologger.smemo.R
import com.yologger.smemo.ui.dto.MemoDto
import com.yologger.smemo.ui.screen.create.CreateActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var speedDialView: SpeedDialView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MemosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        speedDialView = rootView.findViewById(R.id.fragment_home_sdv)
        recyclerView = rootView.findViewById(R.id.fragment_home_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpeedDialView()
        setupRecyclerView()
        setupObserving()
    }

    private fun setupSpeedDialView() {
        speedDialView.addActionItem(SpeedDialActionItem.Builder(R.id.fragment_home_sdv_add, R.drawable.icon_create_filled_black_24).setLabel(getString(R.string.fragment_home_sdv_new_post)).create())
        speedDialView.addActionItem(SpeedDialActionItem.Builder(R.id.fragment_home_sdv_edit, R.drawable.icon_reorder_filled_black_24).setLabel(getString(R.string.fragment_home_sdv_reorder)).create())
        speedDialView.addActionItem(SpeedDialActionItem.Builder(R.id.fragment_home_sdv_delete, R.drawable.icon_delete_filled_black_24).setLabel(getString(R.string.fragment_home_sdv_delete)).create())
        speedDialView.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener {
            when(it.id) {
                R.id.fragment_home_sdv_add -> {
                    val nextIntent = Intent(requireActivity(), CreateActivity::class.java)
                    startActivity(nextIntent)
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.fragment_home_sdv_edit -> {
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.fragment_home_sdv_delete -> {
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
            }
            false
        })
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = MemosAdapter()
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    private fun setupObserving() {
        viewModel.memos.observe(viewLifecycleOwner) {
            recyclerViewAdapter.updateAll(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply { }
    }
}