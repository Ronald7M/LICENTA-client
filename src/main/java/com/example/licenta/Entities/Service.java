package com.example.licenta.Entities;

public class Service extends Tables{



    private int idService;
    private int idResource;
    private int duration;//minutes
    private String nameService;

    private boolean isDelete;

    public boolean getDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Service(int idService, int idResource, int duration, String nameService, boolean isDelet) {
        this.idService = idService;
        this.idResource = idResource;
        this.duration = duration;
        this.nameService = nameService;
        this.isDelete=isDelet;
    }
    public Service(){

    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getDurationAsString(){
        return intToStringH(duration);
    }
    private static String intToStringH(int minuteInput) {
        // Calculăm orele și minutele
        int ore = minuteInput / 60;
        int minute = minuteInput % 60;

        // Formatăm timpul într-un șir "hh:mm"
        String timpString = String.format("%02d:%02d", ore, minute);

        return timpString;
    }
    @Override
    public String toString() {
        return nameService +" "+String.valueOf(duration) ;
    }

}
