package com.avinash.aloader;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by avinash on 3/5/18.
 */

public class aLoader {
    // UI
    private ProgressBar aProgressBar;
    private TextView aTextMessage;
    private LinearLayout progressView;
    private LoaderDialog loaderDialog;
    private Activity activity;

    public enum TextSize {
        SMALL, MEDIUM, LARGE
    }

    public  enum Orientation {
        TOP, CENTER, BOTTOM
    }

    public aLoader() {

    }

    /**
     * This does all the initialization for targetView of our choice
     *
     * @param view     the root view in which the progressBar will be inflated
     * @param activity reference to the activity
     * @return
     */
    public aLoader setTargetView(View view, Activity activity, @NonNull Orientation orientation) {
        this.aProgressBar = new ProgressBar(activity);
        Random random = new Random();
        aProgressBar.setId(random.nextInt(100));
        aTextMessage = new TextView(activity);
        this.activity = activity;

        ViewGroup viewGroup = (ViewGroup) view;
        RelativeLayout relativeLayout = new RelativeLayout(activity);
        progressView = new LinearLayout(activity);
        LinearLayout.LayoutParams paramsProgress = null;
        RelativeLayout.LayoutParams paramsRoot = null;

        if (orientation == Orientation.TOP) {
            paramsProgress = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            progressView.setPadding(50, 20, 50, 20);
            progressView.setGravity(Gravity.CENTER);
            progressView.setOrientation(LinearLayout.HORIZONTAL);
            progressView.setLayoutParams(paramsProgress);

            paramsRoot = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            paramsRoot.addRule(RelativeLayout.CENTER_IN_PARENT);
            paramsRoot.addRule(RelativeLayout.CENTER_VERTICAL);
            relativeLayout.setGravity(Gravity.TOP);
            relativeLayout.setLayoutParams(paramsRoot);
            viewGroup.addView(relativeLayout);
            relativeLayout.addView(progressView);
        } else if (orientation == Orientation.CENTER) {
            paramsProgress = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            progressView.setPadding(50, 50, 50, 50);
            progressView.setGravity(Gravity.CENTER);
            progressView.setOrientation(LinearLayout.VERTICAL);
            progressView.setLayoutParams(paramsProgress);

            paramsRoot = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            paramsRoot.addRule(RelativeLayout.CENTER_IN_PARENT);
            relativeLayout.setGravity(Gravity.CENTER);
            relativeLayout.setLayoutParams(paramsRoot);
            viewGroup.addView(relativeLayout);
            relativeLayout.addView(progressView);
        } else {
            paramsProgress = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            progressView.setPadding(50, 20, 50, 20);
            progressView.setGravity(Gravity.CENTER);
            progressView.setOrientation(LinearLayout.HORIZONTAL);
            progressView.setLayoutParams(paramsProgress);

            paramsRoot = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            paramsRoot.addRule(RelativeLayout.CENTER_IN_PARENT);
            relativeLayout.setGravity(Gravity.BOTTOM);
            relativeLayout.setLayoutParams(paramsRoot);
            viewGroup.addView(relativeLayout);
            relativeLayout.addView(progressView);
        }

        paramsProgress = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressView.addView(aProgressBar, paramsProgress);
        aProgressBar.setVisibility(View.GONE);
        return this;
    }

    /**
     * This does all the initialization for dialog irrespective of any view
     *
     * @param activity
     * @return
     */
    public aLoader setDialog(Activity activity) {
        this.aProgressBar = new ProgressBar(activity);
        Random random = new Random();
        aProgressBar.setId(random.nextInt(100));
        aTextMessage = new TextView(activity);
        this.activity = activity;

        LinearLayout.LayoutParams paramsProgress = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressView = new LinearLayout(activity);
        progressView.setPadding(50, 50, 50, 50);
        progressView.setGravity(Gravity.CENTER);
        progressView.setOrientation(LinearLayout.VERTICAL);
        progressView.addView(aProgressBar, paramsProgress);

        loaderDialog = new LoaderDialog(activity);
        loaderDialog.setLayout(progressView);
        return this;
    }

    public aLoader setDialogCancelable(boolean cancelable) {
        if (loaderDialog != null) {
            loaderDialog.setCancelable(cancelable);
        }
        return this;
    }

    /**
     * This is to start the progress bar
     *
     * @return
     */
    public aLoader start() {
        if (aProgressBar != null && aTextMessage != null) {
            visible();
        } else {
            throw new NullPointerException("View not initialized");
        }
        return this;
    }

    /**
     * This is to stop the progress bar
     *
     * @return
     */
    public aLoader stop() {
        if (aProgressBar != null && aTextMessage != null) {
            gone();
        } else {
            throw new NullPointerException("View not initialized");
        }
        return this;
    }

    private void visible() {
        if (loaderDialog != null) {
            loaderDialog.show();
            return;
        }
        aProgressBar.setVisibility(View.VISIBLE);
        aTextMessage.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.VISIBLE);
    }

    private void gone() {
        if (loaderDialog != null) {
            loaderDialog.dismiss();
            return;
        }
        aProgressBar.setVisibility(View.GONE);
        aTextMessage.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);
    }

    /**
     * To set the progressBar color
     *
     * @param color
     * @return
     */
    public aLoader setProgressBarColor(int color) {
        if (this.aProgressBar != null) {
            this.aProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        } else {
            throw new NullPointerException("View not initialized");
        }
        return this;
    }

    /**
     * Set background of the progress
     *
     * @param drawable pass any drawable to set the background
     * @return
     */
    public aLoader setBackgroundView(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            progressView.setBackground(drawable);
        }
        return this;
    }

    /**
     * To set message
     *
     * @param message   the  message that needs to be display
     * @param textColor message color if wanted or else pass null
     * @param textSize  message textSize small,medium,large can be passed or else pass null for default textSize
     * @return
     */
    public aLoader message(String message, @Nullable Integer textColor, @Nullable TextSize textSize) {
        if (this.aProgressBar != null) {
            if (progressView != null) {
                aTextMessage.setText(message);
                // set textSize
                if (textSize != null) {
                    if (textSize == TextSize.SMALL) {
                        aTextMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    }
                    if (textSize == TextSize.MEDIUM) {
                        aTextMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                    }
                    if (textSize == TextSize.LARGE) {
                        aTextMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                    }
                }
                // set textColor
                if (textColor != null) {
                    aTextMessage.setTextColor(textColor);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                aTextMessage.setLayoutParams(params);
                aTextMessage.setGravity(Gravity.CENTER);
                try {
                    progressView.addView(aTextMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                throw new NullPointerException("View not initialized");
            }
        } else {
            throw new NullPointerException("View not initialized");
        }
        return this;
    }

    /**
     * Set animation to the text if required
     *
     * @param drawable pass any anim file to set the animation
     * @return
     */
    public aLoader setMessageAnimation(int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Animation a = AnimationUtils.loadAnimation(activity, drawable);
            aTextMessage.clearAnimation();
            aTextMessage.startAnimation(a);
        }
        return this;
    }

    public boolean isShown() {
        if (aProgressBar != null) {
            return aProgressBar.isShown();
        }
        return false;
    }
}
