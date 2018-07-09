package com.jewel.util.view.inject;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Jewel
 * @version 1.0
 * @since 2018/07/02
 */
public interface IViewInject {

    /**
     * 注入布局，如果target为Activity，则会替换ContentView;<br>
     * 如果target为View，则会替换当前的target;<br>
     * 如果target为Fragment，则会替换Fragment RootView.<br>
     *
     * @param target   Activity 或者 View 或者 Fragment
     * @param layoutId 替换target的布局
     * @return {@code true} 成功展示layout布局
     */
    boolean inject(Object target, @LayoutRes int layoutId);

    /**
     * 注入View，如果target为Activity，则会替换ContentView;<br>
     * 如果target为View，则会替换当前的target;<br>
     * 如果target为Fragment，则会替换Fragment RootView.<br>
     *
     * @param target     Activity 或者 View 或者 Fragment
     * @param injectView 替换target的iew
     * @return {@code true} 成功展示injectView
     */
    boolean inject(Object target, @NonNull View injectView);

    /**
     * 如果target上有注入的View，则移除该View，重新展示target
     *
     * @param target Activity 或者 View
     * @return {@code true} 成功展示target
     */
    boolean revert(Object target);

    /**
     * 销毁注入的View.
     * @param target Activity 或者 View 或者 Fragment
     */
    void destroy(Object target);

    /**
     * 销毁注入的View.
     * @param targets Activity 或者 View 或者 Fragment
     */
    void destroy(Object... targets);
}
