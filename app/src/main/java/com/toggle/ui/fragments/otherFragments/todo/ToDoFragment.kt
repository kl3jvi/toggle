package com.toggle.ui.fragments.otherFragments.todo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.callHistory
import com.toggle.databinding.ToDoFragmentBinding
import com.toggle.ui.activities.MainActivity
import com.toggle.utils.collectFlow
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ToDoFragment : Fragment(R.layout.to_do_fragment) {

    private val binding: ToDoFragmentBinding by viewBinding()
    private val viewModel: ToDoViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        collectFlow(viewModel.callHistory) {
            Log.e("s", it.toString())
            binding.recyclerView.withModels {
                it.forEach {
                    callHistory {
                        id(UUID.randomUUID().toString())
                        callData(it)
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