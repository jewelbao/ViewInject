package com.jewel.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jewel.util.view.inject.ViewInjectFactory;

/**
 * @author Jewel
 * @version 1.0
 * @since 2018/07/09
 */
public class AFragment extends Fragment {
    View button;
    View textView;
    View layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        button = view.findViewById(R.id.button);
        textView =  view.findViewById(R.id.textView);
        layout =  view.findViewById(R.id.layout);
        final View loadingView = getLayoutInflater().inflate(R.layout.layout_loading, null);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewInjectFactory.getInstance().inject(button, loadingView);
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewInjectFactory.getInstance().revert(button);
                    }
                }, 5000);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewInjectFactory.getInstance().inject(textView, R.layout.layout_loading);
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewInjectFactory.getInstance().revert(textView);
                    }
                }, 5000);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewInjectFactory.getInstance().inject(AFragment.this, loadingView);
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewInjectFactory.getInstance().revert(AFragment.this);
                    }
                }, 2000);
            }
        });
        layout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        return view;
    }

    @Override
    public void onDestroy() {
        ViewInjectFactory.getInstance().destroy(textView, button, this);
        super.onDestroy();
    }
}
