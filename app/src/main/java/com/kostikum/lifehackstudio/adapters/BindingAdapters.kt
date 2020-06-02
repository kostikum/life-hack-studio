package com.kostikum.lifehackstudio.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kostikum.lifehackstudio.R
import com.kostikum.lifehackstudio.network.baseUrl

@BindingAdapter("lat", "lon")
fun hideIfNotValidCoordinates(view: View, lat: String?, lon: String?) {
    if (lat.isNullOrEmpty() || lon.isNullOrEmpty()) {
        view.visibility = View.GONE
    } else if (lat == "0" && lon == "0") view.visibility = View.GONE
    else {
        try {
            val latDouble = lat.toDouble()
            val lonDouble = lon.toDouble()
            if (latDouble >= -90 && latDouble <= 90 && lonDouble >= -190 && lonDouble <= 190) {
                view.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            view.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) imageView.visibility = View.GONE
    else {
        imageView.visibility = View.VISIBLE
        Glide.with(imageView.context).load("$baseUrl$url")
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(imageView)
    }
}

@BindingAdapter("setTextOrHide")
fun setText(textView: TextView, text: String?) {
    if (text.isNullOrEmpty()) textView.visibility = View.GONE
    else {
        textView.visibility = View.VISIBLE
        textView.text = text
    }
}
