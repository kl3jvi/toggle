package com.toggle.ui.fragments.loginFragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toggle.databinding.LoginFragmentBinding
import com.toggle.ui.activities.MainActivity
import com.toggle.utils.launchActivity

class LoginFragment : Fragment() {


    private lateinit var binding: LoginFragmentBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToForgotPassFragment()
            )
        }
        binding.login.setOnClickListener {

            requireActivity().apply {
                launchActivity<MainActivity> {}
                finish()
            }
        }
    }

}