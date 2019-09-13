package com.example.detail

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.detail.domain.GetUserDetailCase
import com.example.model.views.User
import com.example.repository.AppDispatchers
import com.example.repository.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val getUserDetailCase: GetUserDetailCase, private val appDispatchers: AppDispatchers) : BaseViewModel() {
    val userDetail : MutableLiveData<User> = MutableLiveData()

    fun loadUserDetail(login: String) {
        GlobalScope.launch(appDispatchers.io) {
            var data = getUserDetailCase.run(login)
            withContext(appDispatchers.main) {
                when(data.status) {
                    Resource.Status.ERROR -> {
                        userDetail.value = null
                    }
                    Resource.Status.SUCCESS -> {
                        userDetail.value = data.data
                    }
                }
            }
        }
    }
}