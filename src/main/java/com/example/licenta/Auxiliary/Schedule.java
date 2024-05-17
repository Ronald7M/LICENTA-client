package com.example.licenta.Auxiliary;

import com.example.licenta.Entities.Tables;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    public static final String MON="mon";
    public static final String TUE="tue";
    public static final String WED="wed";
    public static final String THU="thu";
    public static final String FRI="fri";
    public static final String SAT="sat";
    public static final String SUN="sun";
    public List<day> weekDays;

    public List<day> getWeekDays() {
        return weekDays;
    }

    public Schedule(){
         weekDays= new ArrayList<>();
        weekDays.add(new day(MON,"08:00","19:00"));
        weekDays.add(new day(TUE,"08:00","19:00"));
        weekDays.add(new day(WED,"08:00","19:00"));
        weekDays.add(new day(THU,"08:00","19:00"));
        weekDays.add(new day(FRI,"08:00","19:00"));
        weekDays.add(new day(SAT,"08:00","19:00"));
        weekDays.add(new day(SUN,"08:00","19:00"));
    }

    public void addDay(String day ,String From,String To){
        for (day weekDay : weekDays) {
            if(weekDay.getDay().equals(day)){
                weekDay.setFrom(From);
                weekDay.setTo(To);
            }
        }
    }

    public String getScheduleCode(){
        String result="";
        for (day weekDay : weekDays) {
            result=result+weekDay.getDay()+"/"+weekDay.getFrom()+"/"+weekDay.getTo()+">";
        }

        return result;
    }


    public String getScheduleText(){
        String result="";
        for (day weekDay : weekDays) {
            result=result+weekDay.getDay()+" : "+weekDay.getFrom()+" - "+weekDay.getTo()+"\n";
        }
        return result;
    }

    public  void  setScheduleByCode(String code){
        String days[]=code.split(">");
        for (String day : days) {
            addDay(day.split("/")[0],day.split("/")[1],day.split("/")[2]);
        }

    }

    public String getFromOfWeekDay(LocalDateTime date){
        int dayOfWeek=getDayOfWeekFromDate(date);

        return weekDays.get(dayOfWeek-1).getFrom();
    }

    public String getToOfWeekDay(LocalDateTime date){
        int dayOfWeek=getDayOfWeekFromDate(date);
        return weekDays.get(dayOfWeek-1).getTo();
    }
    private   int getDayOfWeekFromDate(LocalDateTime date){
        int dayOfWeek =date.getDayOfWeek().getValue();
        return dayOfWeek;

    }


}
