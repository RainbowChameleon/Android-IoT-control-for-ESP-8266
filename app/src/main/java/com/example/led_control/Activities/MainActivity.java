package com.example.led_control.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.led_control.R;
import com.example.led_control.RainbowActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> categories = new ArrayList<String>();
        categories.add("LED strip 1");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


       spinner =  findViewById(R.id.spinner);

       spinner.setAdapter(dataAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_settings_button,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId()==R.id.OpenSettings)
        {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void Solid_Color_Btn_Click(View view)
    {
        Intent intent = new Intent(this, SolidColorActivity.class);
        startActivity(intent);
    }

    public void StatDebugActivity(View view)
    {

    }

    public void Rainbow_BtnClick(View view)
    {
       Intent intent = new Intent(this, RainbowActivity.class);
       startActivity(intent);

    }

    public void Gradient_Color_Btn_Click(View view)
    {
        Intent intent = new Intent(this, GradientActivity.class);
        startActivity(intent);
    }
}
