package com.example.licenta.DataBase;

import com.example.licenta.Entities.*;
import com.example.licenta.dashbordController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class handlerDB{


    public static User checkLogin(String username, String password){
        List<User> list = DATA_BASE.getUsers();
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getUsername().equals(username) && list.get(i).getPassword().equals(password)){
                return list.get(i);
            }
        }
        return null;
    }

    public static SharedResource getMyResource(int idUser){
        List<SharedResource> resources = DATA_BASE.getSharedResources();

        for (int i = 0; i <resources.size() ; i++) {
            if(resources.get(i).getIdUser()==idUser){
                return resources.get(i);
            }
        }
        return null;

    }

    public static void createResource(SharedResource sh){
        DATA_BASE.postSharedResource(sh);
    }

    public static Category getCategoryById(int idCategory){
        List<Category> category=DATA_BASE.getCategories();
        for (int i = 0; i <category.size(); i++) {
            if(category.get(i).getIdCategory()==idCategory){
                return category.get(i);
            }
        }
        return null;
    }

    public static List<Category> getCategories(){
        return DATA_BASE.getCategories();
    }

    public static void changeResource(SharedResource resource){
        DATA_BASE.putSharedResource(resource.getIdResource(), resource);
    }

    public static List<Service> getServicesByResource(int myResource){
       List<Service> services =DATA_BASE.getService();
       List<Service> outputServices=new ArrayList<>();
        for (int i = 0; i < services.size(); i++) {
          if(services.get(i).getIdResource()==myResource){
              outputServices.add(services.get(i));
          }
        }
        return outputServices;
    }
    public  static void addService(Service service){
        DATA_BASE.postService(service);
    }
    public static User getUserByAppointment(Appointment app){
        List<User> list = DATA_BASE.getUsers();
        for (User user : list) {
            if(user.getId()==app.getIdUser()){
                return user;
            }
        }
        return null;
    }
    public static Service getServiceByAppointment(Appointment app){
        List<Service> list = DATA_BASE.getService();
        for (Service service : list) {
            if(service.getIdService()==app.getIdService()){
                return service;
            }
        }
        return null;
    }
    public static Service getResourceServiceById(int id){
        List<Service> allService =DATA_BASE.getService();
        for (Service service : allService) {
            if(service.getIdService()==id){
                return service;
            }
        }
        return null;
    }
    public static List<Appointment> getAppointment(){

        return DATA_BASE.getAppointment();
    }
    public static List<Appointment> getAppointmentByResource(SharedResource myResource){
        List<Appointment> allApp = DATA_BASE.getAppointment();
        List<Appointment> resultApp=new ArrayList<>();
        for (Appointment appointment : allApp) {
            if(getResourceServiceById(appointment.getIdService())!=null) {
                if (getResourceServiceById(appointment.getIdService()).getIdResource() == myResource.getIdResource()) {
                    resultApp.add(appointment);
                }
            }

        }

        return resultApp;
    }

    public static void makeNewAppointment(Appointment app){
        DATA_BASE.postAppointment(app);
        dataBase.insertAppointments(app);

        List<Appointment> list=getAppointment();
        int id=list.get(list.size()-1).getIdAppointment();
        System.out.println(id);
        DATA_BASE.postAppointmentStatus(new AppointmentStatus(id,Status.REQUESTED, dashbordController.getCurrentDateTime()));
    }

    public static List<AppointmentStatus> getAppointmentStatus(Appointment ap){
        List<AppointmentStatus> LIST=  DATA_BASE.getAppointmentStatus();
        List<AppointmentStatus> ret = new ArrayList<>();
        for (AppointmentStatus app : LIST) {
            if(app.getIdAppointment()== ap.getIdAppointment()){
                ret.add(app);
            }
        }

        return ret;

    }

    public static AppointmentStatus getCurrentAppointmentStatus(Appointment ap){
        List<AppointmentStatus> checkList =getAppointmentStatus(ap);
        return  checkList.get(checkList.size()-1);
    }
    public static void addNewAppointmentStatus( AppointmentStatus as){
        DATA_BASE.postAppointmentStatus(as);
    }
    public static List<SharedResource> getSharedResource(){

        return DATA_BASE.getSharedResources();
    }

    public static List<Preferences> getMyPreference(User me ){
        List<Preferences> pref =DATA_BASE.getPreferences();
        List<Preferences> ret= new ArrayList<>();
        for (Preferences preferences : pref) {
            if(preferences.getIdUser()==me.getId()){
                ret.add(preferences);
            }
        }
        return ret;
    }
    public static void addNewPref(Preferences p){

                DATA_BASE.postPreferences(p);
    }
    public static void deletePreference(Preferences p ){

            DATA_BASE.deletePreference(p);
    }
    public static SharedResource getResourceById(int idRes){
        List<SharedResource>  list= DATA_BASE.getSharedResources();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getIdResource()==idRes){
                return  list.get(i);
            }
        }
        return null;
    }
    public static boolean thereIsPref(int idUser,int idPref){
        List<Preferences> pref =DATA_BASE.getPreferences();
        for (Preferences preferences : pref) {
            if(preferences.getIdUser()==idUser && preferences.getIdResource()==idPref){
                return true;
            }
        }
        return false;
    }

    public static void updateService(Service old, String newName){
        old.setNameService(newName);
        DATA_BASE.putService(old.getIdService(),old);

    }
    public static void deleteService(Service delete){
        DATA_BASE.deleteService(delete);
    }


}
