package com.jesse.baselibs.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesse.baselibs.mvp.IBaseView
import com.jesse.baselibs.mvp.IModel
import com.jesse.baselibs.mvp.PresenterDispatch
import com.jesse.baselibs.mvp.PresenterProviders
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class BaseMvpFragment<P : BasePresenter<IModel, IBaseView>> : RxFragment(), IBaseView {

    private var mContentView: View? = null

    private val mPresenterProviders by lazy {
        PresenterProviders.inject(this)
    }

    private val mPresenterDispatch by lazy {
        PresenterDispatch(mPresenterProviders)
    }

    /**
     * 创建View
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutId(), container, false)
        } else {
            mContentView!!.parent?.let {
                (it as ViewGroup).removeView(mContentView)
            }
        }
        return mContentView!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenterDispatch.attachView<BasePresenter<IModel, IBaseView>>(this)
        init(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
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