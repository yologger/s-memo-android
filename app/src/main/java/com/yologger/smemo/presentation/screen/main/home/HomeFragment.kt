package com.yologger.smemo.presentation.screen.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.yologger.smemo.R
import com.yologger.smemo.presentation.screen.create.CreateActivity

class HomeFragment : Fragment() {

    private lateinit var speedDialView: SpeedDialView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        speedDialView = rootView.findViewById(R.id.fragment_home_sdv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpeedDialView()
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

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply { }
    }
}