package com.example.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.usecases.GetTopUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModelJacob(private val getTopUsersUseCase: GetTopUsersUseCase) : BaseViewModel() {

    val usersLiveData: MutableLiveData<List<UserEntity>> = MutableLiveData()

    fun userClicksOnItem(user: UserEntity) {
        Log.e("userClicksOnItem", "${user.login}")
        navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                user.login
            )
        )
    }


   fun loadUsers() {
       GlobalScope.launch(Dispatchers.IO) {
           var data = getTopUsersUseCase.run()

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
//        getTopUsersUseCase.runCallback(object : CallBackI {
//            override fun onSuccess(data: Any) {
//                usersLiveData.value = data as List<UserResponse>
//            }
//
//            override fun onError() {
//                usersLiveData.value = null
//            }
//
//        })
    }
}