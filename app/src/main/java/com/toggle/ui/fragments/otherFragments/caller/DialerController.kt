package com.toggle.ui.fragments.otherFragments.caller

import android.widget.TextView
import com.airbnb.epoxy.EpoxyController
import com.toggle.dialButton

fun EpoxyController.buildDial(tv: TextView) {
    val stringList = listOf(
        "A B C",
        "D E F",
        "G H I",
        "J K L",
        "M N O",
        "P Q R S",
        "T U V",
        "W X Y Z",
    )
    val lastRow = listOf(
        "*",
        "0",
        "#"
    )
    for (i in 1..12) {
        dialButton {
            id(i)
            if (i in 1..9) {
                num(i.toString())
            } else {
                num(lastRow[i - 10])
            }
            if (i in 2..9) {
                digits(stringList[i - 2])
            }
            clickListener { v ->
                when (i) {
                    10 -> tv.append("*")
                    11 -> tv.append(0.toString())
                    12 -> tv.append("#")
                    else -> tv.append(i.toString())
                }
            }
        }

    }
}