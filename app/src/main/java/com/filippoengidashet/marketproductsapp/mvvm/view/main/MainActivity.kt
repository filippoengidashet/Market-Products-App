package com.filippoengidashet.marketproductsapp.mvvm.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.filippoengidashet.marketproductsapp.R
import com.filippoengidashet.marketproductsapp.mvvm.view.products.list.ProductsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button_tap).setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
            finish()
        }
    }
}
