package com.eman.paginationapp.presentation.ui.paginate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eman.paginationapp.databinding.ItemLayoutBinding
import com.eman.paginationapp.domain.models.User

class UserPagerAdapter : PagingDataAdapter<User, UserPagerAdapter.UserViewHolder>(UserComparator) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)!!
        holder.view.textViewUserName.text = user.name
        Glide.with(holder.itemView.context).load(user.logo).into(holder.view.imageViewAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    class UserViewHolder(val view: ItemLayoutBinding): RecyclerView.ViewHolder(view.root) {

    }

    object UserComparator: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}