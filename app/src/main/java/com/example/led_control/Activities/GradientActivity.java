package com.example.led_control.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.led_control.ColorHandler;
import com.example.led_control.IoT_Devices.IoT_DevicesFactory;
import com.example.led_control.IoT_Devices.LedStrip;
import com.example.led_control.R;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;


public class GradientActivity extends AppCompatActivity implements View.OnTouchListener {

    LedStrip ledStrip;


    ImageView ColorWheelImage;
    Bitmap ColorWheelBitmap;
    View ColorView0;
    View ColorView1;
    SeekBar BrightnessSeekBar;

    int SelectedColor0=0;
    int SelectedColor1=0;
    boolean SecondColor = false;





    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient);
        Toolbar toolbar = findViewById(R.id.ToolbarGradient);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ColorWheelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.extractcolors);
        ColorWheelImage = findViewById(R.id.ColorWheelGradient);
        ColorView0 = findViewById(R.id.ColorView0Gradient);
        ColorView1 = findViewById(R.id.ColorView1Gradient);
        BrightnessSeekBar = findViewById(R.id.SeekBarBrightnessGradient);
        ColorWheelImage.setImageBitmap(ColorWheelBitmap);
        ColorWheelImage.setOnTouchListener(this);


        try {
            IoT_DevicesFactory.UpdateLedStripsList(this.getApplicationContext());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ledStrip = IoT_DevicesFactory.getLedSrtip();



        BrightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                ledStrip.SetColorGradient(SelectedColor0,SelectedColor1,BrightnessSeekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                ledStrip.StartSendingRequstsPeriodicalAsync(0);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                ledStrip.StopSendingRequstsPeriodicalAsync();
            }
        });

    }


    public void ColorViewClick(View view)
    {
        SecondColor = !SecondColor;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                {

                    ledStrip.StartSendingRequstsPeriodicalAsync(0);
                    int ExtractedColor = ColorHandler.ExtractColor(event, ColorWheelImage, ColorWheelBitmap);
                    if (ExtractedColor != 0)
                    {
                        if (SecondColor)
                        {
                            SelectedColor0 = ExtractedColor;
                            ColorView0.setBackgroundColor(SelectedColor0);
                        }

                        else
                        {
                            SelectedColor1 = ExtractedColor;
                            ColorView1.setBackgroundColor(SelectedColor1);
                        }
                        ledStrip.SetColorGradient(SelectedColor0, SelectedColor1, BrightnessSeekBar.getProgress());
                    }
                break;
                }

            case MotionEvent.ACTION_MOVE:
                {

                    int ExtractedColor = ColorHandler.ExtractColor(event, ColorWheelImage, ColorWheelBitmap);
                    if (ExtractedColor != 0)
                    {
                        if (SecondColor)
                        {
                            SelectedColor0 = ExtractedColor;
                            ColorView0.setBackgroundColor(SelectedColor0);
                        }

                         else
                        {
                            SelectedColor1 = ExtractedColor;
                            ColorView1.setBackgroundColor(SelectedColor1);
                        }
                        ledStrip.SetColorGradient(SelectedColor0, SelectedColor1, BrightnessSeekBar.getProgress());
                    }
                    break;
                }

            case MotionEvent.ACTION_UP: { ledStrip.StopSendingRequstsPeriodicalAsync();break; }

        }

        return true;
    }

}
