package com.example.avinash.myprogressbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.avinash.aloader.aLoader;
import com.avinash.aloader.aLoader.Orientation;
public class MainActivity extends AppCompatActivity {
    aLoader aLoader;
    View relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.rootView);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbTarget:
                if (checked)
                    if (aLoader != null) {
                        if (aLoader.isShown()) {
                            stop(view);
                        }
                    }
                targetView();
                break;
            case R.id.rbDialog:
                if (checked)
                    if (aLoader != null) {
                        if (aLoader.isShown()) {
                            stop(view);
                        }
                    }
                dialogView();
                break;
        }
    }

    public void targetView() {
        aLoader = new aLoader().setTargetView(relativeLayout, MainActivity.this, Orientation.BOTTOM);
    }

    public void dialogView() {
        aLoader = new aLoader().setDialog(this).setProgressBarColor(Color.WHITE).message("Loading...", Color.WHITE, com.avinash.aloader.aLoader.TextSize.SMALL).setBackgroundView(ContextCompat.getDrawable(this, R.drawable.backgroundv1));
    }

    public void start(View view) {
        if (aLoader != null)
            aLoader.start();
    }

    public void stop(View view) {
        if (aLoader != null)
            aLoader.stop();
    }

    public void color(View view) {
        if (aLoader != null)
            aLoader.setProgressBarColor(Color.WHITE);
    }

    public void message(View view) {
        if (aLoader != null)
            aLoader.message("Loading...", Color.WHITE, com.avinash.aloader.aLoader.TextSize.SMALL);
    }

    public void background(View view) {
        if (aLoader != null)
            aLoader.setBackgroundView(ContextCompat.getDrawable(this, R.drawable.backgroundv2));
    }

    public void messageAnimation(View view) {
        if (aLoader != null)
            aLoader.setMessageAnimation(R.anim.animation);
    }

    @Override
    public void onBackPressed() {
        if (aLoader != null) {
            if (aLoader.isShown()) {
                aLoader.stop();
            }
        }
    }
}
