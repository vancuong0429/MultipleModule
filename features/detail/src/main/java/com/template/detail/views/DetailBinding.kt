package com.template.detail.views

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object DetailBinding {
    @BindingAdapter("app:imageUrlRounded")
    @JvmStatic fun loadImageRounded(view: androidx.appcompat.widget.AppCompatImageView, url: String?) {
        Glide.with(view.context).load(url).apply(RequestOptions.circleCropTransform()).into(view)
    }
}