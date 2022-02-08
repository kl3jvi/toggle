package com.toggle.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toggle.data.model.SampleCall
import com.toggle.databinding.ItemToDoBinding

class ToDoAdapter() :
    ListAdapter<SampleCall, ToDoAdapter.ViewHolder>(MainDiffUtil<SampleCall>()) {
    inner class ViewHolder(val binding: ItemToDoBinding) : RecyclerView.ViewHolder(binding.root) {



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