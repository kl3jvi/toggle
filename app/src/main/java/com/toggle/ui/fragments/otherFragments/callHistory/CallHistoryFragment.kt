package com.toggle.ui.fragments.otherFragments.callHistory

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.databinding.DetailsFragmentBinding
import com.toggle.utils.viewBinding

class CallHistoryFragment : Fragment(R.layout.call_history_fragment) {

    private val viewModel: CallHistoryViewModel by viewModels()
    private val binding: DetailsFragmentBinding by viewBinding()

}