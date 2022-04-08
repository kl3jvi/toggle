package com.toggle.bindings

import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.toggle.R

object ViewBinding {
    @JvmStatic
    @BindingAdapter("image", "cardBgColor")
    fun setImage(image: ImageView, type: String?, cardView: CardView) {
        when (type) {
            "INCOMING" -> {
                image.load(R.drawable.ic_phone_in) {
                    crossfade(true)
                    diskCachePolicy(CachePolicy.ENABLED)
                }
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        cardView.context,
                        R.color.red
                    )
                )
            }
            else -> {
                image.load(R.drawable.ic_phone_out) {
                    crossfade(true)
                    diskCachePolicy(CachePolicy.ENABLED)
                }
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        cardView.context,
                        R.color.laPalma
                    )
                )
            }
        }
    }

    @JvmStatic
    @BindingAdapter("drawable")
    fun setDrawable(image: ImageView, drawable: Int) {
        image.load(drawable) {
            crossfade(true)
            diskCachePolicy(CachePolicy.ENABLED)
        }

    }

    @JvmStatic
    @BindingAdapter("cardBg")
    fun setCardBg(cardView: CardView, color: Int?) {
        if (color != null) {
            cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    cardView.context,
                    color
                )
            )
        }
    }

    @JvmStatic
    @BindingAdapter("progress")
    fun setProgress(slider: SeekBar, progress: Int?) {
        if (progress != null) {
            slider.progress = progress
        }
    }

    @JvmStatic
    @BindingAdapter("booleanVisibility")
    fun setVisibility(view: View, isVisible: Boolean? = false) {
        view.isVisible = isVisible ?: false
    }


}

