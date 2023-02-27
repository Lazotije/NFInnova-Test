package com.example.repoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repoapp.databinding.RepositoryListItemBinding
import com.example.repoapp.model.UserRepo

internal class ReposAdapter(private val onItemClicked: (position: Int) -> Unit) :
    ListAdapter<UserRepo, ReposAdapter.ReposViewHolder>(ReposDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReposViewHolder(
            RepositoryListItemBinding.inflate(inflater, parent, false), onItemClicked
        )
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        bindRepo(holder, getItem(position) as UserRepo)
    }

    private fun bindRepo(holder: ReposViewHolder, repository: UserRepo) {
        holder.binding.apply {
            label.text = repository.name
            numOpenIssues.text = repository.open_issues.toString()
        }
    }


    internal class ReposViewHolder(
        val binding: RepositoryListItemBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    internal object ReposDiffCallback : DiffUtil.ItemCallback<UserRepo>() {
        override fun areItemsTheSame(oldItem: UserRepo, newItem: UserRepo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserRepo, newItem: UserRepo): Boolean {
            return oldItem == newItem
        }
    }
}