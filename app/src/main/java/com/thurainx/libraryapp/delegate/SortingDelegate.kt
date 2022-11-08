package com.thurainx.libraryapp.delegate

import com.thurainx.libraryapp.utils.SortType

interface SortingDelegate {
    fun onTapSort(sortType: SortType)
}