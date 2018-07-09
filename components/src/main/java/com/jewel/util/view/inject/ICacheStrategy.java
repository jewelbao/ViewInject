package com.jewel.util.view.inject;

import android.app.Activity;
import android.view.View;

/**
 * 缓存策略
 *
 * @author Jewel
 * @version 1.0
 * @since 2018/07/02
 */
interface ICacheStrategy {

   String TAG = "Inject";

    /**
     * 注入View之前的验证，主要来判断该View是否已注入过。
     * @param injectView 要注入的View
     */
    void valid(View injectView);

    /**
     * 根据Key从缓存中获取注入的View
     *
     * @param key 如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link android.app.Activity}，则key为{@link Activity#hashCode()};<br>
     *            如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link View}，则key为{@link View#hashCode()}
     * @return {@link View}
     */
    View getCacheInjectView(Object key);

    /**
     * 以key-value的形式保存注入的View
     *
     * @param key        如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link android.app.Activity}，则key为{@link Activity#hashCode()};<br>
     *                   如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link View}，则key为{@link View#hashCode()}
     * @param injectView {@link View}
     */
    void putCacheInjectView(Object key, View injectView);

    /**
     * 根据Key从缓存中移除注入的View
     *
     * @param key 如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link android.app.Activity}，则key为{@link Activity#hashCode()};<br>
     *            如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link View}，则key为{@link View#hashCode()}
     */
    void removeCacheInjectView(Object key);


    /**
     * 根据Key从缓存中获取原有的View
     *
     * @param key 如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link android.app.Activity}，则key为{@link Activity#hashCode()};<br>
     *            如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link View}，则key为{@link View#hashCode()}
     * @return {@link View}
     */
    View getOriginView(Object key);

    /**
     * 以key-value的形式保存原有的View
     *
     * @param key        如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link android.app.Activity}，则key为{@link Activity#hashCode()};<br>
     *                   如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link View}，则key为{@link View#hashCode()}
     * @param originView {@link View}
     */
    void putOriginView(Object key, View originView);

    /**
     * 根据Key从缓存中移除原有的View
     *
     * @param key 如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link android.app.Activity}，则key为{@link Activity#hashCode()};<br>
     *            如果{@link IViewInject#inject(Object, int)}中的{@link Object}为{@link View}，则key为{@link View#hashCode()}
     */
    void removeOriginView(Object key);
}
