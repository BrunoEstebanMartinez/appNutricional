package com.example.prueba;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_Ejercicios;
import DataBaseEstructure.Estructure_Plan;
import DataBaseEstructure.Estructure_recomendacion_ejercicios;
import DataBaseEstructure.Estructure_recomendacion_plan;
import DataBaseEstructure.ejercicios_Map;

public class CustomCalendarViewEjercicio extends LinearLayout {

    ImageButton NextButton, PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS = 42;

    Locale localizacion = new Locale("es", "ES");

    Calendar calendar = Calendar.getInstance(localizacion);
    Context context;
    DataBaseOpenHelper dataBaseOpenHelper;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", localizacion);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", localizacion);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", localizacion);
    SimpleDateFormat eventDateFormate = new SimpleDateFormat("yyyy-MM-dd", localizacion);


    EditText NombreEjer, plani, DuracionEjer;
    MyGridAdapterEjercicio myGridAdapterEjercicio;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<ejercicios_Map> eventsList =  new ArrayList<>();
    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinut;

    TextView  hora;
    public CustomCalendarViewEjercicio(Context context) {
        super(context);
    }

    public CustomCalendarViewEjercicio(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        IntializeLayout();
        SetUpCalendar();
        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, -1);
                SetUpCalendar();
            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, 1);
                SetUpCalendar();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);

                final View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout_ejercicio, null);
                //

                final EditText Nombre_E = addView.findViewById(R.id.nombre_Ejer);
                final EditText Duracion_E = addView.findViewById(R.id.duracion_Ejer);
                final EditText EventName_E = addView.findViewById(R.id.eventname_Ejer);
                final TextView EventTime_E = addView.findViewById(R.id.eventtime_Ejer);

                final CheckBox alarmMe = addView.findViewById(R.id.alarmmeEjer);
                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.setTime(dates.get(position));
                alarmYear = dateCalendar.get(Calendar.YEAR);
                alarmMonth = dateCalendar.get(Calendar.MONTH);
                alarmDay = dateCalendar.get(Calendar.DAY_OF_MONTH);
