package com.example.licenta.Entities;

public class Status {
    public static int REQUESTED=1;
    public static int CONFIRMED=2;
    public static int COMPLETED=3;
    public static int USER_CANCEL=4;
    public static int COMPANY_CANCEL=5;

    private int idStatus;
    private String name;
    private int nrCrt;
    private String comment;
    private int userAccessibility;
    private int resourceAccessibility;

    public Status(int idStatus, String name, int nrCrt, String comment, int userAccessibility, int resourceAccessibility) {
        this.idStatus = idStatus;
        this.name = name;
        this.nrCrt = nrCrt;
        this.comment = comment;
        this.userAccessibility = userAccessibility;
        this.resourceAccessibility = resourceAccessibility;
    }
    public Status(){

    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNrCrt() {
        return nrCrt;
    }

    public void setNrCrt(int nrCrt) {
        this.nrCrt = nrCrt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserAccessibility() {
        return userAccessibility;
    }

    public void setUserAccessibility(int userAccessibility) {
        this.userAccessibility = userAccessibility;
    }

    public int getResourceAccessibility() {
        return resourceAccessibility;
    }

    public void setResourceAccessibility(int resourceAccessibility) {
        this.resourceAccessibility = resourceAccessibility;
    }
}
