package com.jesse.baselibs.base

import com.jesse.baselibs.mvp.IBaseView
import com.jesse.baselibs.mvp.IPresenter
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import java.lang.IllegalArgumentException

abstract class BasePresenter<V : IBaseView> : IPresenter<V> {

    var mView: V? = null
        private set

    override fun attachView(rootView: V) {
        this.mView = rootView
    }


    override fun detachView() {
        mView = null
    }

    fun <VT> bindToLifecycle(): LifecycleTransformer<VT> {
        return when (mView) {
            is RxFragment -> (mView as RxFragment).bindToLifecycle()
            is RxAppCompatActivity -> (mView as RxAppCompatActivity).bindToLifecycle()
            else -> throw IllegalArgumentException("class must extents RxAppCompatActivity or RxFragment")
        }
    }

    /**
     * 绑定 RxJava OnDestroy 生命周期，封装常用的即可
     * 优点：当Presenter对应的UI的类型(比如Activity或Fragment)切换时，不会崩溃
     * 缺点：需要对所有需要的声明周期，都需要进行封装
     */
    fun <VT> bindUntilOnDestroyEvent(): LifecycleTransformer<VT> {
        if (mView is RxFragment) {
            return bindFragmentUntilEvent(FragmentEvent.DESTROY)
        }
        return bindActivityUntilEvent(ActivityEvent.DESTROY)
    }

    /**
     * 绑定 RxActivity 指定生命周期
     * 有点：灵活
     * 缺点：当Presenter对应的UI的类型(比如Activity或Fragment)切换时，需要修改对应的Presenter
     */
    fun <VT> bindActivityUntilEvent(event: ActivityEvent): LifecycleTransformer<VT> {
        return when (mView) {
            is RxAppCompatActivity -> (mView as RxAppCompatActivity).bindUntilEvent(event)
            else -> throw IllegalArgumentException("class must extents RxAppCompatActivity")
        }
    }

    /**
     * 绑定 RxFragment 指定的声明周期
     */
    fun <VT> bindFragmentUntilEvent(event: FragmentEvent): LifecycleTransformer<VT> {
        return when (mView) {
            is RxAppCompatActivity -> (mView as RxFragment).bindUntilEvent(event)
            else -> throw IllegalArgumentException("class must extents RxFragment")
        }
    }


}