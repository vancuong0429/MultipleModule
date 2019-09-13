package com.example.home.domain

import com.example.home.CallBackI
import com.example.model.views.User
import com.example.repository.HomeRepository
import com.example.repository.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetTopUsers(private val homeRepository: HomeRepository): UseCase<List<User>>() {
    override suspend fun run(): Resource<List<User>> {
        return homeRepository.getTopUsers()
    }

    override fun runCallback(callBackI: CallBackI) {
        GlobalScope.launch {
            val data: Resource<List<User>> = homeRepository.getTopUsers()
            when(data.status) {
                Resource.Status.SUCCESS -> callBackI.onSuccess(data)
                Resource.Status.ERROR -> callBackI.onError()
            }

        }
    }
//    override suspend fun run(fore: Boolean): Resource<List<User>> = homeRepository.getTopUsers(fore)


}