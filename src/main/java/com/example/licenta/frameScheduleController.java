package com.example.licenta;

import com.example.licenta.Auxiliary.Schedule;
import com.example.licenta.Auxiliary.day;
import com.example.licenta.Entities.SharedResource;
import com.example.licenta.Entities.Tables;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class frameScheduleController extends  CON{

    @FXML
    private Button closeBut;

    @FXML
    private TextField friF;

    @FXML
    private TextField friT;

    @FXML
    private TextField monF;

    @FXML
    private TextField monT;

    @FXML
    private TextField satF;

    @FXML
    private TextField satT;

    @FXML
    private Button saveBut;

    @FXML
    private TextField sunF;

    @FXML
    private TextField sunT;

    @FXML
    private TextField thuF;

    @FXML
    private TextField thuT;

    @FXML
    private TextField tueF;

    @FXML
    private TextField tueT;

    @FXML
    private TextField wedF;

    @FXML
    private TextField wedT;
    private static Schedule SCHEDULE;
    public void setData(Tables service){

    }


    public void setSchedule(Schedule sh){
        SCHEDULE=sh;
        init();
    }
    public void init(){
       List<day> list= SCHEDULE.getWeekDays();
       monF.setText(list.get(0).getFrom());
       monT.setText(list.get(0).getTo());

        tueF.setText(list.get(1).getFrom());
        tueT.setText(list.get(1).getTo());

        wedF.setText(list.get(2).getFrom());
        wedT.setText(list.get(2).getTo());

        thuF.setText(list.get(3).getFrom());
        thuT.setText(list.get(3).getTo());

        friF.setText(list.get(4).getFrom());
        friT.setText(list.get(4).getTo());

        satF.setText(list.get(5).getFrom());
        satT.setText(list.get(5).getTo());

        sunF.setText(list.get(6).getFrom());
        sunT.setText(list.get(6).getTo());
    }

    public  boolean getSchedule(){
        if(checkInput(monF.getText())==false)
            return false;
        if(checkInput(monT.getText())==false)
            return false;
        if(checkInput(tueF.getText())==false)
            return false;
        if(checkInput(tueT.getText())==false)
            return false;
        if(checkInput(wedF.getText())==false)
            return false;
        if(checkInput(wedT.getText())==false)
            return false;
        if(checkInput(tueF.getText())==false)
            return false;
        if(checkInput(tueT.getText())==false)
            return false;
        if(checkInput(friF.getText())==false)
            return false;
        if(checkInput(friT.getText())==false)
            return false;
        if(checkInput(satF.getText())==false)
            return false;
        if(checkInput(satT.getText())==false)
            return false;
        if(checkInput(sunF.getText())==false)
            return false;
        if(checkInput(sunT.getText())==false)
            return false;


        SCHEDULE.addDay(Schedule.MON,monF.getText(),monT.getText());
        SCHEDULE.addDay(Schedule.TUE,tueF.getText(),tueT.getText());
        SCHEDULE.addDay(Schedule.WED,wedF.getText(),wedT.getText());
        SCHEDULE.addDay(Schedule.THU,thuF.getText(),thuT.getText());
        SCHEDULE.addDay(Schedule.FRI,friF.getText(),friT.getText());
        SCHEDULE.addDay(Schedule.SAT,satF.getText(),satT.getText());
        SCHEDULE.addDay(Schedule.SUN,sunF.getText(),sunT.getText());
        return true;
    }

    public static boolean checkInput(String text) {
        String regex = "^\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }




}
