package com.jewel.util.view.inject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

/**
 * Target为Activity时的注入实现
 * @author Jewel
 * @version 1.0
 * @since 2018/07/02
 */
class ActivityInject extends BaseInject{

    @Override
    public boolean inject(Object target, int layoutId) {
        if (!(target instanceof Activity)) return false;
        Activity activity = (Activity) target;

        FrameLayout rootView = getDecorView(activity);
        if(rootView == null) return false;

        View statusView = getCacheInjectView(getKey(target));

        if (statusView != null) {
            rootView.removeView(statusView);
        }

        View newStatusView = LayoutInflater.from(activity).inflate(layoutId, null);

        showContentView(activity, false);

        rootView.addView(newStatusView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        putCacheInjectView(getKey(target), newStatusView);
        return true;
    }

    @Override
    public boolean inject(Object target, @NonNull View injectView) {
        valid(injectView);

        if (!(target instanceof Activity)) return false;
        Activity activity = (Activity) target;

        FrameLayout rootView = getDecorView(activity);
        if (rootView == null) return false;

        final View statusView = getCacheInjectView(getKey(target));
        if (statusView != null) {
            rootView.removeView(statusView);
        }

        showContentView(activity, false);

        rootView.addView(injectView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        putCacheInjectView(getKey(target), injectView);
        return true;
    }

    @Override
    public boolean revert(Object target) {
        if (!(target instanceof Activity)) return false;
        Activity activity = (Activity) target;
        showContentView(activity, true);
        View statusView = getCacheInjectView(getKey(target));
        if (statusView == null) return false;

        FrameLayout rootView = getDecorView(activity);
        if (rootView == null) return false;

        rootView.removeView(statusView);

        removeCacheInjectView(getKey(target));
        return true;
    }

    @Override
    public void destroy(Object target) {
        removeCacheInjectView(getKey(target));
    }

    @Override
    public void destroy(Object... targets) {
        for (Object target : targets) {
            destroy(target);
        }
    }

    @Nullable
    private FrameLayout getDecorView(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) return null;
        Window window = activity.getWindow();
        if (window == null) return null;
        return (FrameLayout) window.getDecorView();
    }

    @Nullable
    private ViewGroup getContentView(Activity activity) {
        return activity.findViewById(android.R.id.content);
    }

    private void showContentView(Activity activity, boolean show) {
        View view = getContentView(activity);
        if(view != null) {
            view.setVisibility(show ? View.VISIBLE :View.GONE);
        }
    }
}
