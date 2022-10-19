package com.example.prueba;

import android.content.Context;
import android.graphics.Color;
import android.icu.util.ULocale;
import android.provider.CalendarContract;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import DataBaseEstructure.plan_Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyGridAdapter extends ArrayAdapter {

    List<java.util.Date> dates;
    Calendar currentDate;
    List<plan_Map> events;
    LayoutInflater inflater;

    public MyGridAdapter(Context context, List<java.util.Date> dates, Calendar currentDate, List<plan_Map> events) {
        super(context, R.layout.single_cell_layout);

        this.dates = dates;
        this.currentDate = currentDate;
        this.events = events;
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int DayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH) + 1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int correntMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);

        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.single_cell_layout,parent, false);
        }
        if(displayMonth == correntMonth && displayYear == currentYear ){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.colorGrass));
        }
         else{
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }
         TextView Day_Number = view.findViewById(R.id.calendar_day);
         TextView EventNumber = view.findViewById(R.id.events_id);
         Day_Number.setText(String.valueOf(DayNo));
         Calendar eventCalendar = Calendar.getInstance();
         ArrayList<String> arrayList = new ArrayList<>();
         for(int i = 0; i < events.size();i++){
           eventCalendar.setTime(ConvertirDeTextoAHora(events.get(i).getDATE()));
           if(DayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH)+1 &&
            displayYear == eventCalendar.get(Calendar.YEAR)){
               arrayList.add(events.get(i).getEVENT());
               EventNumber.setText(arrayList.size()+" Plan");
           }
         }
        return  view;
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

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }
}
