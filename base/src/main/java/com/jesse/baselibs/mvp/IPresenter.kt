package com.jesse.baselibs.mvp

/**
 * Presentation的通用接口
 */
interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()
}