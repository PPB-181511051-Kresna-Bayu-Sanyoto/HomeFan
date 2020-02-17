package com.example.fan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch lights;
    SeekBar seekBar;
    final int SPEED[] = {5000, 3500, 2000, 1000};
    int index;
    GradientDrawable gd = new GradientDrawable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        lights = (Switch) findViewById(R.id.lights);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //animator
        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rotate when toggle button isChecked=true
                if(toggleButton.isChecked()==true){
                    rotateAnimator.setDuration(5000);
                    rotateAnimator.start();
                }
                else{
                    //stop when toggle button isChecked=false
                    rotateAnimator.end();
                }
            }
        });


        //turn on/off light
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

            //when checked=true
        lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lights.isChecked()==true) {
                    gd.setColors(new int[]{Color.YELLOW, Color.TRANSPARENT});
                    imageView.setBackground(gd);
                }else{
                    //when checked=false
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                index=progress;
                //rotate the fan based on progress parameter
                if (toggleButton.isChecked()==true){
                    rotateAnimator.setDuration(SPEED[index]);
                    rotateAnimator.start();
                }else{
                    rotateAnimator.end();
                }
                lights.callOnClick();

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
            }
        });

    }
}
