package com.tushar.horizontalcards.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.tushar.horizontalcards.base.BaseAdapter
import com.tushar.horizontalcards.data.remote.ClassModel
import com.tushar.horizontalcards.databinding.RowCourseBinding

class CourseAdapter() : BaseAdapter<ClassModel>(DIFF_CALLBACK) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = RowCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = CourseRowViewModel()
        mBinding.vm = viewModel
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        if (binding is RowCourseBinding) {
            binding.vm?.item?.set(getItem(position))
            binding.executePendingBindings()
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ClassModel>() {
            override fun areItemsTheSame(oldItem: ClassModel, newItem: ClassModel): Boolean = false

            override fun areContentsTheSame(oldItem: ClassModel, newItem: ClassModel): Boolean =
                oldItem == newItem
        }
    }
}