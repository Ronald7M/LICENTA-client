package com.example.licenta;

import com.example.licenta.Auxiliary.Schedule;
import com.example.licenta.Auxiliary.handlerFxml;
import com.example.licenta.Auxiliary.handlerPanel;
import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class newAppointmentController extends CON {
    @FXML
    private Button butBack;
    @FXML
    private Button BACK;

    @FXML
    private Button butNext;

    @FXML
    private ChoiceBox<Service> cbService;

    @FXML
    private FlowPane gridShow;

    @FXML
    private Label lbCategory;

    @FXML
    private Label lbDay;
    @FXML
    private Label lbDayOfWeek;

    @FXML
    private Label lbDescription;
    @FXML
    private Label lbName;

    @FXML
    private Label lbSchedule;

    @FXML
    private AnchorPane nodResource;

    @FXML
    private AnchorPane nodSearch;

    @FXML
    private Button butPref;

    @FXML
    private VBox showPanel;

    @FXML
    private TextField textSearch;

    @FXML
    private VBox vShow;

    private LocalDateTime today ;

    private static int SEARCH =1;
    private static int RESOURCE =2;

    private Service SERVICE;
    private handlerPanel hp;
    public static  boolean flagUpdate=false;


    private SharedResource myResource;

    public void setData(Tables service){
        myResource=(SharedResource) service;
        setLayer(SEARCH);
        init();
    }
    void init(){
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            onSearch();
        });


    }



    void setLayer(int type){
        nodResource.setVisible(false);
        nodSearch.setVisible(false);
        if(type==SEARCH){
            initSearchLayer();
            Application.doAnimation(nodSearch);
        }
        if(type==RESOURCE){
            if(myResource!=null)
            Application.doAnimation(nodResource);
        }
    }

    private void initSearchLayer() {
        List<Preferences> pref= handlerDB.getMyPreference(dashbordController.getUser());
        if(pref==null){
            return;
        }
        vShow.getChildren().clear();
        for (Preferences preferences : pref) {
            cardResourceController controller =(cardResourceController)handlerFxml.addFxmlInContainer("cardResource.fxml",vShow,handlerDB.getResourceById(preferences.getIdResource()));
            controller.setParentController(this);
        }

    }


    public void setResource(SharedResource resource){
        this.myResource=resource;
        checkAddPrefBut();
        setLayer(RESOURCE);
        initResourceLayer();
        updateShowPanel();
        cbService.setOnAction((
                event)->{SERVICE=cbService.getValue();
                updateShowPanel();
        });

    }
    void checkAddPrefBut(){
        if(handlerDB.thereIsPref(dashbordController.getUser().getId(), myResource.getIdResource())){
            butPref.setText("-");
        }else{
            butPref.setText("+");
        }
    }
    private void initResourceLayer(){
        today=dashbordController.getCurrentDateTime();
        lbDay.setText(extractDateAsString(today));
        lbDayOfWeek.setText(getDayOfWeekAsString(today));
        lbDescription.setText(myResource.getDescription());
        lbName.setText(myResource.getName());
        lbCategory.setText(handlerDB.getCategoryById(myResource.getIdCategory()).getName());
        lbSchedule.setText(myResource.getSchedule().getScheduleText());

        List<Service> list=handlerDB.getServicesByResource(myResource.getIdResource());
        cbService.getItems().clear();
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getDelete()==false)
            cbService.getItems().add(list.get(i));
        }
        Service info =new Service(0,0,0," ",false){
            public String toString() {
                return "Choose your Service"  ;
            }
        };
        cbService.setValue(info);
        cbService.setOnMouseClicked(event -> {
            cbService.getItems().remove(info);
        });
    }
    private void  updateShowPanel(){
         hp = new handlerPanel();
        String from=myResource.getSchedule().getFromOfWeekDay(today);
        String to=myResource.getSchedule().getToOfWeekDay(today);
        hp.createUiDay(showPanel,today,SERVICE,myResource,from,to);
        if(today==dashbordController.getCurrentDateTime()){
            butBack.setDisable(true);
        }else{
            butBack.setDisable(false);
        }
        lbDayOfWeek.setText(getDayOfWeekAsString(today));
    }



    public static String extractDateAsString(LocalDateTime dateTime) {
        LocalDateTime date = dateTime.toLocalDate().atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
    public void onSearch(){
        gridShow.getChildren().clear();
        List<SharedResource> all = handlerDB.getSharedResource();
        for (SharedResource res : all) {
            if(res.getName().toLowerCase().contains(textSearch.getText().toLowerCase())==true && textSearch.getText().equals("")==false){
                cardResourceController controller=(cardResourceController) handlerFxml.addFxmlInContainer("cardResource.fxml",gridShow,res);
                controller.setParentController(this);

            }
        }
    }

    public void onNextDay(){
        today=today.plusDays(1);
        lbDay.setText(extractDateAsString(today));
        updateShowPanel();
    }
    public void onBackDay(){
        today=today.minusDays(1);
        lbDay.setText(extractDateAsString(today));
        updateShowPanel();
    }
    public void onBackButton(){
        setLayer(SEARCH);
        textSearch.setText("");

    }
    public  String getDayOfWeekAsString(LocalDateTime date){
        int dayOfWeek =date.getDayOfWeek().getValue();
       if(dayOfWeek==1)
           return "Mon";
        if(dayOfWeek==2)
            return "Tue";
        if(dayOfWeek==3)
            return "Wed";
        if(dayOfWeek==4)
            return "Thu";
        if(dayOfWeek==5)
            return "Fri";
        if(dayOfWeek==6)
            return "Sat";
        if(dayOfWeek==7)
            return "Sun";

       return "0";
    }

    public void onButPref(){
        if(butPref.getText().equals("-")){
            handlerDB.deletePreference(new Preferences(dashbordController.getUser().getId(), myResource.getIdResource()));
            butPref.setText("+");
            return;
        }
        if(butPref.getText().equals("+")){
            handlerDB.addNewPref(new Preferences(dashbordController.getUser().getId(), myResource.getIdResource()));
            butPref.setText("-");
        }
    }




}
