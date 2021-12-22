/*
 * PlayTheater.core
 * ImageViewExt.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.util.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import isato.made.playtheater.core.BuildConfig.BASE_IMAGE_URL
import jp.wasabeef.glide.transformations.BitmapTransformation

fun ImageView.loadImage(path: String) {
    Glide.with(this.context)
        .load("$BASE_IMAGE_URL$path")
        .override(300,250)
        .into(this)
}

fun ImageView.loadTransformImage(path: String, method: BitmapTransformation) {
    Glide.with(this.context)
        .load("$BASE_IMAGE_URL$path")
        .apply(bitmapTransform(method))
        .into(this)
}