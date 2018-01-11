package com.example.shenyonghe.millionanimations;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by shenyonghe on 2018/1/11.
 */

public class ProgressBarAnimation extends ProgressBar {
    public ProgressBarAnimation(Context context) {
        super(context);
    }

    public ProgressBarAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBarAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setProgress(int progress) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 25; i++) {
                    Message message = new Message();
                    message.what = progress * i / 24;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setMyProgress(msg.what);
        }
    };

    private void setMyProgress(int progress) {
        super.setProgress(progress);
    }
}
