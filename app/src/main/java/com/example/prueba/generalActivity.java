package com.example.prueba;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.prueba.databinding.ActivityGeneralBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class generalActivity extends Activity {
    private Integer resultadoC = 0;
    private double resultadoKis = 0;
    private Double ultimo = 0.0;
    private Double ultimo2 = 0.0;
    private TextView Pasos;
    private TextView PreviousCounter;
    private TextView local;
    private TextView Cal;
    private TextView Tiempo;
    private TextView kilo;
    private Integer calorias = 0;
    private Integer kilometros = 0;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private LocationManager ubicacion;
    ActivityGeneralBinding binding;
    private Chronometer chronometer;
    private boolean runnning;
    private long pauseOffset;
    private Activity ActivityCompat;

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        binding=ActivityGeneralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //localizacion();
        //registrarLocalizacion();
        Cal = findViewById(R.id.calorias);
        Pasos = findViewById(R.id.pasos);
        local = findViewById(R.id.localizacion);
        kilo = findViewById(R.id.kis);
        PreviousCounter = findViewById(R.id.tvcounter);
        chronometer = findViewById(R.id.tiempo);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener stepDetector = new SensorEventListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 6) {
                        stepCount++;
                        calorias = calorias + stepCount;
                        resultadoC = calorias / 20;
                        //ultimo2 = resultadoC / 100;
                        kilometros = kilometros  + stepCount;
                        resultadoKis = kilometros * 0.77;
                        ultimo = resultadoKis / 1000;

                    }
                    Pasos.setText(stepCount.toString());
                    PreviousCounter.setText(stepCount.toString());
                    Cal.setText(resultadoC.toString());
                    kilo.setText(ultimo.toString());
                    final ValueAnimator animator = ValueAnimator.ofInt(stepCount);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            binding.tvcounter.setText(animation.getAnimatedValue().toString()+"");
                            binding.progressbar.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
                        }
                    });
                    animator.start();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(stepDetector, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void startChronometer(View v){
        if(!runnning){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            runnning = true;
        }
    }
    public void pauseChronometer(View v){
        if (runnning){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            runnning = false;
        }
    }
    public void resetChronometer(View v){
      chronometer.setBase(SystemClock.elapsedRealtime());
      pauseOffset = 0;
    }

    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
    }

    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);

    }

    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("Contando", 0);
    }
/*
    private void localizacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }
        Cal = findViewById(R.id.calorias);
        Tiempo = findViewById(R.id.tiempo);
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc != null){
        Log.d("Latitud", String.valueOf(loc.getLatitude()));
        Log.d("Longitud", String.valueOf(loc.getLongitude()));
       // Cal.setText(String.valueOf(loc.getLatitude()));
        // Tiempo.setText(String.valueOf(loc.getLongitude()));
        }
    }
*/
    /*
    private void registrarLocalizacion() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new miLocalizacionListener());
    }
*/

    
   private class miLocalizacionListener implements LocationListener{

       @Override
       public void onLocationChanged(Location location) {
         location.getLatitude();
         location.getLongitude();
          //Cal.setText(lat);
          //kilo.setText(lon);
           Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

           try {
               List<Address> direccion1 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
               local.setText(direccion1.get(0).getAddressLine(0));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       @Override
       public void onStatusChanged(String s, int i, Bundle bundle) {

       }

       @Override
       public void onProviderEnabled(String s) {

       }

       @Override
       public void onProviderDisabled(String s) {

       }


   }



}