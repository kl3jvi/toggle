package com.toggle.ui.fragments.otherFragments.todo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.databinding.ToDoFragmentBinding
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoFragment : Fragment(R.layout.to_do_fragment) {

    private val binding: ToDoFragmentBinding by viewBinding()
    private val viewModel: ToDoViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}