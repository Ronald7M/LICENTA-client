package com.example.licenta;
 import com.example.licenta.Auxiliary.alertFrame;
 import com.example.licenta.DataBase.handlerDB;
 import javafx.animation.ScaleTransition;
 import javafx.scene.control.TextField;
import com.example.licenta.Auxiliary.handlerFxml;
import com.example.licenta.Entities.Service;
import com.example.licenta.Entities.Tables;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

 import javafx.scene.image.ImageView;
 import javafx.util.Duration;

 import java.awt.*;
import java.io.IOException;
 import java.util.List;

 import static javafx.scene.paint.Color.TRANSPARENT;

public class cardServiceResourceController extends CON {

        public static boolean frameAddNewServiceIsShow=false;

        private static VBox CARDPLACE;

    @FXML
    private AnchorPane nodeAdd;

    @FXML
    private Button butConfirmationAdd;


    @FXML
    private Button butExitAdd;

    @FXML
    private TextField textAddDuration;

    @FXML
    private TextField textAddName;


    @FXML
    private ImageView butAdd;

    @FXML
    private TextField chDuration;

    @FXML
    private TextField chName;


    @FXML
    private AnchorPane settings;

    @FXML
    private AnchorPane nodeId;

    @FXML
    private Button butEdit;

    @FXML
    private Label duration;

    @FXML
    private Label name;

    private Service myServices;
    private static myResourceController parentController;
    public cardServiceResourceController() {
    }

    public void setData(Tables service) {
        if (service == null) {
            initAddAsButton();
        }
        if (service instanceof Service) {
            Service s = (Service) service;
            this.myServices = s;
            init();
        }
    }
    public void setParentController(myResourceController parentController){
        this.parentController=parentController;
    }
        private void init(){
            name.setText(myServices.getNameService());
            duration.setText(String.valueOf(myServices.getDuration()));
            chDuration.setText(String.valueOf(myServices.getDuration()));
            chName.setText(myServices.getNameService());
        }

        public void openModalWithVBox(){
            Point point = MouseInfo.getPointerInfo().getLocation();
            double mouseX = point.getX();
            double mouseY = point.getY();
            handlerFxml.openModalWithVBox(mouseX,mouseY,"cardServiceResource.fxml","#settings",myServices,true);

        }

        public void show(){
            System.out.println(name.getText());
        }
        public void closeModal(){
            handlerFxml.close();
        }
        private void initAddAsButton(){
            butAdd.setOnMouseEntered(event -> {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), butAdd);
                scaleTransition.setToX(1.5); // Scalarea pe axa X la 1.5x
                scaleTransition.setToY(1.5); // Scalarea pe axa Y la 1.5x
                scaleTransition.play();
            });


            butAdd.setOnMouseExited(event -> {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), butAdd);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.play();
            });


            butAdd.setOnMouseClicked(event -> {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), butAdd);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.play();

                scaleTransition.play();
                onButAddClick();
            });
        }
        public static void setCARDPLACE(VBox CARDPLACE){
            cardServiceResourceController.CARDPLACE =CARDPLACE;
        }

         void onButAddClick(){

            if(frameAddNewServiceIsShow==false){
                handlerFxml.addChildFxmlInContainer("cardServiceResource.fxml","#nodeAddFrame",CARDPLACE, null);
                frameAddNewServiceIsShow=true;
            }else{
                hideFrameAddService();
                frameAddNewServiceIsShow=false;
            }
        }
        public  void hideFrameAddService(){
            String idToDelete = "nodeAddFrame";
            CARDPLACE.getChildren().removeIf(node -> node.getId() != null && node.getId().equals(idToDelete));
        }

        public void test(){
            try {
                int duration = Integer.parseInt(textAddDuration.getText());
                myResourceController.addNewService(textAddName.getText(),duration);
                uppdate();
            } catch (NumberFormatException e) {
                alertFrame f = new alertFrame("Letters cannot be entered in the duration field", alertFrame.INFORMATION);
            }
        }

    public void uppdate(){
        CARDPLACE.getChildren().clear();
        List <Service> services =   handlerDB.getServicesByResource(myResourceController.getMyResource().getIdResource());
        for (Service service : services) {
            if(service.getDelete()==false)
            handlerFxml.addChildFxmlInContainer("cardServiceResource.fxml","#nodeId",CARDPLACE, service);
        }
        handlerFxml.addChildFxmlInContainer("cardServiceResource.fxml","#nodeAdd",CARDPLACE, null);
        cardServiceResourceController.setCARDPLACE(CARDPLACE);
    }

    public void onEdit(){
        handlerDB.updateService(myServices,chName.getText());
        alertFrame ad = new alertFrame("Your service has been updated!!",alertFrame.INFORMATION);
        closeModal();
        parentController.onViewServices();



    }
    public void onDelete(){
        handlerDB.deleteService(myServices);
        alertFrame ad = new alertFrame("Your service has been deleted!!",alertFrame.INFORMATION);
        closeModal();
        parentController.onViewServices();
    }

}
