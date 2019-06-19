package com.example.led_control.IoT_Devices;

import android.content.Context;

import com.example.led_control.IoT_Devices.LedStrip;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class IoT_DevicesFactory
{

  static  List <LedStrip> ledStrips = new ArrayList();


    public static void UpdateLedStripsList(Context context) throws InterruptedException, ExecutionException, JSONException {
       LedStrip LedStrip0;

          LedStrip0 = new LedStrip("http://192.168.43.158",context);
          ledStrips.add(LedStrip0);
  }

    public static  List <LedStrip> GetLedStripsList()
  {

      return ledStrips;
  }
  public static LedStrip getLedSrtip ()
  {
      return ledStrips.get(0);
  }
 // public getStripsNames



}
