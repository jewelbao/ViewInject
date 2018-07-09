package com.jewel.util.view.inject;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 默认缓存策略
 *
 * @author Jewel
 * @version 1.0
 * @since 2018/07/02
 */
public class CacheStrategy implements ICacheStrategy {

    private Map<Object, View> cacheInjectViewMap;
    private Map<Object, View> cacheOriginViewMap;

    private static CacheStrategy INSTANCE;

    public static CacheStrategy getInstance() {
        if (INSTANCE == null) {
            synchronized (CacheStrategy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CacheStrategy();
                }
            }
        }
        return INSTANCE;
    }

    private CacheStrategy() {
        cacheInjectViewMap = new HashMap<>();
        cacheOriginViewMap = new HashMap<>();
    }

    @Override
    public void valid(View injectView) {
        if (injectView.getParent() != null) {
            Log.w(TAG, "The injectView already has a parent. " +
                    "InjectFramework will call removeView() on the child's parent first.");

            ViewGroup viewGroup = (ViewGroup) injectView.getParent();
            viewGroup.removeView(injectView);
        }

        Object key = getKeyByInjectView(injectView);
        Log.d(TAG, "Current injectView's key is " + key);
        if (key == null) return;
        Log.d(TAG, "remove injectView from cache");
        removeCacheInjectView(key);

        // 获取原View
        View originView = getOriginView(key);
        Log.d(TAG, "Current injectView's originView is " + originView);
        if (originView == null) return;
        originView.setVisibility(View.VISIBLE);

        Log.d(TAG, "remove originView from cache");
        removeOriginView(key);
    }

    @Override
    public View getCacheInjectView(Object key) {
        return cacheInjectViewMap.get(key);
    }

    @Override
    public void putCacheInjectView(Object key, View injectView) {
        cacheInjectViewMap.put(key, injectView);
    }

    @Override
    public void removeCacheInjectView(Object key) {
        cacheInjectViewMap.remove(key);
    }

    @Override
    public View getOriginView(Object key) {
        return cacheOriginViewMap.get(key);
    }

    @Override
    public void putOriginView(Object key, View originView) {
        cacheOriginViewMap.put(key, originView);
    }

    @Override
    public void removeOriginView(Object key) {
        cacheOriginViewMap.remove(key);
    }

    private Object getKeyByInjectView(View view) {
        Object key = null;
        Set<Map.Entry<Object, View>> sets = cacheInjectViewMap.entrySet();
        for (Map.Entry<Object, View> entry : sets) {
            if (view == entry.getValue()) {
                key = entry.getKey();
                break;
            }
        }
        return key;
    }
}
