package isato.made.playtheater.core.util.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import isato.made.playtheater.core.BuildConfig.BASE_IMAGE_URL

fun ImageView.loadImage(path: String) {
    Glide.with(this.context)
        .load("$BASE_IMAGE_URL$path")
        .override(300,250)
        .into(this)
}