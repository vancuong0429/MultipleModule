package com.template.home.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.template.domain.entities.UserEntity

object HomeBinding {
    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, users: List<UserEntity>?) {
        with(recyclerView.adapter as HomeAdapter) {
            users?.let { updateData(it) }
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).apply(RequestOptions.circleCropTransform()).into(view)
    }


}