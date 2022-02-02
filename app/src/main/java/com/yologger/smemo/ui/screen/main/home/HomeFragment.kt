package com.yologger.smemo.ui.screen.main.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.orhanobut.logger.Logger
import com.yologger.smemo.R
import com.yologger.smemo.ui.screen.create.CreateActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var speedDialView: SpeedDialView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MemosAdapter

    private val startActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.extras?.getLong("id")?.let { viewModel.addNewMemoById(it) }
        }
    }

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
            when (it.id) {
                R.id.fragment_home_sdv_add -> {
                    val nextIntent = Intent(requireActivity(), CreateActivity::class.java)
                    startActivityForResult.launch(nextIntent)
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.fragment_home_sdv_edit -> {
                    speedDialView.close()
                    return@OnActionSelectedListener true
                }
                R.id.fragment_home_sdv_delete -> {
                    speedDialView.close()
                    showAlertDialog("Delete All")
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
        viewModel.liveMemos.observe(viewLifecycleOwner) {
            recyclerViewAdapter.updateAll(it)
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is HomeViewModel.Event.MEMO_ADDED -> {
                    recyclerViewAdapter.addLast(it.memoDto)
                }
            }
        }
    }

    private fun showAlertDialog(message: String) = AlertDialog.Builder(requireContext())
        .setMessage("Delete all?")
        .setPositiveButton("OK") { _, _ -> viewModel.deleteAllMemos() }
        .setNegativeButton("Cancel") { _, _ -> }
        .create()
        .show()

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply { }
    }
}