package com.toggle.ui.fragments.loginFragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toggle.data.model.LoginResponseItem
import com.toggle.databinding.LoginFragmentBinding
import com.toggle.ui.activities.MainActivity
import com.toggle.utils.State
import com.toggle.utils.collectFlow
import com.toggle.utils.launchActivity
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
            if (validateFields()) {
                val email = binding.emailAddress.text.toString()
                val password = binding.editTextTextPassword.text.toString()
                collectFlow(
                    viewModel.checkForLogIn(
                        email,
                        password
                    )
                ) { state ->
                    when (state) {
                        is State.Error -> makeToast("Error!")
                        is State.Loading -> makeToast("Checking Database!")
                        is State.Success -> tryLogIn(state.data?.first())
                    }
                }
            }
        }
    }


    private fun validateFields(): Boolean {
        if (binding.emailAddress.text.isEmpty()) binding.emailAddress.error =
            "Please Enter E-Mail Address"
        if (binding.editTextTextPassword.text.isEmpty()) binding.editTextTextPassword.error =
            "Please Enter Password"
        return binding.emailAddress.text.isNotEmpty() && binding.editTextTextPassword.text.isNotEmpty()
    }

    private fun Fragment.makeToast(text: String?) {
        Toast.makeText(requireContext(), text.orEmpty(), Toast.LENGTH_SHORT).show()
    }


    private fun tryLogIn(responseItem: LoginResponseItem?) {
        if (responseItem?.flag.equals("Wrong Password ")) {
            makeToast(responseItem?.flag)
        } else if (responseItem?.flag.equals("Success")) {
            responseItem?.let {
                viewModel.saveUserData(
                    it.userId.toString(),
                    it.TUserId.toString()
                )
            }
            requireActivity().apply {
                launchActivity<MainActivity> {}
                finish()
            }
        }
    }
}

