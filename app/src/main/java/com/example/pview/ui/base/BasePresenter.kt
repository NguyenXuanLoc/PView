package com.example.pview.ui.base

abstract class BasePresenter<T : BaseView>() {

    abstract fun attachView(view: T)

    abstract fun detachView()
}