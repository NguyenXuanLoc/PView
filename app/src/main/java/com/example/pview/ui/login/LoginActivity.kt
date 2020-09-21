package com.example.pview.ui.login

import com.example.pview.R
import com.example.pview.common.Constant
import com.example.pview.common.ext.openWebView
import com.example.pview.common.ext.settings
import com.example.pview.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity<LoginView, LoginPresenterImp>(), LoginView {
    override fun initView(): LoginView {
        return this
    }

    override fun initPresenter(): LoginPresenterImp {
        return LoginPresenterImp(self)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initWidgets() {
        wvContent.settings()
        wvContent.openWebView(Constant.URL_ON_MOBI)
    }
}