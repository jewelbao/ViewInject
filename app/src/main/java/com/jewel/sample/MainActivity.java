package com.jewel.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jewel.util.view.inject.ViewInjectFactory;

public class MainActivity extends AppCompatActivity {
    View button;
    View textView;
    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        layout = findViewById(R.id.layout);
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
                ViewInjectFactory.getInstance().inject(MainActivity.this, loadingView);
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewInjectFactory.getInstance().revert(MainActivity.this);
                        gotoFragment();
                    }
                }, 2000);
            }
        });
    }

    private void gotoFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.layout, new AFragment())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(MainActivity.class.getSimpleName(), "Activity hashCode : " + MainActivity.this.hashCode());
        ViewInjectFactory.getInstance().revert(MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        ViewInjectFactory.getInstance().destroy(textView, button, this);
        super.onDestroy();
    }
}
