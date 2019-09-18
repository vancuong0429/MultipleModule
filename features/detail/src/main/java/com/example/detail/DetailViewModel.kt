package com.example.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.BaseViewModel
import com.example.common.AppDispatchers
import com.example.domain.entities.UserEntity
import com.example.domain.usecases.GetUserDetailUseCase
import com.example.domain.usecases.GetUserDetailUseCaseParams
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val getUserDetailUseCase: GetUserDetailUseCase, private val appDispatchers: AppDispatchers) : BaseViewModel() {
    val userDetail : MutableLiveData<UserEntity> = MutableLiveData()

    fun loadUserDetail(login: String) {
        viewModelScope.launch(appDispatchers.main) {
            val data = getUserDetailUseCase.execute(GetUserDetailUseCaseParams(login))
            data.either({
                userDetail.value = null
            },{  userEntity ->
                userDetail.value = userEntity
            })
        }
    }
}