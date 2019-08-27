package com.jesse.baselibs.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.ConcurrentHashMap

/**
 * @Description: 基于Rxjava实现的事件总线操作类
 * created by Jesse at 2019-08-27
 */
object RxBus {

    private val bus: Subject<Any> = PublishSubject.create<Any>().toSerialized()

    private val mStickyEventMap = ConcurrentHashMap<Class<*>, Any>()

    /**
     * 发送事件
     */
    fun post(obj: Any) {
        bus.onNext(obj)
    }

    /**
     * 在调用线程中订阅
     */
    fun <T> toObservable(tClass: Class<T>): Observable<T> {
        return bus.ofType(tClass)
    }

    /**
     * 在主线程中订阅
     */
    fun <T> toObservableOnMain(tClass: Class<T>): Observable<T> {
        return bus.ofType(tClass).observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 发送粘性事件
     */
    fun postSticky(obj: Any) {
        synchronized(mStickyEventMap) {
            mStickyEventMap.put(obj.javaClass, obj)
        }
        post(obj)
    }

    fun <T> toObservableSticky(tClass: Class<T>): Observable<T> {
        synchronized(mStickyEventMap) {
            val observable = bus.ofType(tClass)
            val event = mStickyEventMap[tClass]
            return if (event != null) {
                Observable.merge<T>(observable, Observable.create<T> { emitter -> emitter.onNext(tClass.cast(event)) })
            } else {
                observable
            }
        }
    }

    fun <T> toObservableStickyOnMain(tClass: Class<T>): Observable<T> {
        synchronized(mStickyEventMap) {
            val observable = bus.ofType(tClass).observeOn(AndroidSchedulers.mainThread())
            val event = mStickyEventMap[tClass]
            return if (event != null) {
                Observable.merge<T>(observable, Observable.create<T> { emitter -> emitter.onNext(tClass.cast(event)) })
                        .observeOn(AndroidSchedulers.mainThread())
            } else {
                observable
            }
        }
    }

    /**
     * 是否存在订阅者
     */
    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }

    /**
     * 根据eventType获取Sticky事件
     */
    fun <T> getStickyEvent(eventType: Class<T>): T {
        synchronized(mStickyEventMap) {
            return eventType.cast(mStickyEventMap[eventType])
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     */
    fun <T> removeStickyEvent(eventType: Class<T>): T {
        synchronized(mStickyEventMap) {
            return eventType.cast(mStickyEventMap.remove(eventType))
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    fun removeAllStickyEvents() {
        synchronized(mStickyEventMap) {
            mStickyEventMap.clear()
        }
    }


}