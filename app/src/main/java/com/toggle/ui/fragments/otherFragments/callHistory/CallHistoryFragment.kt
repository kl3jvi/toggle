package com.toggle.ui.fragments.otherFragments.callHistory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toggle.R
import com.toggle.callHistory
import com.toggle.databinding.CallHistoryFragmentBinding
import com.toggle.ui.activities.MainActivity
import com.toggle.utils.collectFlow
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CallHistoryFragment : Fragment(R.layout.call_history_fragment) {

    private val viewModel: CallHistoryViewModel by viewModels()
    private val binding: CallHistoryFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        collectFlow(viewModel.callHistory) { listOfCall ->
            binding.recyclerView.withModels {
                listOfCall.forEach {
                    callHistory {
                        id(UUID.randomUUID().toString())
                        callData(it)
                        clickListener { _ ->
                            val directions =
                                CallHistoryFragmentDirections.actionCallHistoryFragmentToDetailsFragment(
                                    it
                                )
                            findNavController().navigate(directions)
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavBar()
        }
    }
}


