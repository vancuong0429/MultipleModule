package com.example.model.views

import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("total_count")
    var totalCount: Int = 0,

    @SerializedName("items")
    var data: T? = null
)