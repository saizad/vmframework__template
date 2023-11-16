package com.drone.destination.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.drone.destination.R
import com.vm.framework.enums.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun ImageView.circle(
    src: String, scaleType: BitmapTransformation = FitCenter(),
    hideOnFail: Boolean = false,
    @DrawableRes ph: Int? = R.drawable.baseline_image_24,
    @DrawableRes errorImage: Int = R.drawable.round_warning_24
) {
    glideRequestBuilder(src, hideOnFail, ph, errorImage)
        .transform(CircleCrop(), scaleType)
        .into(this)
}

fun ImageView.glideRequestBuilder(
    src: String,
    hideOnFail: Boolean = false,
    @DrawableRes ph: Int? = R.drawable.baseline_image_24,
    @DrawableRes errorImage: Int = R.drawable.round_warning_24
): RequestBuilder<Drawable> {
    return Glide.with(context)
        .load(src)
        .run {
            ph?.let {
                return@run placeholder(ph)
            }
            return@run this
        }
        .error(errorImage)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                isVisible = !hideOnFail
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                isVisible = true
                return false
            }

        })
}