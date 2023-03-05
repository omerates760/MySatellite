package com.omerates.home

sealed class HomeEvent {
    data class QueryChangeEvent(val newQuery: String) : HomeEvent()
    object ExecuteSearchEvent : HomeEvent()
}