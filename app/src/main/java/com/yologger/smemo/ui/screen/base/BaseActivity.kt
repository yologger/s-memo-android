package com.yologger.smemo.ui.screen.base

import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    protected fun showToast(message: String) {
        val toast = Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }
}