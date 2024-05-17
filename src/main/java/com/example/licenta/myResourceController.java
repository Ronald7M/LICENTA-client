package com.example.licenta;


import com.example.licenta.Auxiliary.handlerFxml;
import com.example.licenta.Auxiliary.alertFrame;
import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class myResourceController extends CON implements Initializable {

    @FXML
    private VBox scSchedule;

    private User user;
    @FXML
    private   VBox cardPlace;
    @FXML
    private ChoiceBox<Category> chCategory;

    @FXML
    private TextArea chDescription;

    @FXML
    private TextField chName;

    @FXML
    private TextArea chSchedule;

    @FXML
    private Button butCreate;

    @FXML
    private TextField chooseName;
    @FXML
    private VBox noResource;

    @FXML
    private VBox changeResource;

    @FXML
    private VBox chooseNameResource;

    @FXML
    private VBox viewResource;

    @FXML
    private Label lbCategory;

    @FXML
    private Label lbDescription;

    @FXML
    private Label lbName;

    @FXML
    private VBox viewServices;

    @FXML
    private Label lbSchedule;
    private static SharedResource myResource;
    private frameScheduleController conFrameSchedule;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user=dashbordController.getUser();
        myResource= handlerDB.getMyResource(user.getId());
       setFirstLayer();

    }
    private void layerOff(){
        noResource.setVisible(false);
        changeResource.setVisible(false);
        chooseNameResource.setVisible(false);
        viewResource.setVisible(false);
        viewServices.setVisible(false);
    }

    private void setLayer(String input){
        layerOff();
        myResource= handlerDB.getMyResource(user.getId());
        if(input.equals("withoutR")){
            noResource.setVisible(true);
            Application.doAnimation(noResource);
        }
        if(input.equals("changeR")){
            changeResource.setVisible(true);
            Application.doAnimation(changeResource);
            chName.setText(myResource.getName());
            chDescription.setText(myResource.getDescription());
            conFrameSchedule=(frameScheduleController) handlerFxml.addFxmlInContainer("frameSchedule.fxml",scSchedule,null);
             conFrameSchedule.setSchedule(myResource.getSchedule());
            addCat();

        }
        if(input.equals("chooseNameR")){
            chooseNameResource.setVisible(true);
            Application.doAnimation(chooseNameResource);
        }
        if(input.equals("viewR")){
            viewResource.setVisible(true);
            Application.doAnimation(viewResource);
            lbName.setText(myResource.getName());
            lbCategory.setText(handlerDB.getCategoryById(myResource.getIdCategory()).getName());
            lbDescription.setText(myResource.getDescription());
            lbSchedule.setText(myResource.getSchedule().getScheduleText());

        }
        if(input.equals("viewServices")){
            viewServices.setVisible(true);
            Application.doAnimation(viewServices);
            addInCardPlace();
        }
    }


    public void onNoResource(){
        setLayer("withoutR");
    }
    public void onChangeResource(){
        setLayer("changeR");
    }
    public  void onChooseNameResource(){
        setLayer("chooseNameR");
    }
    public void onViewServices(){
        setLayer("viewServices");

    };
    public void onViewResource(){
        setLayer("viewR");

    }
    public void createResource(){
        handlerDB.createResource(new SharedResource(user.getId(), chooseName.getText()));
        onViewResource();
    }

    void setFirstLayer(){
        if(myResource==null){
            setLayer("withoutR");
        }else{
            setLayer("viewR");
        }
    }

    void addCat(){
        List<Category> listCategories=handlerDB.getCategories();
        chCategory.getItems().clear();
        for (int i = 0; i <listCategories.size() ; i++) {
            chCategory.getItems().add(listCategories.get(i));
        }
        chCategory.setValue(handlerDB.getCategoryById(myResource.getIdCategory()));
        chCategory.setOnAction(this::changeCategoryInMyResource);
        chCategory.setOnMouseEntered(event -> {
            String selectedItem = chCategory.getSelectionModel().getSelectedItem().getDescription();

                Tooltip tooltip = new Tooltip(selectedItem);
                tooltip.setShowDelay(Duration.seconds(1));
                Tooltip.install(chCategory, tooltip);

        });

        chCategory.setOnMouseExited(event -> {
            Tooltip.uninstall(chCategory, null);
        });
    }
    void changeCategoryInMyResource(ActionEvent event){
        myResource.setIdCategory(chCategory.getValue().getIdCategory());
        System.out.println(myResource.getIdCategory());
    }
    void changeResourceInDB(){
        myResource.setName( chName.getText());
        myResource.setDescription(chDescription.getText());
        if(conFrameSchedule.getSchedule()==false){
            alertFrame af =new alertFrame("Input format wrong",alertFrame.INFORMATION);
            return;
        }


        handlerDB.changeResource(myResource);
        onViewResource();
    }
    public void onChangeFinal(){
        alertFrame q = new alertFrame(alertFrame.CONFIRMATION){
            public void Yes(){
                changeResourceInDB();
            }
        };

    }


    public void addInCardPlace(){
        cardPlace.getChildren().clear();
        List <Service> services =   handlerDB.getServicesByResource(myResource.getIdResource());
        for (Service service : services) {
            if(service.getDelete()==false) {
                cardServiceResourceController con = (cardServiceResourceController) handlerFxml.addChildFxmlInContainer("cardServiceResource.fxml", "#nodeId", cardPlace, service);
                con.setParentController(this);
            }
        }
        cardServiceResourceController con=(cardServiceResourceController)handlerFxml.addChildFxmlInContainer("cardServiceResource.fxml","#nodeAdd",cardPlace, null);
        con.setParentController(this);
        cardServiceResourceController.setCARDPLACE(cardPlace);
    }


    public static void  addNewService(String name, int duration){
        handlerDB.addService(new Service(-1,myResource.getIdResource(),duration,name,false));
        alertFrame f = new alertFrame("Your service has been added", alertFrame.INFORMATION);

    }
    public static SharedResource getMyResource(){
        return myResource;
    }









}
