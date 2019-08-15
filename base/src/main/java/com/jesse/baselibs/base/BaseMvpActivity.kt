package com.jesse.baselibs.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.jesse.baselibs.mvp.IBaseView
import com.jesse.baselibs.mvp.IModel
import com.jesse.baselibs.mvp.PresenterDispatch
import com.jesse.baselibs.mvp.PresenterProviders
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseMvpActivity<P : BasePresenter<IModel, IBaseView>> : RxAppCompatActivity(), IBaseView {

    private val mPresenterProviders by lazy {
        PresenterProviders.inject(this)
    }

    private val mPresenterDispatch by lazy {
        PresenterDispatch(mPresenterProviders)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPresenterDispatch.attachView<BasePresenter<IModel, IBaseView>>(this)
        init(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenterDispatch.detachView<BasePresenter<IModel, IBaseView>>()
    }

    protected fun getPresenter(): P {
        return mPresenterProviders.getPresenter(0)
    }

    fun getPresenterProviders(): PresenterProviders {
        return mPresenterProviders
    }

    /**
     * 获取RootLayout布局Id
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化
     */
    abstract fun init(savedInstanceState: Bundle?)

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

}