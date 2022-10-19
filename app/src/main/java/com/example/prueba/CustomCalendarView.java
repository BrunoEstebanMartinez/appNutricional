package com.example.prueba;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_Plan;
import DataBaseEstructure.Estructure_recomendacion_plan;
import DataBaseEstructure.plan_Map;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomCalendarView extends LinearLayout {
    ImageButton NextButton, PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS = 42;
    Locale localizacion = new Locale("es", "ES");
    Calendar calendar = Calendar.getInstance(localizacion);
    Context context;
    DataBaseOpenHelper dataBaseOpenHelper;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy",localizacion);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", localizacion);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", localizacion);
    SimpleDateFormat eventDateFormate = new SimpleDateFormat("yyyy-MM-dd",localizacion);

    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<plan_Map> eventsList =  new ArrayList<>();
    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinut;


    EditText NombreCom, TipoComRer, TipoComida;
    TextView Time;
    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs) {
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
                final View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout, null);
                //

                final EditText Tipo_Comida = addView.findViewById(R.id.tipo_comida);
                final EditText Nombre = addView.findViewById(R.id.nombre_P);
                final EditText EventName = addView.findViewById(R.id.eventname);
                final TextView EventTime = addView.findViewById(R.id.eventtime);
                final CheckBox alarmMe = addView.findViewById(R.id.alarmme);
                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.setTime(dates.get(position));
                alarmYear = dateCalendar.get(Calendar.YEAR);
                alarmMonth = dateCalendar.get(Calendar.MONTH);
                alarmDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

                //
                TipoComida = addView.findViewById(R.id.tipo_comida);
                NombreCom = addView.findViewById(R.id.nombre_P);
                TipoComRer = addView.findViewById(R.id.eventname);
                Time = addView.findViewById(R.id.eventtime);

                SugerenciaDeNombre();
                //
                ImageButton SetTime = addView.findViewById(R.id.seteventime);
                Button AddEvent = addView.findViewById(R.id.addevent);
                SetTime.setOnClickListener(new OnClickListener() {
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
                                EventTime.setText(event_Time);
                                alarmHour = c.get(Calendar.HOUR_OF_DAY);
                                alarmMinut = c.get(Calendar.MINUTE);

                            }
                        }, hours, minuts, false);
                        timePickerDialog.show();
                    }
                });
                final String date = eventDateFormate.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));


                    AddEvent.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String NombreCampo = NombreCom.getText().toString();
                            String RecorCamp = TipoComRer.getText().toString();
                            String Tiempo = Time.getText().toString();
                            String CampoComida = TipoComida.getText().toString();

                            if (CampoComida.equals("") || NombreCampo.equals("") || RecorCamp.equals("") || Tiempo.equals("00:00 AM/PM")) {
                                Toast.makeText(context, "!Ups! Uno o varios campos están vacios",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                if (alarmMe.isChecked()) {
                                    SalvarPlan(Tipo_Comida.getText().toString(), Nombre.getText().toString(), EventName.getText().toString(), EventTime.getText().toString(), date, month, year, "on");
                                    SetUpCalendar();
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.set(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinut);
                                    setAlarm(calendar, EventName.getText().toString(), EventTime.getText().toString(),
                                            getRequestCode(Tipo_Comida.getText().toString(), Nombre.getText().toString(), date, EventName.getText().toString(), EventTime.getText().toString()));
                                    alertDialog.dismiss();
                                } else {
                                    SalvarPlan(Tipo_Comida.getText().toString(), Nombre.getText().toString(),
                                            EventName.getText().toString(), EventTime.getText().toString(),
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
               View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
               RecyclerView recyclerView = showView.findViewById(R.id.EventsRV);
               RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
               recyclerView.setLayoutManager(layoutManager);
               recyclerView.setHasFixedSize(true);
               EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext()
                       ,RecoleccionPorDia(date));
               recyclerView.setAdapter(eventRecyclerAdapter);
               eventRecyclerAdapter.notifyDataSetChanged();
               builder.setView(showView);
               alertDialog = builder.create();
               alertDialog.show();


               return true;
           }
       });


    }



    private int getRequestCode(String tipo_concepto, String nombre_comida, String date,String event, String time){
        int code = 0;

        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoPlanPorIDNotificacion(tipo_concepto, nombre_comida, date, event, time, database);
        while (cursor.moveToNext()){
            code = cursor.getInt(cursor.getColumnIndex(Estructure_Plan.ID));
        }
        cursor.close();
        dataBaseOpenHelper.close();
        return code;
    }


    private void setAlarm (Calendar calendar, String event, String time, int RequestCOde){
                 Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
                 intent.putExtra("event", event);
                 intent.putExtra("time", time);
                 intent.putExtra("id", RequestCOde);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCOde, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private ArrayList<plan_Map> RecoleccionPorDia(String date){
        ArrayList<plan_Map> arrayList = new ArrayList<>();
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoPlan(date, database);
        while (cursor.moveToNext()){
            String tipo_comida = cursor.getString(cursor.getColumnIndex(Estructure_Plan.TIPO_CONCEPTO));
            String nombre = cursor.getString(cursor.getColumnIndex(Estructure_Plan.NOMBRE_COMIDA));
            String planificador = cursor.getString(cursor.getColumnIndex(Estructure_Plan.EVENT));
            String hora = cursor.getString(cursor.getColumnIndex(Estructure_Plan.TIME));
            String dia = cursor.getString(cursor.getColumnIndex(Estructure_Plan.DATE));
            String mes = cursor.getString(cursor.getColumnIndex(Estructure_Plan.MONTH));
            String anio = cursor.getString(cursor.getColumnIndex(Estructure_Plan.YEAR));
            plan_Map events = new plan_Map(tipo_comida, nombre, planificador, hora, dia, mes, anio);
            arrayList.add(events);
        }
        cursor.close();
        dataBaseOpenHelper.close();

        return arrayList;
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);

    }

    public void SalvarPlan(String tipo_concepto, String nombre_comida, String event, String time,
                           String date, String month, String year, String notificacion){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.GuardarPlan(tipo_concepto, nombre_comida, event, time, date, month, year, notificacion, database);
        dataBaseOpenHelper.close();
        Toast.makeText(context, "¡Exito! Oprime por 3 segundos para ver tu planeación", Toast.LENGTH_SHORT).show();

    }


    private void IntializeLayout(){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        NextButton = view.findViewById(R.id.nextBtn);
        PreviousButton = view.findViewById(R.id.previousBtn);
        CurrentDate = view.findViewById(R.id.current_Date);
        gridView = view.findViewById(R.id.gridview);
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
        myGridAdapter = new MyGridAdapter(context, dates, calendar, eventsList);
        gridView.setAdapter(myGridAdapter);
    }


    private void RecoleccionDePlanPorMes(String month, String year){
        eventsList.clear();
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoPlanPorMes(month,year,database);
        while (cursor.moveToNext()){
           // Integer ID = Integer.valueOf(cursor.getString(cursor.getColumnIndex(Estructure_Plan.ID)));
            String tipo_comida = cursor.getString(cursor.getColumnIndex(Estructure_Plan.TIPO_CONCEPTO));
            String nombre = cursor.getString(cursor.getColumnIndex(Estructure_Plan.NOMBRE_COMIDA));
            String planificador = cursor.getString(cursor.getColumnIndex(Estructure_Plan.EVENT));
            String hora = cursor.getString(cursor.getColumnIndex(Estructure_Plan.TIME));
            String dia = cursor.getString(cursor.getColumnIndex(Estructure_Plan.DATE));
            String mes = cursor.getString(cursor.getColumnIndex(Estructure_Plan.MONTH));
            String anio = cursor.getString(cursor.getColumnIndex(Estructure_Plan.YEAR));
            plan_Map events = new plan_Map(tipo_comida, nombre, planificador, hora, dia, mes, anio);
            eventsList.add(events);
        }
        cursor.close();
        dataBaseOpenHelper.close();
    }

    public void SugerenciaDeNombre(){
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        Cursor cursor = dataBaseOpenHelper.ConsultaRecomendacionSeguimientoAlimento(database);
        while(cursor.moveToNext()){
            NombreCom.setText(cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.NOMBRE_RP)));
            TipoComRer.setText(cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.TIPO_RP)));
        }
        cursor.close();
    }
}
