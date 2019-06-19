package com.example.led_control.IoT_Devices;

import android.content.Context;
import com.koushikdutta.ion.Ion;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;



public class IoT_Device
{

    public   String Address;
    public   String Request ;
    //
    private  String DeviceName;
    private  String Response ;
    private  Context AppContext;
    private boolean ConnectionStatus = true;


    public  IoT_Device( String address , Context context )
    {
        AppContext = context;
        Address = address;
    }


    public String GetDeviceName() { return DeviceName; }

    public boolean SetDeviceName(String Name)
    {
        try
        {
         // send command to device to change name
        }
        catch (Exception e)
        {
            return  false;
        }
        DeviceName = Name;
            return  true ;

    }

    public JSONObject GetDeviceInfo () throws ExecutionException, InterruptedException, JSONException
    {

        JSONObject JsonData = new JSONObject(Ion.with(AppContext).load(Address+"/DeviceInfo").asString().get());
        // Get Name from Json


        return JsonData;
    }

    public void StartSendingRequstsPeriodicalAsync ( final int DelayInterval)
    {
        if ( ConnectionStatus != true)
        {

            ConnectionStatus = true;

            new Thread(new Runnable() {
                public void run() {
                    while (ConnectionStatus) {
                        try {
                            // Response =
                            Ion.with(AppContext).load(Request).asString().get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(DelayInterval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }


    }
    public void StopSendingRequstsPeriodicalAsync ()
    {
        ConnectionStatus = false;
    }


}
