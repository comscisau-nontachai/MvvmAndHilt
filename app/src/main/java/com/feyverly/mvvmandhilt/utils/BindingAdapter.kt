package com.feyverly.mvvmandhilt.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url:String?){
    url?.let {
        Glide.with(this).load(url).transform(CircleCrop()).into(this)
    }
}