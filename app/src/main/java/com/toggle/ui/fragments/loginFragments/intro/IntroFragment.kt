package com.toggle.ui.fragments.loginFragments.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toggle.R
import com.toggle.databinding.IntroFragmentBinding
import com.toggle.ui.activities.MainActivity
import com.toggle.utils.launchActivity
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroFragment : Fragment(R.layout.intro_fragment) {

    private val binding: IntroFragmentBinding by viewBinding()
    private val viewModel: IntroViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginDirection.setOnClickListener {
            findNavController().navigate(
                IntroFragmentDirections.actionIntroFragmentToLoginFragment(

                )
            )
        }
        checkIfIsLoggedIn()
    }

    private fun checkIfIsLoggedIn() {
        val isLoggedIn = viewModel.isUserLoggedIn()
        if (isLoggedIn)
            requireContext().launchActivity<MainActivity> { }
    }
}