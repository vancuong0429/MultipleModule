package com.example.multiplemodule.di

import com.example.detail.di.createDetailModule
import com.example.home.di.createHomeModule
import com.example.local.di.localModule
import com.example.remote.di.createRemoteModule

val appComponent= listOf(createRemoteModule("https://api.github.com/"),
    createHomeModule, createDetailModule, localModule)
