package com.Jesse.androidbasekotlin.Home.contract

import com.jesse.baselibs.mvp.IBaseView
import com.jesse.baselibs.mvp.IPresenter

class HomeContract {

    interface View : IBaseView {

    }

    interface Presenter : IPresenter<View> {

    }

}