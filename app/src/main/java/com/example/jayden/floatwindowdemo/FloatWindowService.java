package com.example.jayden.floatwindowdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class FloatWindowService extends Service {
    public FloatWindowService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createFloatWindow(getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }

    private void createFloatWindow(Context context){
        WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);

        //screen width/height
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        int screenWidth = point.x;
        int screenHeight = point.y;

        //image
        FloatWindowSmallView imageView = new FloatWindowSmallView(context);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = 100;
        params.height = 100;
        params.x = screenWidth;
        params.y = screenHeight / 2;

        imageView.setParams(params);
        windowManager.addView(imageView, params);
    }
}
