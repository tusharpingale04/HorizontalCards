package com.tushar.horizontalcards.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import coil.api.load
import com.tushar.horizontalcards.AppController
import com.tushar.horizontalcards.R


/**
 * Consists of all binding adapters used in the layout file
 */

@BindingAdapter("bindDrawable")
fun setImageDrawable(view: ImageView, color: String) {

    val layerDrawable = AppController.getInstance().getDrawable(R.drawable.bg_gradient) as LayerDrawable
    val gradientDrawable = layerDrawable.findDrawableByLayerId(R.id.main_bg) as GradientDrawable
    gradientDrawable.shape = GradientDrawable.RECTANGLE
    gradientDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
    gradientDrawable.colors = intArrayOf(Color.parseColor("#${color}"), darkenColor(Color.parseColor("#${color}"), 0.8f))

    val primaryCircle = layerDrawable.findDrawableByLayerId(R.id.primaryCircle) as GradientDrawable
    primaryCircle.orientation = GradientDrawable.Orientation.RIGHT_LEFT
    primaryCircle.colors = intArrayOf(Color.parseColor("#${color}"), android.R.color.transparent)

    val secondaryCircle = layerDrawable.findDrawableByLayerId(R.id.SecondaryCircle) as GradientDrawable
    secondaryCircle.orientation = GradientDrawable.Orientation.RIGHT_LEFT
    secondaryCircle.colors = intArrayOf(Color.parseColor("#${color}"), darkenColor(Color.parseColor("#${color}"), 0.6f))


    val drawableArray = arrayOf<Drawable>(gradientDrawable, primaryCircle, secondaryCircle)
    val ld = LayerDrawable(drawableArray)

    val width = screenRectPx.width()
    val height = screenRectPx.height() / 4 + 100

    ld.setLayerWidth(0, width)
    ld.setLayerHeight(0, height)

    ld.setLayerInset(1, (-width / 5.4).dp2px, 0, (width / 21.6).dp2px, 0)
    ld.setLayerInset(2, (width / 7.2).dp2px, 0, (-width / 21.6).dp2px, (-height / 68.3).dp2px)

    view.setImageDrawable(ld)
}

@ColorInt
fun darkenColor(@ColorInt color: Int, percent: Float): Int {
    return Color.HSVToColor(FloatArray(3).apply {
        Color.colorToHSV(color, this)
        this[2] *= percent
    })
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView,url: String?) {
    url?.let {
        imageView.load("https://${it.substring(2,it.length)}") {
            error(R.drawable.ic_broken_image)
        }
    } ?: imageView.setImageResource(R.drawable.ic_broken_image)
}