package com.yologger.smemo.ui.screen.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.yologger.smemo.R
import com.yologger.smemo.databinding.ActivityDetailBinding
import com.yologger.smemo.ui.dto.MemoDto
import com.yologger.smemo.ui.screen.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initToolbar()
    }

    private fun initBinding() {
        val memoDto = intent.getSerializableExtra("selected_memo") as MemoDto
        viewModel.setLiveData(memoDto)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.event.observe(this) {
            when(it) {
                is DetailViewModel.Event.INPUTS_EMPTY_ERROR -> showToast(getString(R.string.activity_detail_toast_error))
                is DetailViewModel.Event.ON_MEMO_UPDATED -> {
                    showToast(getString(R.string.activity_detail_toast_success))
                    val intent = Intent()
                    intent.putExtra("updated_memo", it.memoDto)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.activity_detail_tb)
        toolbar.setNavigationIcon(R.drawable.icon_close_filled_black_24)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.inflateMenu(R.menu.activity_detail_menu_toolbar)
        toolbar.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.activity_detail_tb_save -> viewModel.onSave()
                else -> finish()
            }
            true
        }
    }
}