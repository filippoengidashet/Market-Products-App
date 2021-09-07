package com.filippoengidashet.marketproductsapp.mvvm.view.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Filippo 19/08/2021
 */
abstract class BaseActivity : AppCompatActivity() {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        onViewReady(savedInstanceState)
    }

    @CallSuper
    protected open fun onViewReady(state: Bundle?) {}

    protected fun showToast(message: String?, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

    abstract fun getContentView(): Int
}