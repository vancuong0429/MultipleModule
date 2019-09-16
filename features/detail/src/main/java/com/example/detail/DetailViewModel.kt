package com.example.detail

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.AppDispatchers
import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.usecases.GetUserDetailUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val getUserDetailUseCase: GetUserDetailUseCase, private val appDispatchers: AppDispatchers) : BaseViewModel() {
    val userDetail : MutableLiveData<UserEntity> = MutableLiveData()

    fun loadUserDetail(login: String) {
        GlobalScope.launch(appDispatchers.io) {
            var data = getUserDetailUseCase.run(login)
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