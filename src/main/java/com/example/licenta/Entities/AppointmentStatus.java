package com.example.licenta.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentStatus extends Tables {
    private int idAppointment;
    private int idStatus;
    private LocalDateTime dateTime;

    public AppointmentStatus(int idAppointment, int idStatus, LocalDateTime dateTime) {
        this.idAppointment = idAppointment;
        this.idStatus = idStatus;
        this.dateTime = dateTime;
    }
    public AppointmentStatus(){

    }

    public int getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


}
