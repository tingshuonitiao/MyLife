package com.mylife.tsnt.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class ToastUtil {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    /*private static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }

    private static void showToast(Context context, int resId, int gravity) {
        showToast(context, context.getString(resId), gravity, 0, 0);
    }

    private static void showToast(Context context, String s, int gravity) {
        showToast(context, s, gravity, 0, 0);
    }

    private static void showToast(Context context, int resId, int gravity, int offX, int offY) {
        showToast(context, context.getString(resId), gravity, offX, offY);
    }*/

    //API低版本时设置一个Gravity.CENTER只会水平居中
    public static void showToast(Context context, String s) {
        showToast(context, s, Gravity.CENTER | Gravity.CENTER, 0, 0);
        /*if (toast == null) {
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;*/
    }

    private static void showToast(Context context, String s, int gravity, int offX, int offY) {
        if (toast == null) {
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, offX, offY);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
