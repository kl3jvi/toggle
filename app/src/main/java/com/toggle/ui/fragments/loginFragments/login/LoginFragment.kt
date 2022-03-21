package com.toggle.ui.fragments.loginFragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.toggle.databinding.LoginFragmentBinding
import com.toggle.utils.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

//            if (validateFields()) {
            val email = binding.emailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
//            requireActivity().apply {
//                launchActivity<MainActivity> {}
//                finish()
//            }
            lifecycleScope.launchWhenCreated {
                viewModel.checkForLogIn(
                    email,
                    password
                ).collect { state ->
                    when (state) {
                        is State.Error -> {
                            Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                        }
                        is State.Loading -> {
                            Toast.makeText(
                                requireContext(),
                                "Checking Database!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is State.Success -> {
                            Toast.makeText(
                                requireContext(),
                                "${state.data}",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }
            }

//
        }
    }

    private fun validateFields(): Boolean {
        return binding.emailAddress.text.isEmpty() && binding.editTextTextPassword.text.isEmpty()
    }

}