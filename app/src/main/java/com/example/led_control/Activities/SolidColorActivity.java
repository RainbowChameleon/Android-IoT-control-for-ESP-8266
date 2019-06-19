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


public class SolidColorActivity extends AppCompatActivity implements View.OnTouchListener {


    LedStrip ledStrip;
    ImageView ColorWheelImage;
    Bitmap ColorWheelBitmap;
    View ColorView;
    SeekBar SeekBarBrightness;

    int SelectedColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_color);
        Toolbar toolbar = findViewById(R.id.toolbarSolidColor);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ColorWheelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.extractcolors);

        ColorWheelImage = findViewById(R.id.ColorWheelSolidColor);
        ColorView = findViewById(R.id.colorViewSolidColor);
        SeekBarBrightness = findViewById(R.id.seekBarBrightnessSolidColor);

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




        SeekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                ledStrip.SetSolidColor(SelectedColor,SeekBarBrightness.getProgress());
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN :
                {   SelectedColor = ColorHandler.ExtractColor(event,ColorWheelImage,ColorWheelBitmap);
                    ledStrip.StartSendingRequstsPeriodicalAsync(0);
                    if (SelectedColor!=0)
                    {
                        ledStrip.SetSolidColor(SelectedColor, SeekBarBrightness.getProgress());
                        ColorView.setBackgroundColor(SelectedColor);
                    }
                    break;
                }
            case  MotionEvent.ACTION_MOVE:
                {
                    SelectedColor = ColorHandler.ExtractColor(event,ColorWheelImage,ColorWheelBitmap);

                    if (SelectedColor!=0)
                    {
                        ledStrip.SetSolidColor(SelectedColor, SeekBarBrightness.getProgress());
                        ColorView.setBackgroundColor(SelectedColor);
                    }
                    break;
                }
            case  MotionEvent.ACTION_UP : { ledStrip.StopSendingRequstsPeriodicalAsync(); break;}

        }

        return true;
    }


}



