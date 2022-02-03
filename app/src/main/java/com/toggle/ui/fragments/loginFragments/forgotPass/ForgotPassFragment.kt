package com.toggle.ui.fragments.loginFragments.forgotPass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.databinding.ForgotPassFragmentBinding

class ForgotPassFragment : Fragment() {


    private val viewModel: ForgotPassViewModel by viewModels()
    private lateinit var binding: ForgotPassFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ForgotPassFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


}