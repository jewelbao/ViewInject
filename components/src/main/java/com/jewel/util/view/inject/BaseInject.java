package com.jewel.util.view.inject;

import android.view.View;

/**
 * @author Jewel
 * @version 1.0
 * @since 2018/07/02
 */
abstract class BaseInject implements IViewInject{

    private ICacheStrategy cacheStrategy;

    BaseInject() {
        cacheStrategy = CacheStrategy.getInstance();
    }

    Object getKey(Object object){
        return object.hashCode();
    }

    View getCacheInjectView(Object key) {
        Objects.requireNonNull(cacheStrategy);
        return cacheStrategy.getCacheInjectView(key);
    }

    void putCacheInjectView(Object key, View injectView) {
        Objects.requireNonNull(cacheStrategy);
        cacheStrategy.putCacheInjectView(key, injectView);
    }

    void removeCacheInjectView(Object key) {
        Objects.requireNonNull(cacheStrategy);
        cacheStrategy.removeCacheInjectView(key);
    }

    void putOriginView(Object key, View originView) {
        cacheStrategy.putOriginView(key, originView);
    }

    void removeOriginView(Object key) {
        cacheStrategy.removeOriginView(key);
    }

    void valid(View injectView) {
        cacheStrategy.valid(injectView);
    }
}
