package com.iyoyogo.android.view;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.iyoyogo.android.R;
import com.iyoyogo.android.utils.DensityUtil;

public class LikePopupWindow extends BasePopupWindow {
    private View mContentView;
    private Context context;
    private LikeCallback likeCallback;
    private int isLike;
    DrawableTextView drawableTextView;
    private int icon;
    private String text;


    public LikePopupWindow(Context context, int icon, String text) {
        super(context);
        this.context = context;
        this.icon = icon;
        this.text = text;
        init();
    }

    public void setLikeCallback(LikeCallback likeCallback) {
        this.likeCallback = likeCallback;
    }

    private void init() {
        mContentView = LayoutInflater.from(context).inflate(R.layout.popwindow_like, null);
        setContentView(mContentView);
        setWidth(DensityUtil.dp2px(context, 100));
        setHeight(DensityUtil.dp2px(context, 40));
        setTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
        // 重写onKeyListener
        mContentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        drawableTextView = mContentView.findViewById(R.id.dt_like);
        if (0 != icon) {
            drawableTextView.setDrawableLeft(icon);
        }
        drawableTextView.setText(text);
        mContentView.findViewById(R.id.rl_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (likeCallback != null) {
                    likeCallback.onLikeClick();
                }
            }
        });
    }

    @Override
    public View getmContentView() {
        return mContentView;
    }

    public interface LikeCallback {
        void onLikeClick();
    }
}
