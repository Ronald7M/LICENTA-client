package com.example.licenta;

import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.Appointment;
import com.example.licenta.Entities.AppointmentStatus;
import com.example.licenta.Entities.Status;
import com.example.licenta.Entities.Tables;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class cardConfirmViewController extends CON {
    public static int VIEW=1;
    public static int CONFIRM=2;
    private int type=1;
    private String Color="#5CF591";

    @FXML
    private AnchorPane panel;
    @FXML
    private ImageView ivConfirm;

    @FXML
    private ImageView ivIgnore;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbDuration;

    @FXML
    private Label lbName;

    @FXML
    private Label lbServiceName;

    private Appointment myApp;
    private HBox parent;

    public void setData(Tables appointment){
        if(appointment instanceof Appointment){
            myApp=(Appointment) appointment;
           init();
        }

    }
    public void setType(int input,HBox parent){
        this.parent=parent;
        type=input;
        if(type==CONFIRM){
            ivConfirm.setVisible(true);
            ivIgnore.setVisible(true);
        }
        if(type==VIEW){
            ivConfirm.setVisible(false);
            ivIgnore.setVisible(false);
        }

    }

    private void init(){
        lbName.setText(handlerDB.getUserByAppointment(myApp).getName());
        lbServiceName.setText(handlerDB.getServiceByAppointment(myApp).getNameService());
        lbDuration.setText(String.valueOf(handlerDB.getServiceByAppointment(myApp).getDuration()));
        lbDate.setText(myApp.getDateAsString());
        setColor();
    }

    void setColor() {
        String style = "-fx-background-color: " + Color + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-radius: 10px; " +
                "-fx-border-color: #2980b9;" +
                "-fx-border-width: 2px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);";
        panel.setStyle(style);
    }

    public void onConfirmApp(){
        handlerDB.addNewAppointmentStatus(new AppointmentStatus(myApp.getIdAppointment(), Status.CONFIRMED, dashbordController.getCurrentDateTime()));
        panel.setDisable(true);
        animateAndRemove(parent,panel);
    }

    public void onCancelApp(){
        handlerDB.addNewAppointmentStatus(new AppointmentStatus(myApp.getIdAppointment(), Status.COMPANY_CANCEL, dashbordController.getCurrentDateTime()));
        panel.setDisable(true);
        animateAndRemove(parent,panel);
    }

    private void animateAndRemove(HBox hbox, AnchorPane button) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), button);
        transition.setOnFinished(event -> hbox.getChildren().remove(button));
        transition.setByX(button.getWidth());
        transition.play();
    }
}
