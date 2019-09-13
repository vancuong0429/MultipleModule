package com.example.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.home.domain.GetTopUsers
import com.example.model.views.User
import com.example.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModelJacob(private val getTopUsers: GetTopUsers) : BaseViewModel() {

    val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    fun userClicksOnItem(user: User) {
        Log.e("userClicksOnItem", "${user.login}")
        navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                user.login
            )
        )
    }


   fun loadUsers() {
       GlobalScope.launch(Dispatchers.IO) {
           var data = getTopUsers.run()

           withContext(Dispatchers.Main) {
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