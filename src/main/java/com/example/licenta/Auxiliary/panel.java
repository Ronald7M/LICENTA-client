package com.example.licenta.Auxiliary;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


import static javafx.scene.paint.Color.LIGHTBLUE;
interface action {
    void execute();
}


public class panel extends AnchorPane {

    public static int FREE=1;
    public static int BOOKED=2;
    public static int REQUESTED=4;
    public static int UNAVAILABLE=3;
    private String sTooltip;
    Tooltip tooltip = new Tooltip();
    private String sFrom="13:40";
    private String sTo="16:52";
    private int H;
    private String Color1="#808080";
    private String Color2="#000000";
    private Label from;
    private Label to;
    private action ac;



    int type=0;
   public panel(int H,int type,String from, String to ){
       super();
       sFrom=from;
       sTo=to;
       this.type=type;
       this.H=H+30;
       this.setPrefHeight(H);
       this.setPrefWidth(130);
       settingsForType();
       addStyle();
       handlerLabels();
       tooltip.setText(sTooltip);
       tooltip.setShowDelay(Duration.millis(500));
       Tooltip.install(this,tooltip);
       this.setPickOnBounds(false);
       this.setOnMouseClicked(this::handleAnchorPaneClick);

   }

    private void handleAnchorPaneClick(MouseEvent mouseEvent) {
       if(type==FREE){

          ac.execute();
       }

    }
    public void action(action ac){
      this.ac=ac;
    }


    void handlerLabels(){
       Font f=new Font("Arial", 16);
        //Font fb = Font.font(f.getFamily(), FontWeight.BOLD, f.getSize());
        from=new Label();
        to=new Label();
        from.setFont(f);
        to.setFont(f);

       from.setText(sFrom);
       to.setText(sTo);

       from.setLayoutX(5);
       from.setLayoutX(5);

       to.setLayoutX(5);
       to.setLayoutY(H);

       to.setMaxSize(100,15);


       this.getChildren().add(from);
       this.getChildren().add(to);

   }
   void addStyle(){
       String style = "-fx-background-color: linear-gradient(to bottom right, "+Color1+", "+Color2+");" +
               "-fx-background-radius: 10px;" +
               "-fx-border-radius: 10px; " +
               "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 20, 0, 0, 0);";

       this.setStyle(style);
   }
   private void settingsForType(){
       if(type==BOOKED){
           Color1="#FF0000";
           Color2="#C90000";
           sTooltip="This interval is booked";
       }
       if(type==UNAVAILABLE){
           Color1="#403B4A";
           Color2="#939394";
           sTooltip="Out of Schedule";
       }
       if(type==FREE){
           Color1="#28C700";
           Color2="#1F9900";
           sTooltip="This interval is available";
       }
       if(type==REQUESTED){
           Color1="#FFE000";
           Color2="#799F0C";
           sTooltip="This interval waiting for confirmation";
       }
   }




}
