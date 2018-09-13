package com.sixfingers.botalov.gg.Base.View;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

@SuppressLint("Registered")
public class BaseFullActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
