package com.toggle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toggle.data.model.SampleCall
import com.toggle.databinding.ItemToDoBinding
import com.toggle.ui.fragments.otherFragments.todo.ToDoFragmentDirections

class ToDoAdapter() :
    ListAdapter<SampleCall, ToDoAdapter.ViewHolder>(MainDiffUtil<SampleCall>()) {
    inner class ViewHolder(val binding: ItemToDoBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.callData?.let { callDetails ->
                    navigateToDetails(
                        callDetails,
                        view
                    )
                }
            }
        }

        private fun navigateToDetails(callDetails: SampleCall, view: View) {
            val directions = ToDoFragmentDirections.actionToDoFragmentToDetailsFragment()
            view.findNavController().navigate(directions)
        }


        fun bindItem(sampleCall: SampleCall) {
            binding.callData = sampleCall
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemToDoBinding =
            ItemToDoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(getItem(position))
}