//
                NombreEjer = addView.findViewById(R.id.nombre_Ejer);
                DuracionEjer = addView.findViewById(R.id.duracion_Ejer);
                plani = addView.findViewById(R.id.eventname_Ejer);
                hora = addView.findViewById(R.id.eventtime_Ejer);
                SugerenciaNombre();

                //
                ImageButton SetTime_E = addView.findViewById(R.id.seteventime_Ejer);
                Button AddEvent_E = addView.findViewById(R.id.addevent_Ejer);
                SetTime_E.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minuts = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog
                                , new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hformate = new SimpleDateFormat("K:mm a", localizacion);
                                String event_Time = hformate.format(c.getTime());
                                EventTime_E.setText(event_Time);

                            }
                        }, hours, minuts, false);
                        timePickerDialog.show();
                    }
                });
                final String date = eventDateFormate.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));


                AddEvent_E.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nombre = NombreEjer.getText().toString();
                                String Duracion = DuracionEjer.getText().toString();
                                        String plan = plani.getText().toString();
                                                String horaE =  hora.getText().toString();
                                                if(nombre.equals("") || Duracion.equals("") || plan.equals("") || horaE.equals("00:00 AM/PM")){
                                                    Toast.makeText(context, "!Ups! Uno o varios campos están vacios",
                                                            Toast.LENGTH_LONG).show();
                                                }else{
                        if(alarmMe.isChecked()){
                            SalvarPlan(Nombre_E.getText().toString(), Duracion_E.getText().toString(), EventName_E.getText().toString(), EventTime_E.getText().toString(), date, month, year, "on");
                            SetUpCalendar();
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinut);
                            setAlarm(calendar, EventName_E.getText().toString(), EventTime_E.getText().toString(),
                                    getRequestCode(Nombre_E.getText().toString(), Duracion_E.getText().toString(), date, EventName_E.getText().toString(), EventTime_E.getText().toString()));
                            alertDialog.dismiss();
                        }else {
                            SalvarPlan(Nombre_E.getText().toString(), Duracion_E.getText().toString(),
                                    EventName_E.getText().toString(), EventTime_E.getText().toString(),
                                    date, month, year, "off");
                            SetUpCalendar();
                            alertDialog.dismiss();
                        }
                        }
                    }
                });
                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String date = eventDateFormate.format(dates.get(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout_ejercicio, null);
                RecyclerView recyclerView = showView.findViewById(R.id.EventsRVEjercicio);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EjercicioRecyclerAdapter ejercicioRecyclerAdapter = new EjercicioRecyclerAdapter(showView.getContext()
                        ,RecoleccionPorDia(date));
                recyclerView.setAdapter(ejercicioRecyclerAdapter);
                ejercicioRecyclerAdapter.notifyDataSetChanged();
                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });

    }

    private int getRequestCode(String nombre_E, String duracion_E, String date_E, String event_E,
                               String time_E){
        int code = 0;

        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoEjercicioPorIDNotificacion(nombre_E, duracion_E, date_E, event_E, time_E,  database);
        while (cursor.moveToNext()){
            code = cursor.getInt(cursor.getColumnIndex(Estructure_Ejercicios.ID_E));
        }
        cursor.close();
        dataBaseOpenHelper.close();
        return code;
    }
    private void setAlarm (Calendar calendar, String event_E, String time_E, int RequestCOde){
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiverEjercicios.class);
        intent.putExtra("event_E", event_E);
        intent.putExtra("time_E", time_E);
        intent.putExtra("id_E", RequestCOde);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCOde, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private ArrayList<ejercicios_Map> RecoleccionPorDia(String date_E){
        ArrayList<ejercicios_Map> arrayList = new ArrayList<>();
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoEjercicio(date_E, database);
        while (cursor.moveToNext()){
            String nombre_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.NOMBRE_E));
            String duracion_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.DURACION_E));
            String planificador_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.EVENT_E));
            String hora_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.TIME_E));
            String dia_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.DATE_E));
            String mes_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.MONTH_E));
            String anio_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.YEAR_E));
            ejercicios_Map events = new ejercicios_Map(nombre_E, duracion_E, planificador_E, hora_E, dia_E, mes_E, anio_E);
            arrayList.add(events);
        }
        cursor.close();
        dataBaseOpenHelper.close();

        return arrayList;
    }

    public CustomCalendarViewEjercicio(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    public void SalvarPlan(String nombre_E, String duracion_E, String event_E,
                           String time_E, String date_E, String month_E, String year_E, String notificacion_E){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.GuardarEjercicioACalendario(nombre_E, duracion_E, event_E,
                 time_E, date_E, month_E,  year_E, notificacion_E ,database);
        dataBaseOpenHelper.close();
        Toast.makeText(context, "¡Exito! Oprime por 3 segundos para ver tu planeación", Toast.LENGTH_SHORT).show();

    }
    private void IntializeLayout(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout_ejercicio,this);
        NextButton = view.findViewById(R.id.nextBtnEjercicio);
        PreviousButton = view.findViewById(R.id.previousBtnEjercicio);
        CurrentDate = view.findViewById(R.id.current_Date_Ejercicio);
        gridView = view.findViewById(R.id.gridviewejercicio);
    }

    private void SetUpCalendar(){
        String currwntDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currwntDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);
        RecoleccionDePlanPorMes(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));
        while (dates.size() < MAX_CALENDAR_DAYS){

            dates.add((Date) monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        myGridAdapterEjercicio = new MyGridAdapterEjercicio(context, dates, calendar, eventsList);
        gridView.setAdapter(myGridAdapterEjercicio);
    }


    private void RecoleccionDePlanPorMes(String month_E, String year_E){
        eventsList.clear();
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoEjercicioPorMes(month_E,year_E,database);
        while (cursor.moveToNext()){
            String nombre_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.NOMBRE_E));
            String duracion_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.DURACION_E));
            String planificador_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.EVENT_E));
            String hora_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.TIME_E));
            String dia_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.DATE_E));
            String mes_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.MONTH_E));
            String anio_E = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.YEAR_E));
            ejercicios_Map events = new ejercicios_Map(nombre_E, duracion_E, planificador_E, hora_E, dia_E, mes_E, anio_E);
            eventsList.add(events);
        }
        cursor.close();
        dataBaseOpenHelper.close();
    }
     public void SugerenciaNombre(){
         SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
         dataBaseOpenHelper = new DataBaseOpenHelper(context);
         Cursor cursor = dataBaseOpenHelper.ConsultaRecomendacionSeguimientoEjercicio(database);
         while(cursor.moveToNext()){
             NombreEjer.setText(cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.NOMBRE_RE)));
             DuracionEjer.setText(cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.TIEMPO_RE)));
         }
         cursor.close();
     }
}
