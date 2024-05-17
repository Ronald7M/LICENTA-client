package com.example.licenta;

import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.SharedResource;
import com.example.licenta.Entities.Tables;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.ResultSet;

public class cardResourceController extends CON{


    @FXML
    private Button butt;

    @FXML
    private Label category;

    @FXML
    private Label name;

    private SharedResource RESOURCE;
    private newAppointmentController parentController;
    public void setData(Tables service){
        RESOURCE=(SharedResource) service;
        init();
    }
    void init(){
        name.setText(RESOURCE.getName());
        category.setText(String.valueOf(handlerDB.getCategoryById(RESOURCE.getIdCategory())));
    }
    public void setParentController(newAppointmentController parentController){
        this.parentController=parentController;
    }

    public void onClick(){
        parentController.setResource(RESOURCE);

    }


}
