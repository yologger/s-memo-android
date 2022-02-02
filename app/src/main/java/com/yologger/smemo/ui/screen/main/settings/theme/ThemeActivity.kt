package com.yologger.smemo.ui.screen.main.settings.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger
import com.yologger.smemo.R
import com.yologger.smemo.common.ThemeManager

class ThemeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ThemeRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.activity_theme_rv)
        recyclerViewAdapter = ThemeRVAdapter(this) { position ->
            when(position) {
                0 -> ThemeManager.applyTheme(applicationContext, ThemeManager.ThemeMode.LIGHT)
                1 -> ThemeManager.applyTheme(applicationContext, ThemeManager.ThemeMode.DARK)
            }
        }
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
}