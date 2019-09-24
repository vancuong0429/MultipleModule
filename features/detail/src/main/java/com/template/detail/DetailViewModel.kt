package com.template.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.template.common.AppDispatchers
import com.template.common.BaseViewModel
import com.template.domain.entities.UserEntity
import com.template.domain.usecases.GetUserDetailUseCase
import com.template.domain.usecases.GetUserDetailUseCaseParams
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {
    val userDetail: MutableLiveData<UserEntity> = MutableLiveData()

    fun loadUserDetail(login: String) = viewModelScope.launch(appDispatchers.main) {
        val data = getUserDetailUseCase.execute(GetUserDetailUseCaseParams(login))
        data.either({
            userDetail.value = null
        }, { userEntity ->
            userDetail.value = userEntity
        })
    }
}