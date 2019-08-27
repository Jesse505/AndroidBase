package com.Jesse.androidbasekotlin.Home.presenter

import com.Jesse.androidbasekotlin.Home.contract.HomeContract
import com.Jesse.androidbasekotlin.Home.model.HomeModel
import com.jesse.baselibs.base.BasePresenter

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private val mModel by lazy {
        HomeModel()
    }
}