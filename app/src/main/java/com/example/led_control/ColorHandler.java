package com.example.led_control;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.widget.ImageView;

public final class ColorHandler
{
    public static int ExtractColor (MotionEvent event, ImageView Image, Bitmap ImageBitmap)
    {
        float eventX = event.getX();
        float eventY = event.getY();
        float[] eventXY = new float[]{eventX, eventY};

        Matrix invertMatrix = new Matrix();
        Image.getImageMatrix().invert(invertMatrix);

        invertMatrix.mapPoints(eventXY);
        int x = Integer.valueOf((int) eventXY[0]);
        int y = Integer.valueOf((int) eventXY[1]);
        if (x < 0) {
            x = 0;
        } else if (x > ImageBitmap.getWidth() - 1) {
            x = ImageBitmap.getWidth() - 1;
        }

        if (y < 0) {
            y = 0;
        } else if (y > ImageBitmap.getHeight() - 1) {
            y = ImageBitmap.getHeight() - 1;
        }


        int color = ImageBitmap.getPixel(x, y);

        return  color;

    }

}
