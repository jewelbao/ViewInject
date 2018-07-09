package com.jewel.util.view.inject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @author Jewel
 * @version 1.0
 * @since 2018/07/02
 */
public class ViewInjectFactory implements IViewInject {

    private static ViewInjectFactory INSTANCE;

    private final IViewInject activityInject;
    private final IViewInject fragmentInject;
    private final IViewInject viewInject;

    public static ViewInjectFactory getInstance() {
        if(INSTANCE == null) {
            synchronized (ViewInjectFactory.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ViewInjectFactory();
                }
            }
        }
        return INSTANCE;
    }

    private ViewInjectFactory() {
        this.activityInject = new ActivityInject();
        this.fragmentInject = new FragmentInject();
        this.viewInject = new ViewInject();
    }

    @Override
    public boolean inject(Object target, int layoutId) {
        if (target instanceof Activity) {
            return activityInject.inject(target, layoutId);
        } else if (target instanceof Fragment) {
            return fragmentInject.inject(target, layoutId);
        } else if (target instanceof View) {
            return viewInject.inject(target, layoutId);
        }
        return false;
    }

    @Override
    public boolean inject(Object target, @NonNull View injectView) {
        if (target instanceof Activity) {
            return activityInject.inject(target, injectView);
        } else if (target instanceof Fragment) {
            return fragmentInject.inject(target, injectView);
        } else if (target instanceof View) {
            return viewInject.inject(target, injectView);
        }
        return false;
    }

    @Override
    public boolean revert(Object target) {
        if (target instanceof Activity) {
            return activityInject.revert(target);
        } else if (target instanceof Fragment) {
            return fragmentInject.revert(target);
        } else if (target instanceof View) {
            return viewInject.revert(target);
        }
        return false;
    }

    @Override
    public void destroy(Object target) {
        if (target instanceof Activity) {
            activityInject.destroy(target);
        } else if (target instanceof Fragment) {
            fragmentInject.destroy(target);
        } else if (target instanceof View) {
            viewInject.destroy(target);
        }
    }

    @Override
    public void destroy(Object... targets) {
        for(Object target : targets) {
            destroy(target);
        }
    }
}
