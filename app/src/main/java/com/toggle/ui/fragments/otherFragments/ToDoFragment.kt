package com.toggle.ui.fragments.otherFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.toggle.R
import com.toggle.data.model.SampleCall
import com.toggle.databinding.ToDoFragmentBinding
import com.toggle.ui.adapters.ToDoAdapter
import com.toggle.utils.viewBinding

class ToDoFragment : Fragment(R.layout.to_do_fragment) {

    private val viewModel: ToDoViewModel by viewModels()
    private val binding: ToDoFragmentBinding by viewBinding()
    private val toDoAdapter by lazy { ToDoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.apply {
            adapter = toDoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            toDoAdapter.submitList(
                listOf(
                    SampleCall("+355 694518882", "on Clay Telecom"),
                    SampleCall("+355 692193120", "on Clay Telecom"),
                    SampleCall("+355 693308642", "on Clay Telecom"),
                    SampleCall("+355 694518882", "on Clay Telecom"),
                )
            )
        }
    }
}