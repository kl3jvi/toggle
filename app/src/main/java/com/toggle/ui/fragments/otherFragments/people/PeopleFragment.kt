package com.toggle.ui.fragments.otherFragments.people

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.callHistory
import com.toggle.cardDetails
import com.toggle.contacts
import com.toggle.databinding.PeopleFragmentBinding
import com.toggle.utils.collectFlow
import com.toggle.utils.randomId
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.people_fragment) {

    private val viewModel: PeopleViewModel by viewModels()
    private val binding: PeopleFragmentBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* A listener for the toggle button group. */
        binding.toggleButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            // Respond to button selection
            when (checkedId) {
                R.id.contacts -> {
                    collectFlow(viewModel.contactDetails) { list ->
                        binding.list.withModels {
                            list.forEach {
                                contacts {
                                    id(randomId())
                                    contactDetails(it)

                                }
                            }
                        }
                    }
                }
                R.id.teammates -> {
                    collectFlow(viewModel.test) { list ->
                        binding.list.withModels {
                            list.forEach {
                                cardDetails {
                                    id(randomId())
                                    number(it.toString())
                                    drawable(R.drawable.ic_people)
                                    background(R.color.primaryColor)
                                    isVisible(false)
                                }
                            }
                        }
                    }
                }
                R.id.local -> {
                    Toast.makeText(requireContext(), "local", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}