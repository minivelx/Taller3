package com.movil.p.taller3;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.pm.ActivityInfo;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView x,y,z;
    private  Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        //sensor coordenadas
        x = (TextView)findViewById(R.id.xID);
        y = (TextView)findViewById(R.id.yID);
        z = (TextView)findViewById(R.id.zID);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume(){
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0){ //si tiene acelerometro
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }

    protected void onPause(){
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, mAccelerometer);
        super.onPause();
    }

    protected void onStop(){
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, mAccelerometer);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.x.setText("X = "+event.values[SensorManager.DATA_X]);
        this.y.setText("Y = "+event.values[SensorManager.DATA_Y]);
        this.z.setText("Z = "+event.values[SensorManager.DATA_Z]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
