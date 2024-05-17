package com.example.licenta;

import com.example.licenta.Auxiliary.alertFrame;
import com.example.licenta.Auxiliary.handlerFxml;
import com.example.licenta.DataBase.handlerDB;
import com.example.licenta.Entities.Appointment;
import com.example.licenta.Entities.Service;
import com.example.licenta.Entities.SharedResource;
import com.example.licenta.Entities.Tables;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class frameNewAppointmentController extends CON  {

    @FXML
    private Button butDone;

    @FXML
    private Button butExit;

    @FXML
    private Label lbInfo;

    @FXML
    private TextField tfTime;

    private SharedResource RESOURCE;
    private String FROM;
    private String TO;
    private LocalDateTime DAY;
    public  Service SERVICE;



    @Override
    public void setData(Tables service) {
        RESOURCE=(SharedResource) service;
    }
     public void init(LocalDateTime day,Service service,String from,String to){
        DAY=day;
        FROM=from;
        SERVICE=service;
        TO=to;
         initUI();
    }

    private void initUI(){
        lbInfo.setText(FROM+"-"+TO);
        tfTime.setText(FROM);

    }
    public static String subtractMinutes(String time, int minutesToSubtract) {
        String[] timeSplit = time.split(":");
        int hours = Integer.parseInt(timeSplit[0]);
        int minutes = Integer.parseInt(timeSplit[1]);
        int totalMinutes = hours * 60 + minutes;
        totalMinutes -= minutesToSubtract;
        totalMinutes = (totalMinutes + 1440) % 1440;
        hours = totalMinutes / 60;
        minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
    public void closeModal(){
        handlerFxml.close();
    }
    public static boolean checkInput(String text) {
        String regex = "^\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public void doNewAppointment(){

      if(checkInput(tfTime.getText())){
            if(checkInterval(FROM,subtractMinutes(TO,SERVICE.getDuration()),tfTime.getText())){
                String time=tfTime.getText();
                int h= Integer.parseInt(time.split(":")[0]);
                int min= Integer.parseInt(time.split(":")[1]);
                LocalDateTime lc= LocalDateTime.of(DAY.getYear(),DAY.getMonthValue(),DAY.getDayOfMonth(),h,min,0);
                handlerDB.makeNewAppointment(new Appointment(-1,dashbordController.getUser().getId(),SERVICE.getIdService(),lc));
                 handlerFxml.close();
                newAppointmentController.flagUpdate=true;

            }else{
                alertFrame af= new alertFrame("The Time you selected is not available !!!",alertFrame.INFORMATION);
            }

        }else{
          alertFrame af= new alertFrame("Input format wrong !!!",alertFrame.INFORMATION);

      }
        System.out.println("END");


    }


    private  boolean checkInterval(String s1, String s2, String s3) {
        int time1 = covertInMin(s1);
        int time2 = covertInMin(s2);
        int time3 = covertInMin(s3);
        return time3 >= time1 && time3 <= time2;
    }

    private  int covertInMin(String time) {
        String[] timpSplit = time.split(":");
        int h = Integer.parseInt(timpSplit[0]);
        int min = Integer.parseInt(timpSplit[1]);
        return h * 60 + min;
    }


}
