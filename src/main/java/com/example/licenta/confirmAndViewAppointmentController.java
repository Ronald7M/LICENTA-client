package com.example.licenta;

import com.example.licenta.Auxiliary.handlerFxml;
import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.Appointment;
import com.example.licenta.Entities.SharedResource;
import com.example.licenta.Entities.Status;
import com.example.licenta.Entities.Tables;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class confirmAndViewAppointmentController extends CON {

    @FXML
    private AnchorPane mainPanel;
    public static int VIEW=1;
    public static int CONFIRM=2;
    private SharedResource resource;

    private int type=1;

    @FXML
    private Label Title;
    @FXML
    private Label lbFuture;

    @FXML
    private Label lbToday;

    @FXML
    private HBox vbFuture;

    @FXML
    private HBox vbToday;
    SharedResource myResource;


    private LocalDateTime today;

    public confirmAndViewAppointmentController() {
    }

    @Override
    public void setData(Tables resource) {
       if(resource instanceof SharedResource){
           myResource=(SharedResource) resource;
       }

    }
    public void setToday(LocalDateTime today){
        this.today=today;
    }
    public void setType(int input){
        type=input;
        changeType();
    }
    private void changeType(){
        if(type==CONFIRM){
            Title.setText("Confirm Appointments");
        }
        if(type==VIEW){
            Title.setText("View Appointments");

        }

    }
    public void show(){
        fillAll();
        Application.doAnimation(mainPanel);
    }

    public void fillAll(){
        if(myResource==null){
            return;
        }
        int var=-1;
        if(type==CONFIRM){
            var=Status.REQUESTED;
        }
        if(type==VIEW){
            var=Status.CONFIRMED;
        }

        List<Appointment> list = handlerDB.getAppointmentByResource(myResource);
        List<Appointment> todayList=new ArrayList<>();
        List<Appointment> futureList=new ArrayList<>();
        for (Appointment appointment : list) {
            if(appointment.getDateTime().toLocalDate().isEqual(today.toLocalDate())){
                if(handlerDB.getCurrentAppointmentStatus(appointment).getIdStatus()== var)
                             todayList.add(appointment);
            }
        }
        for (Appointment appointment : list) {
            if(appointment.getDateTime().toLocalDate().isAfter(today.toLocalDate())){
                if(handlerDB.getCurrentAppointmentStatus(appointment).getIdStatus()== var)
                        futureList.add(appointment);
            }
        }
        fill(type,vbToday,todayList);
        fill(type,vbFuture,futureList);


    }
    public void fill(int type , HBox where,List<Appointment> list ){
        if(list!=null){
            where.getChildren().clear();
            for (Appointment appointment : list) {
                cardConfirmViewController controller=(cardConfirmViewController)handlerFxml.addFxmlInContainer("cardConfirmView.fxml",where,appointment);
                controller.setType(type,where);
            }
        }
    }



}
