package com.filippoengidashet.marketproductsapp.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.filippoengidashet.marketproductsapp.mvvm.model.database.Product

/**
 * @author Filippo 19/08/2021
 */
object Utils {

    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    fun dpToPx(context: Context, size: Float): Int {
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, size,
                context.resources.displayMetrics
            )
        )
    }

    fun search(query: String, items: List<Product>) =
        items.filter { p -> p.title.contains(query, true) }

    @JvmStatic
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            activity.findViewById<View>(android.R.id.content).windowToken,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    @JvmStatic
    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
