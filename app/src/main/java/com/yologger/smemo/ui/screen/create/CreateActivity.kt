package com.yologger.smemo.ui.screen.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.yologger.smemo.R
import com.yologger.smemo.databinding.ActivityCreateBinding
import com.yologger.smemo.ui.screen.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateActivity : BaseActivity() {

    private val viewModel: CreateViewModel by viewModel()
    private lateinit var binding: ActivityCreateBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initToolbar()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.event.observe(this) {
            when(it) {
                is CreateViewModel.Event.INPUTS_EMPTY_ERROR -> showToast(getString(R.string.activity_create_toast_error))
                is CreateViewModel.Event.ON_MEMO_SAVED -> {
                    showToast(getString(R.string.activity_create_toast_success))
                    val intent = Intent()
                    intent.putExtra("id", it.id)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.activity_create_tb)
        toolbar.title = getString(R.string.activity_create_tb_title)
        toolbar.setNavigationIcon(R.drawable.icon_close_filled_black_24)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.inflateMenu(R.menu.activity_create_menu_toolbar)
        toolbar.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.activity_create_tb_save -> { viewModel.onSave() }
                else -> { finish() }
            }
            true
        }
    }
}