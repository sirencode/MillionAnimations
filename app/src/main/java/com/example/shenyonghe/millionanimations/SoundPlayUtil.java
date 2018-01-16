package com.example.shenyonghe.millionanimations;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * Created by shenyonghe on 2018/1/12.
 */

public class SoundPlayUtil {
    public static void playSound(Activity activity, int raw, MediaPlayer mp) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetFileDescriptor file = activity.getResources().openRawResourceFd(raw);
                try {
                    mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                            file.getLength());
                    mp.prepare();
                    file.close();
                    mp.setVolume(1.0f, 1.0f);
                    mp.setLooping(false);
                    mp.start();
                    mp.setOnCompletionListener(mp1 -> mp1.release());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //震动milliseconds毫秒
    public static void vibrate(final Activity activity) {
        /*
         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
         * */
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
