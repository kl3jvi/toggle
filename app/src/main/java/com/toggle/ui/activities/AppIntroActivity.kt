package com.toggle.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroFragment
import com.toggle.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AppIntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext

        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.first_slider)
        )

        addSlide(
            AppIntroFragment.createInstance(
                title = "The title of your slide",
                description = "A description that will be shown on the bottom",
                titleColorRes = R.color.primaryTextColor,
                descriptionColorRes = R.color.secondaryTextColor,
                backgroundColorRes = R.color.white,
            )
        )

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}

