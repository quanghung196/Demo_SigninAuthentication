package com.example.demo_signinauthentication.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.demo_signinauthentication.R
import com.makeramen.roundedimageview.RoundedImageView

//binding imageview with glide
@BindingAdapter("imageURL")
fun loadImage(view: RoundedImageView, profileImage: String) {

    Glide.with(view.context)
        .load(profileImage)
        .placeholder(R.drawable.image_place_holder)
        .error(R.drawable.image_error)
        .into(view)
}

