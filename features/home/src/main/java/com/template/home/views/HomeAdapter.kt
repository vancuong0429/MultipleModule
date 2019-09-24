package com.template.home.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.template.domain.entities.UserEntity
import com.template.home.HomeViewModel
import com.template.home.R
import com.template.home.databinding.ItemHomeBinding

class HomeAdapter(private val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeViewHolder>() {
    private val users: MutableList<UserEntity> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_home,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bindTo(users[position], viewModel)

    fun updateData(items: List<UserEntity>) {
        val diffCallback = HomeItemDiffCallback(users, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        users.clear()
        users.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

}

class HomeViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    private val binding = ItemHomeBinding.bind(parent)

    fun bindTo(user: UserEntity, viewModel: HomeViewModel) {
        binding.user = user
        binding.viewmodel = viewModel
    }
}