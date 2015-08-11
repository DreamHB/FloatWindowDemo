package com.example.jayden.floatwindowdemo;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void floatWindowClick(View v){
        Toast.makeText(this, "clicked ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FloatWindowService.class);
        startService(intent);
        finish();
    }



}
