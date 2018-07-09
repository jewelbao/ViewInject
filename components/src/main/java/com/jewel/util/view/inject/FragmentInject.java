package com.jewel.util.view.inject;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Target为Fragment时的注入实现
 * @author Jewel
 * @version 1.0
 * @since 2018/07/03
 */
class FragmentInject extends ViewInject {


    @Override
    public boolean inject(Object target, int layoutId) {
        if (!(target instanceof Fragment)) return false;
        Fragment fragment = (Fragment) target;

        View fragmentView = fragment.getView();
        return fragmentView != null && super.inject(fragmentView, layoutId);

    }

    @Override
    public boolean inject(Object target, @NonNull View injectView) {
        if (!(target instanceof Fragment)) return false;
        Fragment fragment = (Fragment) target;

        View fragmentView = fragment.getView();
        return fragmentView != null && super.inject(fragmentView, injectView);

    }

    @Override
    public boolean revert(Object target) {
        if (!(target instanceof Fragment)) return false;
        Fragment fragment = (Fragment) target;

        View fragmentView = fragment.getView();
        return fragmentView != null && super.revert(fragmentView);

    }

    @Override
    public void destroy(Object target) {
        if (!(target instanceof Fragment))return;
        Fragment fragment = (Fragment) target;

        View fragmentView = fragment.getView();
        super.destroy(fragmentView);
    }

    @Override
    public void destroy(Object... targets) {
        for (Object target : targets) {
            destroy(target);
        }
    }
}
