package com.toggle.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.toggle.R

object ViewBinding {
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(image: ImageView, type: String?) {

        when(type){
            "INCOMING"-> {
                image.load(R.drawable.ic_phone_out) {
                    crossfade(true)
                    diskCachePolicy(CachePolicy.ENABLED)
                }
            }
        }


    }
}

