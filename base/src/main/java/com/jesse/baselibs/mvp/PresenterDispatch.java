package com.jesse.baselibs.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.jesse.baselibs.base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * create by lzx
 * time:2018/7/27
 */
public class PresenterDispatch {
    private PresenterProviders mProviders;

    public PresenterDispatch(PresenterProviders providers) {
        mProviders = providers;
    }

    public <P extends BasePresenter> void attachView(IBaseView view) {
        PresenterStore store = mProviders.getPresenterStore();
        HashMap<String, P> mMap = store.getMap();
        for (Map.Entry<String, P> entry : mMap.entrySet()) {
            P presenter = entry.getValue();
            if (presenter != null) {
                presenter.attachView(view);
            }
        }
    }

    public <P extends BasePresenter> void detachView() {
        PresenterStore store = mProviders.getPresenterStore();
        HashMap<String, P> mMap = store.getMap();
        for (Map.Entry<String, P> entry : mMap.entrySet()) {
            P presenter = entry.getValue();
            if (presenter != null) {
                presenter.detachView();
            }
        }
    }

}
