package com.tushar.horizontalcards.ui.courses

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.tushar.horizontalcards.data.remote.ClassModel

class CourseRowViewModel : ViewModel(){
    val item = ObservableField<ClassModel>()
}