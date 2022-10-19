package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VistaCalendarioEjercicio extends Activity {

    CustomCalendarViewEjercicio customCalendarViewEjercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_calendario_ejercicio);
        customCalendarViewEjercicio = findViewById(R.id.custom_calendar_view_ejercicio);



    }
}