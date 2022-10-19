package com.example.prueba;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_Ejercicios;
import DataBaseEstructure.Estructure_Plan;
import DataBaseEstructure.ejercicios_Map;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

public class EjercicioRecyclerAdapter extends RecyclerView.Adapter<EjercicioRecyclerAdapter.MyViewHolder> {


    ArrayList<ejercicios_Map> arrayList;
    Context context;
    DataBaseOpenHelper dataBaseOpenHelper;
    public EjercicioRecyclerAdapter(Context context, ArrayList<ejercicios_Map> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_rowlayout_ejercicio, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final ejercicios_Map MapeoEjercicio = arrayList.get(position);
        holder.Nombre_ETxt.setText(MapeoEjercicio.getNOMBRE_E());
        holder.Duracion_ETxt.setText(MapeoEjercicio.getDURACION_E());
        holder.Date_ETxt.setText(MapeoEjercicio.getDATE_E());
        holder.Event_ETxt.setText(MapeoEjercicio.getEVENT_E());
        holder.Time_ETxt.setText(MapeoEjercicio.getTIME_E());
        if(isAarmed(MapeoEjercicio.getNOMBRE_E(), MapeoEjercicio.getDURACION_E(), MapeoEjercicio.getDATE_E(),
                MapeoEjercicio.getEVENT_E(),MapeoEjercicio.getTIME_E())){
            holder.setAlarmEjercicio.setImageResource(R.drawable.ic_action_notification_on);

        }else{
            holder.setAlarmEjercicio.setImageResource(R.drawable.ic_action_notification_off);

        }
        Calendar datecalendar = Calendar.getInstance();
        datecalendar.setTime(ConvertirDeTextoAHora(MapeoEjercicio.getDATE_E()));
        final int alarmYear = datecalendar.get(Calendar.YEAR);
        final int alarmMonth = datecalendar.get(Calendar.MONTH);
        final int alarmDay = datecalendar.get(Calendar.DAY_OF_MONTH);
        Calendar timecalendar = Calendar.getInstance();
        timecalendar.setTime(ConvertirDeTextoATime(MapeoEjercicio.getTIME_E()));
        final int alarmHour = timecalendar.get(Calendar.HOUR_OF_DAY);
        final int alarmMinut = timecalendar.get(Calendar.MINUTE);


        holder.setAlarmEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAarmed(MapeoEjercicio.getNOMBRE_E(),MapeoEjercicio.getDURACION_E(), MapeoEjercicio.getDATE_E(),
                        MapeoEjercicio.getEVENT_E(),MapeoEjercicio.getTIME_E())){
                    holder.setAlarmEjercicio.setImageResource(R.drawable.ic_action_notification_off);
                    cancelAlarm(getRequestCode(MapeoEjercicio.getNOMBRE_E(),MapeoEjercicio.getDURACION_E(), MapeoEjercicio.getDATE_E(),
                            MapeoEjercicio.getEVENT_E(),MapeoEjercicio.getTIME_E()));
                    updateEvent(MapeoEjercicio.getNOMBRE_E(),MapeoEjercicio.getDURACION_E(), MapeoEjercicio.getDATE_E(),
                            MapeoEjercicio.getEVENT_E(),MapeoEjercicio.getTIME_E(), "off");

                }else {
                    holder.setAlarmEjercicio.setImageResource(R.drawable.ic_action_notification_on);
                    Calendar alarmCalendar = Calendar .getInstance();
                    alarmCalendar.set(alarmYear, alarmMonth, alarmDay, alarmDay, alarmHour, alarmMinut);
                    setAlarm(alarmCalendar, MapeoEjercicio.getEVENT_E(), MapeoEjercicio.getTIME_E(), getRequestCode(MapeoEjercicio.getNOMBRE_E(),MapeoEjercicio.getDURACION_E(), MapeoEjercicio.getDATE_E(),
                            MapeoEjercicio.getEVENT_E(),MapeoEjercicio.getTIME_E()));
                    updateEvent(MapeoEjercicio.getNOMBRE_E(),MapeoEjercicio.getDURACION_E(), MapeoEjercicio.getDATE_E(),
                            MapeoEjercicio.getEVENT_E(),MapeoEjercicio.getTIME_E(), "on");

                }
            }
        }) ;

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView Nombre_ETxt, Duracion_ETxt, Date_ETxt, Event_ETxt, Time_ETxt;
        Button EliminarExercise;
        ImageButton setAlarmEjercicio;
        public MyViewHolder (View itemView){
            super (itemView);

            Nombre_ETxt = itemView.findViewById(R.id.nombre_Ejercicio);
            Duracion_ETxt = itemView.findViewById(R.id.duracion_Ejercicio);
            Date_ETxt = itemView.findViewById(R.id.eventdate_ejercicio);
            Event_ETxt = itemView.findViewById(R.id.eventname_ejercicio);
            Time_ETxt = itemView.findViewById(R.id.eventtime_ejercicio);
            setAlarmEjercicio = itemView.findViewById(R.id.alarmmeBtnE);

        }
    }


    private Date ConvertirDeTextoAHora(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(eventDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }


    private Date ConvertirDeTextoATime(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("kk:mm", Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(eventDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
    private boolean isAarmed(String nombre_E, String duracion_E, String date_E, String event_E,
                             String time_E){
        boolean alarmed = false;
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoEjercicioPorIDNotificacion(nombre_E, duracion_E, date_E, event_E, time_E,  database);
        while (cursor.moveToNext()){
            String notificacionChida = cursor.getString(cursor.getColumnIndex(Estructure_Ejercicios.NOTIFICACION_E));
            if(notificacionChida.equals("on")){
                alarmed = true;
            }else{
                alarmed = false;
            }
        }
        cursor.close();
        dataBaseOpenHelper.close();
        return alarmed;
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

    private void cancelAlarm (int RequestCOde){
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiverEjercicios.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCOde, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
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

    private void updateEvent(String nombre_E, String duracion_E, String date_E, String event_E,
                             String time_E,  String notificacion_E){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.actualizarEjercicioPorNotificacion(nombre_E, duracion_E, date_E, event_E, time_E, notificacion_E, database);
        dataBaseOpenHelper.close();
    }
}
