package com.example.jayden.floatwindowdemo;

import android.content.Context;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Created by jayden on 8/11/15.
 */
public class FloatWindowSmallView extends ImageView{
    private float xInView;
    private float yInView;
    private float xDownInScreen;
    private float yDownInScreen;
    private float xInScreen;
    private float yInScreen;
    private boolean isPressed;

    private int statusBarHeight;
    private WindowManager.LayoutParams params;
    private WindowManager windowManager;

    public FloatWindowSmallView(Context context) {
        super(context);
        setImageResource(R.mipmap.ic_launcher);
        windowManager = getWindowManager(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isPressed = true;
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                xInView = event.getX();
                yInView = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                resetViewPosition();
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setParams(WindowManager.LayoutParams params){
        this.params = params;
    }

    private void updateViewPosition(){
        params.x = (int)(xInScreen - xInView);
        params.y = (int)(yInScreen - yInView);
        windowManager.updateViewLayout(this, params);
    }

    private void resetViewPosition(){
        params.x = (int)(xInScreen - xInView);
        params.y = (int)(yInScreen - yInView);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        if(xInScreen <= (int)(screenWidth / 2.0) ){
            params.x = 0;
        }else{
            params.x = screenWidth;
        }
        windowManager.updateViewLayout(this, params);
    }

    private WindowManager getWindowManager(Context context){
        if(windowManager == null){
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    private int getStatusBarHeight(){
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
}
