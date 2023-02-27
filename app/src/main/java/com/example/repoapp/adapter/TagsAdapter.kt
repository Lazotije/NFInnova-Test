package com.example.repoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.repoapp.databinding.RepositoryTagListItemBinding
import com.example.repoapp.model.TagItem

internal class TagsAdapter :
    androidx.recyclerview.widget.ListAdapter<TagItem, TagsAdapter.TagsViewHolder>(TagsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TagsAdapter.TagsViewHolder(
            RepositoryTagListItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        bindTag(holder, getItem(position) as TagItem)
    }

    private fun bindTag(holder: TagsViewHolder, tag: TagItem) {
        holder.binding.apply {
            tvTagName.text = tag.name
            tvSha.text = tag.commit.sha
        }
    }

    internal class TagsViewHolder(val binding: RepositoryTagListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    internal object TagsDiffCallback : DiffUtil.ItemCallback<TagItem>() {
        override fun areItemsTheSame(oldItem: TagItem, newItem: TagItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TagItem, newItem: TagItem): Boolean {
            return oldItem == newItem
        }
    }


}