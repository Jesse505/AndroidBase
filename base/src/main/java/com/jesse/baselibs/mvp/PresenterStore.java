package com.jesse.baselibs.mvp;

import com.jesse.baselibs.base.BasePresenter;

import java.util.HashMap;

/**
 * create by lzx
 * time:2018/7/26
 */
public class PresenterStore<P extends BasePresenter> {

    private static final String DEFAULT_KEY = "PresenterStore.DefaultKey";
    private  HashMap<String, P> mMap = new HashMap<>();

    public final void put(String key, P presenter) {
        mMap.put(DEFAULT_KEY + ":" + key, presenter);
    }

    public final P get(String key) {
        return mMap.get(DEFAULT_KEY + ":" + key);
    }


    public int getSize() {
        return mMap.size();
    }

    public HashMap<String, P> getMap() {
        return mMap;
    }
}
