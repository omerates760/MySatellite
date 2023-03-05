package com.omerates.common.utils

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()

    fun toData(): T? = if (this is Success) this.data else null
}

