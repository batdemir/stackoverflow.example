package com.batdemir.stackexchange.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.batdemir.stackexchange.R
import com.batdemir.stackexchange.data.entities.db.UserModel
import com.batdemir.stackexchange.databinding.ItemUserBinding

class UserAdapter(private val itemListener: ItemListener) :
    PagingDataAdapter<UserModel, UserAdapter.UserViewHolder>(Companion) {

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemListener {
        fun onClick(model: UserModel)
    }

    companion object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean =
            oldItem.userId == newItem.userId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemUserBinding>(
                layoutInflater,
                R.layout.item_user,
                parent,
                false
            )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.binding.model = current
        holder.binding.root.setOnClickListener {
            itemListener.onClick(current!!)
        }
        holder.binding.executePendingBindings()
    }
}