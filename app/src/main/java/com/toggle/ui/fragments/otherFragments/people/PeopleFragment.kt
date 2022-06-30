package com.toggle.ui.fragments.otherFragments.people

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
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
        fillContacts()
        binding.toggleButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            Log.e("Error", "$group, $checkedId, $isChecked")
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
                    collectFlow(viewModel.teamMates) { list ->
                        binding.list.withModels {
                            list.forEach {
                                cardDetails {
                                    id(randomId())
                                    number(it.teamName)
                                    image(it.teamName.avatarUrl().replace("+", ""))
                                    background(R.color.primaryColor)
                                    details(it.teamID.toString())
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


    private fun fillContacts() {
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
}

private fun String.avatarUrl(): String {
    return "https://ui-avatars.com/api/?background=random&format=svg&bold=true&name=$this"
}