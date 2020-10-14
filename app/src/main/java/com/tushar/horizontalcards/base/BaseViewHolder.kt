package com.tushar.horizontalcards.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseViewHolder class accepts [ViewDataBinding] which is our row layout binding
 */
open class BaseViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)