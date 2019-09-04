package com.example.cattestproject.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.devbyteviewer.domain.Cat
import com.example.instacat.databinding.GridViewItemBinding


class CatGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Cat, CatGridAdapter.CatViewHolder>(DiffCallback) {

    class CatViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            binding.cat = cat
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatViewHolder {
        return CatViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(cat)
        }
        holder.bind(cat)
    }

    class OnClickListener(val clickListener: (cat: Cat) -> Unit) {
        fun onClick(cat: Cat) = clickListener(cat)
    }
}