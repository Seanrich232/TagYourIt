package com.example.tagyourit.utils

interface ViewType {
    fun getViewType(): TYPE
}

enum class TYPE {
    LOADER,
    ITEM
}