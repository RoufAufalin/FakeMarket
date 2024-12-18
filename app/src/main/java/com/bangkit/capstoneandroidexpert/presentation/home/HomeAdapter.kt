package com.bangkit.capstoneandroidexpert.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstoneandroidexpert.R
import com.bangkit.capstoneandroidexpert.databinding.ItemHomeBinding
import com.bangkit.core.domain.model.Product
import com.bumptech.glide.Glide

class HomeAdapter : ListAdapter<Product, HomeAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Product) -> Unit)? = null

    inner class ListViewHolder(private val binding: ItemHomeBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: Product) {
                    Glide.with(itemView.context)
                        .load(data.image)
                        .into(binding.imageProduct)

                    binding.tvProductTitle.text = data.title
                    binding.tvPrice.text = String.format(binding.root.context.getString(R.string.dollar_value), data.price)
                }

        init {
            itemView.setOnClickListener{
                onItemClick?.invoke(getItem(adapterPosition))

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Product> =
            object : DiffUtil.ItemCallback<Product>() {
                override fun areItemsTheSame(
                    oldItem: Product,
                    newItem: Product
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Product,
                    newItem: Product,
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}