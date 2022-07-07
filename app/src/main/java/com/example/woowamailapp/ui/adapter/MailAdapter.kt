package com.example.woowamailapp.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.woowamailapp.R
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
            return oldItem.title == newItem.title
        }
    }

}