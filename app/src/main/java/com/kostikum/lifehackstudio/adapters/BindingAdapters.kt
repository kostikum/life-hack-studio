package com.kostikum.lifehackstudio.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kostikum.lifehackstudio.R
import com.kostikum.lifehackstudio.network.baseUrl

@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE

    if (isNetWorkError) {
        view.visibility = View.GONE
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
