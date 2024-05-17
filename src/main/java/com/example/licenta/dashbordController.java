package com.example.licenta;

import com.example.licenta.Auxiliary.handlerFxml;
import com.example.licenta.Auxiliary.panel;
import com.example.licenta.DataBase.dataBase;
import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class dashbordController  {

    private static User user;
    private static SharedResource myResource;

    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Button chatBut;
    @FXML
    private Button appointmentBut;
    @FXML
    private Button resourceBut;
    @FXML
    private Button minimizeBut;
    @FXML
    private Button closeBut;
    @FXML
    private Label nameLabel;
    @FXML
    private VBox appointmentVBox;
    @FXML
    private VBox resourceVBox;
    @FXML
    private AnchorPane mainPanel;



    private Timeline timer ;

    public static LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    private static  LocalDateTime currentDateTime;


    public void setUser(User user){
        this.user=user;
        nameLabel.setText(user.getName());
        myResource= handlerDB.getMyResource(user.getId());
        init();
    }

    private void setMenu(String select){
        if(select.equals("Chat")){
            appointmentVBox.setVisible(false);
            resourceVBox.setVisible(false);
            return;
        }else if(select.equals("Appointment")){
            appointmentVBox.setVisible(true);
            resourceVBox.setVisible(false);
        }else if(select.equals("Resource")){
            appointmentVBox.setVisible(false);
            resourceVBox.setVisible(true);
        }

    }
    private void init(){
        //Init timer
         timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //currentDateTime = LocalDateTime.now();
                currentDateTime=  LocalDateTime.of(2024,1,2,12,0,0);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String formattedTimeAndDate = currentDateTime.format(formatter);
                dateLabel.setText(formattedTimeAndDate.split(" ")[0]);
                timeLabel.setText(formattedTimeAndDate.split(" ")[1]);
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void onAppointment(){
        setMenu("Appointment");
    }
    public void onResource(){
        setMenu("Resource");
    }
    public void onChat(){
        setMenu("Chat");
    }
    public void onMyResource(){
        loadOnMainPanel("myResource.fxml");

    }
    public void onMyAppointment(){
        mainPanel.getChildren().clear();
        CON con=handlerFxml.addFxmlInContainer("confirmAndViewAppointment.fxml",mainPanel,myResource);
        confirmAndViewAppointmentController controller =(confirmAndViewAppointmentController) con;
        controller.setType(confirmAndViewAppointmentController.VIEW);
        controller.setToday(currentDateTime);
        controller.show();
    }
    public void onNewAppointment(){
        mainPanel.getChildren().clear();
        newAppointmentController con=(newAppointmentController)handlerFxml.addFxmlInContainer("newAppointment.fxml",mainPanel,myResource);


    }
    public void onNextAppointment(){

    }
   public void onMyConfirmation(){
       mainPanel.getChildren().clear();
       CON con=handlerFxml.addFxmlInContainer("confirmAndViewAppointment.fxml",mainPanel,myResource);
       confirmAndViewAppointmentController controller =(confirmAndViewAppointmentController) con;
       controller.setType(confirmAndViewAppointmentController.CONFIRM);
       controller.setToday(currentDateTime);
       controller.show();

   }
   public void onHistory(){

   }
    public void EXIT() {
        Platform.exit();
    }
    public void loadOnMainPanel(String nameFxml){
        mainPanel.getChildren().clear();
        handlerFxml.addFxmlInContainer(nameFxml,mainPanel,null);


    }
    public static User getUser(){
        return user;
    }




}
