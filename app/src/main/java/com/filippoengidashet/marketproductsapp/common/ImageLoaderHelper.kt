package com.filippoengidashet.marketproductsapp.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * @author Filippo 20/08/2021
 */
object ImageLoaderHelper {

    fun loadImage(url: Any, target: ImageView) {
        Glide.with(target.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(ColorDrawable(Color.parseColor("#cacaca")))
            .error(ColorDrawable(Color.parseColor("#ffa993")))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(target)
    }
}
