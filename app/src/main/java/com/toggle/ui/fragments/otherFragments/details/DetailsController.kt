package com.toggle.ui.fragments.otherFragments.details

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.toggle.R
import com.toggle.cardDetails
import com.toggle.data.model.CallHistoryItem
import com.toggle.recording
import com.toggle.title
import com.toggle.utils.randomId

fun EpoxyController.buildDetails(
    callDetails: CallHistoryItem,
    context: Context,
) {
    title {
        id(randomId())
        title(context.getString(R.string.contact_info))
    }
    cardDetails {
        id(randomId())
        number(callDetails.callingNo)
        drawable(R.drawable.ic_people)
        background(R.color.primaryColor)
        isVisible(false)
    }
    title {
        id(randomId())
        title(context.resources.getString(R.string.call_info))
    }
    cardDetails {
        id(randomId())
        number(callDetails.callingNo)
        drawable(R.drawable.ic_phone_out)
        background(R.color.laPalma)
        isVisible(false)
    }
    cardDetails {
        id(randomId())
        number(callDetails.callingNo)
        drawable(R.drawable.ic_people)
        background(R.color.laPalma)
        isVisible(false)
    }

    title {
        id(randomId())
        title(context.getString(R.string.recording))
    }
    recording {
        id(randomId())
        soundProgress(2)
    }
    title {
        id(randomId())
        title(context.getString(R.string.assignedTo))
    }
    cardDetails {
        id(randomId())
        number(callDetails.callingNo)
        drawable(R.drawable.ic_people)
        details("Assigned by Pavit Raj")
        background(R.color.laPalma)
        isVisible(true)
    }
    title {
        id(randomId())
        title(context.getString(R.string.tags))
    }
}