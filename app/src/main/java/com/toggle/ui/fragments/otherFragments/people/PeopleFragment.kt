package com.toggle.ui.fragments.otherFragments.people

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.databinding.PeopleFragmentBinding
import com.toggle.utils.viewBinding

class PeopleFragment : Fragment(R.layout.people_fragment) {

    private val viewModel: PeopleViewModel by viewModels()
    private val binding: PeopleFragmentBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* A listener for the toggle button group. */
        binding.toggleButtonGroup.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            // Respond to button selection
            when (checkedId) {
                R.id.contacts -> {
                    Toast.makeText(requireContext(), "contacts", Toast.LENGTH_SHORT).show()
                }
                R.id.teammates -> {
                    Toast.makeText(requireContext(), "teammates", Toast.LENGTH_SHORT).show()
                }
                R.id.local -> {
                    Toast.makeText(requireContext(), "local", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}