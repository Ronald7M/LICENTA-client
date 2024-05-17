package com.example.licenta.Auxiliary;

import com.example.licenta.Application;
import com.example.licenta.CON;
import com.example.licenta.Entities.Preferences;
import com.example.licenta.Entities.Service;
import com.example.licenta.Entities.Tables;
import com.example.licenta.Entities.User;
import com.example.licenta.newAppointmentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

import java.io.IOException;

public class handlerFxml {
   static Stage modalStage;

   public static void showAndWait(){
       modalStage.showAndWait();
   }
    public static CON openModalWithVBox(double x, double y,String fxml,String node,Tables tab,boolean showAndWait) {
        // Crează un VBox și adaugă noduri în el
        VBox vbox = new VBox();

        CON con=handlerFxml.addChildFxmlInContainer(fxml,node,vbox,tab);

        // Crează o scenă și adaugă VBox în ea


        // Creează o fereastră modală
        modalStage = new Stage();
        modalStage.setX( x);
        modalStage.setY( y);
        modalStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(vbox, 400, 125);
        // Setează scena în fereastra modală și afișează fereastra
        modalStage.setScene(scene);
        modalStage.initStyle(StageStyle.UNDECORATED);
        if(showAndWait)
        modalStage.showAndWait();

        return con;
    }



    public static  CON addFxmlInContainer(String fxml , Parent parent, Tables tab){
        FXMLLoader loader=getFxml(fxml);
        CON controller;
            try {
                Parent ap = loader.load();
                 controller =loader.getController();
                controller.setData(tab);
                setChildrenInParent(parent,ap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return controller;
    }

    public static CON addChildFxmlInContainer(String fxml,String idChildFxml,Parent parent,Tables tab){
        FXMLLoader loader=getFxml(fxml);
        CON controller;
        try {
            Parent root = loader.load();
             controller =loader.getController();
            controller.setData(tab);
            Node node = root.lookup(idChildFxml);
            setChildrenInParent(parent,(Parent)node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
     private static void setChildrenInParent(Parent parent,Parent child){
        if(parent instanceof VBox){
            VBox set =(VBox) parent;
            set.getChildren().add(child);
        }
         if(parent instanceof HBox){
             HBox set =(HBox) parent;
             set.getChildren().add(child);
         }
         if(parent instanceof AnchorPane){
             AnchorPane set =(AnchorPane) parent;
             set.getChildren().add(child);
         }
         if(parent instanceof FlowPane){
             FlowPane set =(FlowPane) parent;
             set.getChildren().add(child);

         }


     }

     private static FXMLLoader getFxml(String input){
         FXMLLoader fxmlLoader = new FXMLLoader();
         fxmlLoader.setLocation(Application.class.getResource(input));
         return fxmlLoader;
     }
     public static void close(){
        modalStage.close();
     }





}
