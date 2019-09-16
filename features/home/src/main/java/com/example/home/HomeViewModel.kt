package com.example.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.AppDispatchers
import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.usecases.GetTopUsersUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getTopUsersUseCase: GetTopUsersUseCase, private val appDispatchers: AppDispatchers) : BaseViewModel() {

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

    fun loadUsers() {
       GlobalScope.launch(appDispatchers.io) {
           var data = getTopUsersUseCase.run()
           withContext(appDispatchers.main) {
               isLoading.set(false)
               when(data.status) {
                   Resource.Status.ERROR -> {
                       usersLiveData.value = null
                   }
                   Resource.Status.SUCCESS -> {
                       usersLiveData.value = data.data
                   }
               }
           }
       }
   }

    fun loadUsersCallback() {
//        getTopUsersUseCase.runCallback(object : CallBackI {
//            override fun onSuccess(data: Any) {
//                usersLiveData.value = data as List<UserResponse>
//            }
//            override fun onError() {
//                usersLiveData.value = null
//            }
//
//        })
    }
}