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
import DataBaseEstructure.Estructure_Plan;
import DataBaseEstructure.plan_Map;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<plan_Map> arrayList;
    DataBaseOpenHelper dataBaseOpenHelper;

    public EventRecyclerAdapter(Context context, ArrayList<plan_Map> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_rowlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final plan_Map MapeoPlan = arrayList.get(position);
           holder.TipoComidaTxt.setText(MapeoPlan.getTIPO_CONCEPTO());
           holder.NombreTxt.setText(MapeoPlan.getNOMBRE_COMIDA());
           holder.EventTxt.setText(MapeoPlan.getEVENT());
           holder.TimeTxt.setText(MapeoPlan.getTIME());
            holder.DateTxt.setText(MapeoPlan.getDATE());
           if(isAarmed(MapeoPlan.getTIPO_CONCEPTO(), MapeoPlan.getNOMBRE_COMIDA(), MapeoPlan.getDATE(),
                   MapeoPlan.getEVENT(),MapeoPlan.getTIME())){
                holder.setAlarm.setImageResource(R.drawable.ic_action_notification_on);

           }else{
               holder.setAlarm.setImageResource(R.drawable.ic_action_notification_off);

           }
           Calendar datecalendar = Calendar.getInstance();
           datecalendar.setTime(ConvertirDeTextoAHora(MapeoPlan.getDATE()));
          final int alarmYear = datecalendar.get(Calendar.YEAR);
           final int alarmMonth = datecalendar.get(Calendar.MONTH);
           final int alarmDay = datecalendar.get(Calendar.DAY_OF_MONTH);
           Calendar timecalendar = Calendar.getInstance();
           timecalendar.setTime(ConvertirDeTextoATime(MapeoPlan.getTIME()));
           final int alarmHour = timecalendar.get(Calendar.HOUR_OF_DAY);
           final int alarmMinut = timecalendar.get(Calendar.MINUTE);

           holder.setAlarm.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(isAarmed(MapeoPlan.getTIPO_CONCEPTO(), MapeoPlan.getNOMBRE_COMIDA(), MapeoPlan.getDATE(),
                           MapeoPlan.getEVENT(),MapeoPlan.getTIME())){
                     holder.setAlarm.setImageResource(R.drawable.ic_action_notification_off);
                     cancelAlarm(getRequestCode(MapeoPlan.getTIPO_CONCEPTO(), MapeoPlan.getNOMBRE_COMIDA(), MapeoPlan.getDATE(),
                             MapeoPlan.getEVENT(),MapeoPlan.getTIME()));
                     updateEvent(MapeoPlan.getTIPO_CONCEPTO(), MapeoPlan.getNOMBRE_COMIDA(), MapeoPlan.getDATE(),
                             MapeoPlan.getEVENT(),MapeoPlan.getTIME(), "off");

                   }else {
                        holder.setAlarm.setImageResource(R.drawable.ic_action_notification_on);
                        Calendar alarmCalendar = Calendar .getInstance();
                        alarmCalendar.set(alarmYear, alarmMonth, alarmDay, alarmDay, alarmHour, alarmMinut);
                        setAlarm(alarmCalendar, MapeoPlan.getEVENT(), MapeoPlan.getTIME(), getRequestCode(MapeoPlan.getTIPO_CONCEPTO(), MapeoPlan.getNOMBRE_COMIDA(),
                                MapeoPlan.getDATE(), MapeoPlan.getEVENT(),MapeoPlan.getTIME()));
                        updateEvent(MapeoPlan.getTIPO_CONCEPTO(), MapeoPlan.getNOMBRE_COMIDA(), MapeoPlan.getDATE(),
                                MapeoPlan.getEVENT(),MapeoPlan.getTIME(), "on");

                   }
               }
           }) ;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView TipoComidaTxt, NombreTxt, DateTxt, EventTxt, TimeTxt;
        Button EliminarPlanner;
        ImageButton setAlarm;
        public MyViewHolder (View itemView){
            super (itemView);

            TipoComidaTxt = itemView.findViewById(R.id.tipocomida);
            NombreTxt = itemView.findViewById(R.id.nombre);
            DateTxt = itemView.findViewById(R.id.eventdate);
            EventTxt = itemView.findViewById(R.id.eventname);
            TimeTxt = itemView.findViewById(R.id.eventtime);
            setAlarm = itemView.findViewById(R.id.alarmmeBtn);

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
    private boolean isAarmed(String tipo_comida, String nombre_comida, String date, String event, String time){
        boolean alarmed = false;
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoPlanPorIDNotificacion(tipo_comida, nombre_comida, date, event, time,  database);
        while (cursor.moveToNext()){
            String notificacionChida = cursor.getString(cursor.getColumnIndex(Estructure_Plan.NOTIFICACION));
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

    private void setAlarm (Calendar calendar, String event, String time, int RequestCOde){
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        //intent.putExtra("tipo_concepto", tipo_concepto);
        intent.putExtra("event", event);
        intent.putExtra("time", time);
        intent.putExtra("id", RequestCOde);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCOde, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm (int RequestCOde){
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, RequestCOde, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager)context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    private int getRequestCode(String tipo_concepto, String nombre_comida, String date, String event, String time){
        int code = 0;

        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.LeyendoPlanPorIDNotificacion(tipo_concepto, nombre_comida, date, event, time,  database);
        while (cursor.moveToNext()){
            code = cursor.getInt(cursor.getColumnIndex(Estructure_Plan.ID));
        }
        cursor.close();
        dataBaseOpenHelper.close();
        return code;
    }

    private void updateEvent(String tipo_concepto, String nombre_comida, String date, String event, String time,  String notificacion){
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.actualizarPlanPorNotificacion(tipo_concepto, nombre_comida, date, event, time, notificacion, database);
        dataBaseOpenHelper.close();
    }
}
