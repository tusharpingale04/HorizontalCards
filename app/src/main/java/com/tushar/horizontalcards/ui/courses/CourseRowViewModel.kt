package com.tushar.horizontalcards.ui.courses

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.tushar.horizontalcards.data.remote.ClassModel

/** 
 * CourseRowViewModel View model for row item of CourseAdapter inherits [ViewModel] 
 */
class CourseRowViewModel : ViewModel(){
    val item = ObservableField<ClassModel>()
}