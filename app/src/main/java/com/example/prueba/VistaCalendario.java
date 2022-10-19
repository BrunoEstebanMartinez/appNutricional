package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VistaCalendario extends Activity {

    CustomCalendarView customCalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_calendario);
        customCalendarView = findViewById(R.id.custom_calendar_view);

    }
}