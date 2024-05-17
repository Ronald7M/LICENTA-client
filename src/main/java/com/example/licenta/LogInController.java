package com.example.licenta;

import com.example.licenta.DataBase.dataBase;
import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogInController {
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button butLogin;
    public static Stage stage;

    @FXML
    protected void onHelloButtonClick() throws IOException {


/*
        usernameInput.setText("eddie_davis");
        passwordInput.setText("EddiePass2022");



        usernameInput.setText("testu");
        passwordInput.setText("testp");

        usernameInput.setText("jane_smith");
        passwordInput.setText("SecurePass321");*/




       User check= handlerDB.checkLogin(usernameInput.getText(),passwordInput.getText());
       if(check==null){
           System.out.println("ERR");
       }else{
           System.out.println(check.getName());
           loadDashBoard(check);
       }
    }
    @FXML
    protected void EXIT() {
        Platform.exit();
    }
    private double x;
    private double y;
    public void loadDashBoard(User user) {
        try {
            // Încarcă fișierul FXML secundar
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            dashbordController  dashcontroller= loader.getController();
            dashcontroller.setUser(user);
            Scene scene = new Scene(root);
             stage = (Stage) butLogin.getScene().getWindow();
            stage.setScene(scene);
            String css = getClass().getResource("style/global.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.show();
            setSetiings(scene,stage);
        } catch (Exception e) {
            e.printStackTrace(); // Gestionează excepțiile corespunzător
        }
    }

    private void setSetiings(Scene scene,Stage stage){

        scene.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y=event.getSceneY();

        });
        scene.setOnMouseDragged((MouseEvent event)->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
            stage.setOpacity(0.8);

        });
        scene.setOnMouseReleased((MouseEvent event)->{
            stage.setOpacity(1);
        });

    }



}