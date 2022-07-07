package com.example.woowamailapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.woowamailapp.databinding.ItemMailBinding
import com.example.woowamailapp.model.Mail

class MailAdapter : ListAdapter<Mail,MailAdapter.MailViewHolder>(MenuDiffCallback()){
    inner class MailViewHolder(private val binding : ItemMailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Mail){
            binding.apply {
                this.mail = item
                executePendingBindings()
            }
        }
    }
    private class MenuDiffCallback : DiffUtil.ItemCallback<Mail>() {

        override fun areItemsTheSame(
            oldItem: Mail,
            newItem: Mail
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Mail,
            newItem: Mail
        ): Boolean {
            return oldItem.content == newItem.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        return MailViewHolder(
            ItemMailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        val mail = getItem(position)
        holder.bind(mail)
    }
}