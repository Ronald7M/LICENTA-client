package com.example.licenta;

import com.example.licenta.Auxiliary.Schedule;
import com.example.licenta.DataBase.dataBase;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Stage stage;
    private double x;
    private double y;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
       FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = getClass().getResource("style/global.css").toExternalForm();
        scene.getStylesheets().add(css);


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

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        dataBase.getCon();

    }
    public static void   doAnimation(Parent parent){
        parent.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), parent);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public static void main(String[] args) {
        launch();
    }

}