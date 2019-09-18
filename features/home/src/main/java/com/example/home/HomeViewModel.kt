package com.example.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.AppDispatchers
import com.example.common.BaseViewModel
import com.example.domain.entities.UserEntity
import com.example.domain.usecases.GetTopUsersUseCase
import com.example.domain.usecases.base.UseCaseParams
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getTopUsersUseCase: GetTopUsersUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    val usersLiveData: MutableLiveData<List<UserEntity>> = MutableLiveData()

    var isLoading: ObservableBoolean = ObservableBoolean()

    fun userClicksOnItem(user: UserEntity) {
        navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                user.login
            )
        )
    }

    fun userRefreshesItems() {
        isLoading.set(true)
        loadUsers()
    }

    fun loadUsers() = viewModelScope.launch(appDispatchers.main) {
        val getUserResult = getTopUsersUseCase.execute(UseCaseParams.Empty)

        isLoading.set(false)
        getUserResult.either({ failure ->
            usersLiveData.value = null
        }, { userEntities ->
            usersLiveData.value = userEntities
        })
    }
}