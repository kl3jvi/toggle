package com.toggle.ui.fragments.loginFragments.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.toggle.databinding.IntroFragmentBinding


class IntroFragment : Fragment() {

    private lateinit var binding: IntroFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = IntroFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginDirection.setOnClickListener {
            findNavController().navigate(
                IntroFragmentDirections.actionIntroFragmentToLoginFragment()
            )
        }
    }

}