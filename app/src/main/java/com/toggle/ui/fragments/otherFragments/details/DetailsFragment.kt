package com.toggle.ui.fragments.otherFragments.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maxkeppeler.sheets.options.DisplayMode
import com.maxkeppeler.sheets.options.Option
import com.maxkeppeler.sheets.options.OptionsSheet
import com.toggle.R
import com.toggle.databinding.DetailsFragmentBinding
import com.toggle.ui.activities.MainActivity
import com.toggle.utils.viewBinding

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by viewModels()
    private val binding: DetailsFragmentBinding by viewBinding()
    private val args: DetailsFragmentArgs by navArgs()
    private val callDetails get() = args.callHistory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener { findNavController().navigateUp() }
        binding.time.text = callDetails.callDate
        binding.detailsRv.withModels {
            buildDetails(callDetails, requireContext())
        }

        binding.more.setOnClickListener {
            OptionsSheet().show(requireContext()) {
                displayMode(DisplayMode.LIST)
                preventIconTint(true)
                with(
                    Option(R.drawable.bottom_sheet_first, "Notes", "Add notes for the numbers"),
                    Option(R.drawable.bottom_sheet_second, "Tags", "Add any tags to give identity"),
                    Option(
                        R.drawable.bottom_sheet_third,
                        "Assign",
                        "Assign number to any team members"
                    )
                )
                onPositive { index: Int, option: Option ->
                    // Handle selected option
                }
            }

        }
    }


    /**
     * It hides the bottom navigation bar when the fragment is resumed.
     */
    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavBar()
        }
    }


}