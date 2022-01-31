package com.yologger.smemo.ui.screen.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    protected fun showToast(message: String) = Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()
}