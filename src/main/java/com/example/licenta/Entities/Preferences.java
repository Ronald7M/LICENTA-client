package com.example.licenta.Entities;

public class Preferences {
    private int idUser;
    private int idResource;

    public Preferences(int idUser, int idObject) {
        this.idUser = idUser;
        this.idResource = idObject;
    }
    public Preferences(){

    }
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }
}
