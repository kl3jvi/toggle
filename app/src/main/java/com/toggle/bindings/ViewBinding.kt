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
import com.toggle.utils.loadUrl

object ViewBinding {
    /**
     * It sets the image of the imageview and the background color of the cardview based on the type of
     * the call.
     *
     * @param image ImageView - The ImageView that will be used to display the image.
     * @param type The type of call, either incoming or outgoing.
     * @param cardView The CardView that will be used to display the call log.
     */
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
    @BindingAdapter("imageUrl")
    fun setImageUrl(image: ImageView, uri: String) {
        if (uri.contains("svg")) {
            image.loadUrl(uri)
        } else {
            image.load(uri) {
                crossfade(true)
                diskCachePolicy(CachePolicy.ENABLED)
            }
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

    /**
     * setVisibility() is a function that takes a `View` and a `Boolean` and
     * sets the visibility of the `View` to `true` if the `Boolean` is `true` and `false` if the
     * `Boolean` is `false`
     *
     * @param view View - The view that we are setting the visibility on.
     * @param isVisible Boolean? = false
     */
    @JvmStatic
    @BindingAdapter("booleanVisibility")
    fun setVisibility(view: View, isVisible: Boolean? = false) {
        view.isVisible = isVisible ?: false
    }


}

