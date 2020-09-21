package com.example.pview.ui.list

import com.example.pview.R
import com.example.pview.ui.base.BaseActivity

class ListVideoActivity : BaseActivity<ListView, ListPresenterImp>(), ListView {
    override fun initView(): ListView {
        return this
    }

    override fun initPresenter(): ListPresenterImp {
        return ListPresenterImp(self)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list
    }

    override fun initWidgets() {
    }
}