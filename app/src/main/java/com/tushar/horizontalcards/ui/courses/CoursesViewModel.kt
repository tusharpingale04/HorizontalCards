package com.tushar.horizontalcards.ui.courses

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.horizontalcards.data.remote.CoursesResponseModel
import com.tushar.horizontalcards.data.repository.CoursesRepository
import com.tushar.horizontalcards.data.repository.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/** 
 * CourseRowViewModel View model for [CoursesActivity] inherits [ViewModel] 
 */
@ExperimentalCoroutinesApi
class CoursesViewModel @ViewModelInject constructor(
    private val repository: CoursesRepository
    ): ViewModel(){

    val isApiInProgress = MutableLiveData(false)
    val isApiError = MutableLiveData(false)
    val loadingText = MutableLiveData("")

    private val _coursesLiveData = MutableLiveData<Resource<CoursesResponseModel>>()
    val coursesLiveData: LiveData<Resource<CoursesResponseModel>>
        get() = _coursesLiveData

    /** 
     * @param isPremium query params passed as part of API 
     * @param includeIndividual query params passed as part of API 
     */
    fun getCourses(isPremium: Boolean, includeIndividual: Boolean){
        viewModelScope.launch {
            repository.getCourses(isPremium, includeIndividual).collect {
                _coursesLiveData.value = it
            }
        }
    }

}