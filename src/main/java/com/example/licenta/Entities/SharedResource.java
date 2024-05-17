package com.example.licenta.Entities;


import com.example.licenta.Auxiliary.Schedule;

public class SharedResource extends Tables {
    private int idResource;
    private int idUser;
    private int idCategory;
    private String description;
    private String name;
    private Schedule schedule;



    public SharedResource(int idUser, String description, String name, Schedule schedule, int idCategory){
        this.idUser=idUser;
        this.idCategory=idCategory;
        this.description=description;
        this.name=name;
        this.schedule= schedule;
    }

    public SharedResource(int idUser, String name){
        schedule=new Schedule();
        this.idResource=-1;
        this.idUser=idUser;
        this.idCategory=0;
        this.description="without description";
        this.name=name;
        this.schedule.setScheduleByCode("mon/08:00/19:00>tue/08:00/19:00>wed/08:00/19:00>thu/08:00/19:00>fri/08:00/19:00>sat/08:00/19:00>sun/08:00/19:00>");
    }
    public SharedResource(){
        schedule=new Schedule();
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule getSchedule(){
        return schedule;
    }
    public String getScheduleS(){
        return schedule.getScheduleCode();
    }



    public void setScheduleS(String schedulesS) {
        Schedule sch = new Schedule();
        sch.setScheduleByCode(schedulesS);
        this.schedule=sch;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


}
