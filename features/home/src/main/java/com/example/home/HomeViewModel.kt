package com.example.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.home.domain.GetTopUsers
import com.example.model.views.User
import com.example.repository.AppDispatchers
import com.example.repository.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getTopUsers: GetTopUsers, private val appDispatchers: AppDispatchers) : BaseViewModel() {

    val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    var isLoading: ObservableBoolean = ObservableBoolean()

    fun userClicksOnItem(user: User) {
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
           var data = getTopUsers.run()
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
        getTopUsers.runCallback(object : CallBackI {
            override fun onSuccess(data: Any) {
                usersLiveData.value = data as List<User>
            }
            override fun onError() {
                usersLiveData.value = null
            }

        })
    }
}