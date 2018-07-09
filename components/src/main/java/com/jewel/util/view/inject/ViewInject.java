package com.jewel.util.view.inject;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Target为View时的注入实现类
 *
 * @author Jewel
 * @version 1.0
 * @since 2018/06/27
 */
class ViewInject extends BaseInject {

    @Override
    public boolean inject(Object target, int layoutId) {
        if (!(target instanceof View)) return false;
        View originView = (View) target;

        ViewGroup viewGroup = (ViewGroup) originView.getParent();
        if (viewGroup == null) return false;

        View injectView = LayoutInflater.from(originView.getContext()).inflate(layoutId, null);

        ViewGroup.LayoutParams params = originView.getLayoutParams();
        injectView.setLayoutParams(params);

        originView.setVisibility(View.GONE);

        viewGroup.addView(injectView, params);

        putCacheInjectView(getKey(target), injectView);
        putOriginView(getKey(target), originView);
        return true;
    }

    @Override
    public boolean inject(Object target, @NonNull View injectView) {
        valid(injectView);

        if (!(target instanceof View)) return false;
        View originView = (View) target;

        ViewGroup viewGroup = (ViewGroup) originView.getParent();
        if (viewGroup == null) return false;

        ViewGroup.LayoutParams params = originView.getLayoutParams();
        injectView.setLayoutParams(params);

        originView.setVisibility(View.GONE);

        viewGroup.addView(injectView, params);

        putCacheInjectView(getKey(target), injectView);
        putOriginView(getKey(target), originView);
        return true;
    }

    @Override
    public boolean revert(Object target) {
        if (!(target instanceof View)) return false;
        View originView = (View) target;
        originView.setVisibility(View.VISIBLE);

        View statusView = getCacheInjectView(getKey(target));
        if (statusView == null) return false;

        ViewGroup viewGroup = (ViewGroup) originView.getParent();
        if (viewGroup == null) return false;

        viewGroup.removeView(statusView);

        destroy(target);
        return true;
    }

    @Override
    public void destroy(Object target) {
        Object key = getKey(target);
        removeCacheInjectView(key);
        removeOriginView(key);
    }

    @Override
    public void destroy(Object... targets) {
        for (Object target : targets) {
            destroy(target);
        }
    }
}
