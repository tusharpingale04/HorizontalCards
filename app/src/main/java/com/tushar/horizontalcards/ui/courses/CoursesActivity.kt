package com.tushar.horizontalcards.ui.courses

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tushar.horizontalcards.R
import com.tushar.horizontalcards.base.BaseActivity
import com.tushar.horizontalcards.data.repository.Resource
import com.tushar.horizontalcards.databinding.ActivityCoursesBinding
import com.tushar.horizontalcards.di.CoursesScope
import com.tushar.horizontalcards.utils.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CoursesActivity : BaseActivity<CoursesViewModel, ActivityCoursesBinding>() {

    @CoursesScope
    @Inject
    lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        mViewBinding.vm = mViewModel
        mViewBinding.lifecycleOwner = this
        getCourses()
        subscribeObservers()
        setUpListeners()
        attachAdapter()
    }

    private fun attachAdapter() {
        if(mViewBinding.rvCourses.adapter == null){
            mViewBinding.rvCourses.adapter = courseAdapter
        }
    }

    private fun setUpListeners() {
        mViewBinding.retryBtn.setOnClickListener {
            getCourses()
        }
    }

    private fun getCourses(){
        if(this.isInternetAvailable()){
            mViewModel.isApiError.value = false
            mViewModel.getCourses(isPremium = true, includeIndividual = true)
        }else{
            Toast.makeText(this, getString(R.string.error_no_connection), Toast.LENGTH_SHORT).show()
        }
    }

    private fun subscribeObservers() {
        mViewModel.coursesLiveData.observe(this, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> {
                    mViewModel.loadingText.value = getString(R.string.loading_please_wait)
                    mViewModel.isApiInProgress.value = true
                }
                Resource.Status.SUCCESS -> {
                    mViewModel.loadingText.value = ""
                    mViewModel.isApiInProgress.value = false
                    if(!resource.data?.data?.classes.isNullOrEmpty()){
                        courseAdapter.submitList(resource.data?.data?.classes)
                    }
                }
                Resource.Status.ERROR -> {
                    mViewModel.loadingText.value = resource.message
                    mViewModel.isApiInProgress.value = false
                    mViewModel.isApiError.value = true

                }
            }
        })
    }

    override val mViewModel: CoursesViewModel
        get() = ViewModelProvider(this).get(CoursesViewModel::class.java)

    override fun getViewBinding() = ActivityCoursesBinding.inflate(layoutInflater)
}