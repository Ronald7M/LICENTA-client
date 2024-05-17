package com.example.licenta.DataBase;

import com.example.licenta.Auxiliary.Schedule;
import com.example.licenta.Entities.*;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class dataBase {


       static Connection conexiune = null;

    public static void getCon() {
        try {
            // Încărcați driverul JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");


            String url = "jdbc:mysql://uksuiylf7hs4w279:MPm8gmzV1xOofjx1n6vu@bqhwl80c1domfwkjfhyp-mysql.services.clever-cloud.com:3306/bqhwl80c1domfwkjfhyp?autoReconnect=true";
            String utilizator = "uksuiylf7hs4w279";
            String parola = "MPm8gmzV1xOofjx1n6vu";
            conexiune = DriverManager.getConnection(url, utilizator, parola);


            if (conexiune != null) {
                System.out.println("Conexiune la baza de date realizată cu succes!");
            } else {
                System.out.println("Eroare la realizarea conexiunii la baza de date!");
            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    protected static List<User> getUser(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try{
            PreparedStatement statement = conexiune.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();

            // Afisarea rezultatelor
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String usernameResult = resultSet.getString("username");
                String passwordResult = resultSet.getString("password");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                users.add(new User(id,usernameResult,passwordResult,name,email));
            }
            return users;
        } catch (SQLException ex) {
            System.err.println("Eroare la executarea interogării SELECT: " + ex.getMessage());
            return null;
        }
    }



    protected static List<SharedResource> getSharedResource(){
        List<SharedResource> resources = new ArrayList<>();
        String sql="SELECT * FROM Shared_resource";
        PreparedStatement statement ;
        try {
            statement = conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                 int idResource=resultSet.getInt("id_resource");
                 int idUser=resultSet.getInt("id_user");
                 int idCategory=resultSet.getInt("id_category");
                 String description=resultSet.getString("description");
                 String name=resultSet.getString("name");
                 Schedule sch= new Schedule();
                 sch.setScheduleByCode(resultSet.getString("Schedule"));

             //   resources.add(new SharedResource(idResource,idUser,description,name,sch,idCategory));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resources;
    }

    protected static void insertSharedResource(SharedResource sh){
        String query = "INSERT INTO Shared_resource ( id_user, description,name,Schedule,id_category) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexiune.prepareStatement(query);
            preparedStatement.setInt(1, sh.getIdUser());
            preparedStatement.setString(2, sh.getDescription());
            preparedStatement.setString(3, sh.getName());
            preparedStatement.setString(4, sh.getSchedule().getScheduleCode());
            preparedStatement.setInt(5, sh.getIdCategory());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static List<Category> getCategory(){
        List<Category> resources = new ArrayList<>();
        String sql="SELECT * FROM Resource_category";
        PreparedStatement statement ;
        try {
            statement=conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id_category");
                String name=resultSet.getString("name");
                String description=resultSet.getString("description");

                resources.add(new Category(id,name,description));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  resources;
    }
    protected static void updateResource(SharedResource sh){

        try{
            String sql = "UPDATE Shared_resource SET name=?,Schedule=?,description=?,id_category=?  WHERE id_resource = ?";
            PreparedStatement statement = conexiune.prepareStatement(sql);
            statement.setString(1, sh.getName());
            statement.setString(2, sh.getSchedule().getScheduleCode());
            statement.setString(3, sh.getDescription());
            statement.setInt(4, sh.getIdCategory());
            statement.setInt(5, sh.getIdResource());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected static List<Service> getService(){
        List<Service> services = new ArrayList<>();
        String sql="SELECT * FROM Resource_services";
        PreparedStatement statement ;
        try {
            statement=conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idService=resultSet.getInt("id_service");
                int idResource=resultSet.getInt("id_resource");
                int duration=resultSet.getInt("duration");
                String name=resultSet.getString("name_service");
                byte tinyIntValue = resultSet.getByte("is_delete");
                boolean isDelete=tinyIntValue != 0;


                services.add(new Service(idService,idResource,duration,name,isDelete));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  services;
    }

    protected static void insertService(Service s){
        String query = "INSERT INTO Resource_services (  id_resource,name_service,duration,is_delete) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexiune.prepareStatement(query);
            preparedStatement.setInt(1, s.getIdResource());
            preparedStatement.setString(2, s.getNameService());
            preparedStatement.setInt(3, s.getDuration());
            int tinyIntValue = s.getDelete() ? 1 : 0;
            preparedStatement.setInt(4,tinyIntValue);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected  static void updateService(Service old, String newName){
        String query = " UPDATE Resource_services SET name_service = ? WHERE id_service = ?";
        try{
            PreparedStatement statement = conexiune.prepareStatement(query);

            statement.setString(1 ,newName);
            statement.setInt(2, old.getIdService());
            statement.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    protected static void deleteService(Service deleteService){
        String query = " UPDATE Resource_services SET is_delete = ? WHERE id_service = ?";
        try{
            PreparedStatement statement = conexiune.prepareStatement(query);
            statement.setInt(1 ,1);
            statement.setInt(2, deleteService.getIdService());
            statement.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    protected static List<Status> getStatus(){
        List<Status> status = new ArrayList<>();
        String sql="SELECT * FROM Status";
        PreparedStatement statement ;
        try {
            statement=conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idStatus=resultSet.getInt("id_status");
                String name=resultSet.getString("name");
                int nrCrt=resultSet.getInt("nr_crt");
                String comment=resultSet.getString("comment");
                int userAcc=resultSet.getInt("user_accessibility");
                int resourceAcc=resultSet.getInt("resource_accessibility");


               status.add(new Status(idStatus,name,nrCrt,comment,userAcc,resourceAcc));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  status;

    }

    //------------------
    protected static List<AppointmentStatus> getAppointmentStatus(int idApp){
        List<AppointmentStatus> status = new ArrayList<>();
        String sql="SELECT * FROM Appointment_status WHERE id_appointment = "+idApp;
        PreparedStatement statement ;
        try {
            statement=conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idAppointment=resultSet.getInt("id_appointment");
                int idStatus=resultSet.getInt("id_status");
                Timestamp dateTime=resultSet.getTimestamp("date_time");

                status.add(new AppointmentStatus(idAppointment,idStatus,dateTime.toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  status;
    }

    protected static void addNewAppointmentStatus(AppointmentStatus as){
        String query = "INSERT INTO Appointment_status (  id_appointment,id_status,date_time) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = conexiune.prepareStatement(query);
            preparedStatement.setInt(1, as.getIdAppointment());
            preparedStatement.setInt(2, as.getIdStatus());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(as.getDateTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected static List<Appointment> getAppointment(){
        List<Appointment> appointments = new ArrayList<>();
        String sql="SELECT * FROM Appointment";
        PreparedStatement statement ;
        try {
            statement=conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idAppointment=resultSet.getInt("id_appointment");
                int idUser=resultSet.getInt("id_user");
                int idService=resultSet.getInt("id_services");
                Timestamp dateTime=resultSet.getTimestamp("date_time");

                appointments.add(new Appointment(idAppointment,idUser,idService,dateTime.toLocalDateTime()));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  appointments;

    }

    protected static void insertAppointments(Appointment s){
        String query = "INSERT INTO Appointment (  id_user,id_services,date_time) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = conexiune.prepareStatement(query);
            preparedStatement.setInt(1, s.getIdUser());
            preparedStatement.setInt(2, s.getIdService());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(s.getDateTime()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static List<Preferences> getPreferences(){
        List<Preferences> pref = new ArrayList<>();
        String sql="SELECT * FROM Preferences";
        PreparedStatement statement ;
        try {
            statement=conexiune.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idUser=resultSet.getInt("id_user");
                int idResource=resultSet.getInt("id_resource");

                pref.add(new Preferences(idUser,idResource));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  pref;

    }
    protected static void insertPreferences(Preferences pref){
        String query = "INSERT INTO Preferences (  id_user,id_resource) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = conexiune.prepareStatement(query);
            preparedStatement.setInt(1, pref.getIdUser());
            preparedStatement.setInt(2, pref.getIdResource());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static void deletePreference(Preferences pref){
        String query = "DELETE FROM Preferences WHERE id_user = ? AND id_resource = ?";
        try{
            PreparedStatement preparedStatement = conexiune.prepareStatement(query);
                preparedStatement.setInt(1, pref.getIdUser());
                preparedStatement.setInt(2, pref.getIdResource());



            preparedStatement.execute();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


}