package com.avinash.aloader;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

/**
 * Created by avinash on 23/5/18.
 */

class LoaderDialog extends Dialog {

    LoaderDialog(@NonNull Context context) {
        super(context);
    }

    public void setLayout(View view) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(view);
        Window window = this.getWindow();
        if (window != null) {
            window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
