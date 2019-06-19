package com.example.led_control.IoT_Devices;

import android.content.Context;
import android.graphics.Color;

import com.example.led_control.IoT_Devices.IoT_Device;


public class LedStrip extends IoT_Device
{


    private   int LED_Num;



    public LedStrip(String StripAddress , Context context)  {

        super(StripAddress,context);

    }


    public void SetSolidColor(int NewColor, int brightness)
    {

        int r = Color.red(NewColor);
        int g = Color.green(NewColor);
        int b = Color.blue(NewColor);

        Request = Address + "/RGB/*r"+r+"r*/*g"+g+"g*/*b"+b+"b*/*i"+brightness+"i*/";

    }


    public void SetColorGradient (int FirstColor , int SecondColor, int brightness)
    {

        int r = Color.red(FirstColor);
        int g = Color.green(FirstColor);
        int b = Color.blue(FirstColor);

        int r1 = Color.red(SecondColor);
        int g1 = Color.green(SecondColor);
        int b1 = Color.blue(SecondColor);

        Request =  Address + "/Gradient/"+
                "*r"+ r + "r*/*g" + g + "g*/*b" + b + "b*"+
                "*R"+ r1 + "R*/*G" + g1 + "G*/*B" + b1 + "B*"+
                "/*i" + brightness + "i*/";
        //
    }




}
