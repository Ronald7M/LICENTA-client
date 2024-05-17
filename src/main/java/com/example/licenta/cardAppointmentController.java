package com.example.licenta;

import com.example.licenta.Entities.Appointment;
import com.example.licenta.Entities.AppointmentStatus;
import com.example.licenta.Entities.Tables;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class cardAppointmentController extends CON {

    private static int VIEW=1;
    private static int EDITABLE=2;
    private int type=1;

    @FXML
    private Label ServiceDuration;

    @FXML
    private Label dateService;

    @FXML
    private Label dateStatus;

    @FXML
    private Label nameResource;

    @FXML
    private Label nameStatus;

    @FXML
    private Label nameUser;

    private AppointmentStatus STATUS;

    @FXML
    private AnchorPane panel;



    public void setData(Tables service){
        STATUS=(AppointmentStatus) service;
        
    }
    public void setType(int type){
        this.type=type;
        init();
    }

    public void init(){
        
    }



    
}
