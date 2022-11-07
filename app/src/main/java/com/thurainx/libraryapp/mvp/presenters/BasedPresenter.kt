package com.thurainx.libraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface BasedPresenter{
    fun onUiReady(owner: LifecycleOwner)
}