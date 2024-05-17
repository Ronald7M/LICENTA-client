package com.example.licenta.Auxiliary;

import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.Appointment;
import com.example.licenta.Entities.Service;
import com.example.licenta.Entities.SharedResource;
import com.example.licenta.Entities.Status;
import com.example.licenta.LogInController;
import com.example.licenta.frameNewAppointmentController;
import com.example.licenta.newAppointmentController;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class handlerPanel {
    private VBox PARENT;
    private LocalDateTime DAY;
    private SharedResource RESOURCE;
    private Service SERVICE;
    private String FROM;
    private String TO;
    private String last;
    private  double scalabilityFactor=1;


    public  void createUiDay(VBox parent , LocalDateTime day , Service service, SharedResource myResource, String from , String to ){
        parent.getChildren().clear();
        SERVICE=service;
        PARENT=parent;
        DAY=day;
        RESOURCE=myResource;
        FROM=from;
        TO=to;


        List<Appointment> apps =getAppForToday();
        create(apps);

    }

    List<Appointment> getAppForToday(){
        List<Appointment> appointments = handlerDB.getAppointmentByResource(RESOURCE);
        List<Appointment> todayList=new ArrayList<>();
        for (Appointment appointment : appointments) {
            if(appointment.getDateTime().toLocalDate().isEqual(DAY.toLocalDate())){
                if(handlerDB.getCurrentAppointmentStatus(appointment).getIdStatus()==Status.CONFIRMED ||handlerDB.getCurrentAppointmentStatus(appointment).getIdStatus()==Status.REQUESTED)
                    todayList.add(appointment);
            }
        }

        //sort list
        Comparator<Appointment> comparator = Comparator.comparing(Appointment::getDateTime);
        Collections.sort(todayList, comparator);

        return todayList;
    }

    public static long calculeazaDiferentaTimp(String ora1, String ora2) {
        LocalTime timp1 = LocalTime.parse(ora1);
        LocalTime timp2 = LocalTime.parse(ora2);
        long diferentaMinute = ChronoUnit.MINUTES.between(timp1, timp2);

        return diferentaMinute;
    }


    private void create(List<Appointment> apps){

        last=FROM;
        add(60,panel.UNAVAILABLE,"00:00",FROM);
        for (Appointment app : apps) {
            int difTime=(int)calculeazaDiferentaTimp(last,app.getTimeAsString());
                if(difTime>0){
                    add((int)(difTime*scalabilityFactor),panel.FREE,last,app.getTimeAsString());
                }
            add((int)(handlerDB.getServiceByAppointment(app).getDuration()*scalabilityFactor),convertAppToStatusPanel(app),last,adunaOre(app.getTimeAsString(),handlerDB.getServiceByAppointment(app).getDurationAsString()));
        }

            add((int)calculeazaDiferentaTimp(last,TO),panel.FREE,last,TO);


        add(60,panel.UNAVAILABLE,TO,"00:00");

    }
    void showFrameNewAppointment(String from, String to){
        if(SERVICE==null){
            alertFrame af =new alertFrame("You must  choose a service!!!",alertFrame.INFORMATION);

            return;
        }

        Point point = MouseInfo.getPointerInfo().getLocation();
        double mouseX = point.getX()-300;
        double mouseY = point.getY();
        frameNewAppointmentController con= (frameNewAppointmentController)handlerFxml.openModalWithVBox(mouseX,mouseY,"frameNewAppointment.fxml","#nodeMain",RESOURCE,false);
        con.init(DAY,SERVICE,from,to);
        handlerFxml.showAndWait();
    }
    void add(int h,int panelType,String from ,String to){
        panel panelNew=new panel(h,panelType,from,to);
        panelNew.action(()->showFrameNewAppointment(from,to));

        PARENT.getChildren().add(panelNew);
        last=to;
    }
    public int convertAppToStatusPanel(Appointment app){
        if(app==null){
            return 0;
        }
        if(handlerDB.getCurrentAppointmentStatus(app).getIdStatus()==Status.CONFIRMED){
            return panel.BOOKED;
        }
        if(handlerDB.getCurrentAppointmentStatus(app).getIdStatus()==Status.REQUESTED){
            return panel.REQUESTED;
        }
        return 0;
    }
    public static String adunaOre(String ora1, String ora2) {
        // Descompunem orele și minutele din fiecare șir
        String[] parts1 = ora1.split(":");
        String[] parts2 = ora2.split(":");

        // Convertim orele și minutele la întregi
        int ore1 = Integer.parseInt(parts1[0]);
        int minute1 = Integer.parseInt(parts1[1]);
        int ore2 = Integer.parseInt(parts2[0]);
        int minute2 = Integer.parseInt(parts2[1]);

        // Adunăm orele și minutele
        int sumaOre = ore1 + ore2;
        int sumaMinute = minute1 + minute2;

        // Verificăm și ajustăm în cazul în care suma minutelor depășește 60
        if (sumaMinute >= 60) {
            sumaOre++;
            sumaMinute -= 60;
        }

        // Afișăm suma în formatul "hh:mm"
        String sumaOreString = String.format("%02d:%02d", sumaOre, sumaMinute);

        return sumaOreString;
    }



}
