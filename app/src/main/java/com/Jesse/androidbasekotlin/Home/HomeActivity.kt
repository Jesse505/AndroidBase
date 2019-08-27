package com.Jesse.androidbasekotlin.Home

import android.os.Bundle
import com.Jesse.androidbasekotlin.Home.contract.HomeContract
import com.Jesse.androidbasekotlin.Home.presenter.HomePresenter
import com.Jesse.androidbasekotlin.R
import com.jesse.baselibs.base.BaseMvpActivity
import com.jesse.baselibs.mvp.CreatePresenter
import com.jesse.baselibs.mvp.PresenterVariable

@CreatePresenter(presenter = [HomePresenter::class])
class HomeActivity : BaseMvpActivity(), HomeContract.View {

    @PresenterVariable
    internal var mPresenter: HomePresenter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun init(savedInstanceState: Bundle?) {

    }
}
