package com.example.licenta.DataBase;

import com.example.licenta.Entities.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DATA_BASE {
    private static final String BASE_URL = "http://localhost:8080/api";

    public static List<User> getUsers(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/user";
            ResponseEntity<List<User>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>() {
                    }
            );
            return  response.getBody();
        } catch (RestClientException e) {
            System.out.println("Something was wrong!!! Users can't be reading");
        }
        return null;

    }

    public static List<SharedResource> getSharedResources(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/sharedResource";
            ResponseEntity<List<SharedResource>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<SharedResource>>() {
                    }
            );
            return  response.getBody();
        } catch (RestClientException e) {
            System.out.println("Something was wrong!!! Resource can't be reading");
        }
        return null;

    }

    public static void postSharedResource(SharedResource sharedResource) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/sharedResource";

            HttpEntity<SharedResource> request = new HttpEntity<>(sharedResource);

            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Resource successfully posted.");
            } else {
                System.out.println("Failed to post resource. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Resource couldn't be posted.");
        }
    }

    public static void putSharedResource(int resourceId, SharedResource sharedResource) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/sharedResource/{id}";
            HttpEntity<SharedResource> request = new HttpEntity<>(sharedResource);
            restTemplate.put(url, request, resourceId);

            System.out.println("Resource successfully updated.");
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Resource couldn't be updated.");
        }
    }
    public static List<Category> getCategories(){
        try{
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "/category";
        ResponseEntity<List<Category>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Category>>() {
                }
        );
        return  response.getBody();
    } catch (RestClientException e) {
        System.out.println("Something was wrong!!! Users can't be reading");
    }
        return null;
    }

    public static List<Service> getService(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/service";
            ResponseEntity<List<Service>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Service>>() {
                    }
            );
            return  response.getBody();
        } catch (RestClientException e) {
            System.out.println("Something was wrong!!! Users can't be reading");
        }
        return null;

    }

    public static void postService(Service service) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/service";

            HttpEntity<Service> request = new HttpEntity<>(service);

            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Service successfully posted.");
            } else {
                System.out.println("Failed to post Service. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Service couldn't be posted.");
        }
    }

    public static void putService(int resourceId, Service service) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/service/{id}";
            HttpEntity<Service> request = new HttpEntity<>(service);
            restTemplate.put(url, request, resourceId);

            System.out.println("Service successfully updated.");
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Service couldn't be updated.");
        }
    }

    public static void deleteService(Service service) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/service/delete";

            // Trimitem întregul corp al obiectului Service în corpul cererii
            HttpEntity<Service> request = new HttpEntity<>(service);
            restTemplate.postForEntity(url, request,Void.class);

            System.out.println("Service successfully deleted.");
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Service couldn't be deleted.");
        }
    }

    public static List<AppointmentStatus> getAppointmentStatus(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/appointmentStatus";
            ResponseEntity<List<AppointmentStatus>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<AppointmentStatus>>() {
                    }
            );
            return  response.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something was wrong!!! Appointment Status can't be reading");
        }
        return null;

    }

    public static void postAppointmentStatus(AppointmentStatus as) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/appointmentStatus";

            HttpEntity<AppointmentStatus> request = new HttpEntity<>(as);

            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Appointment Status successfully posted.");
            } else {
                System.out.println("Failed to post Appointment Status. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Appointment Status couldn't be posted.");
        }
    }

    public static List<Preferences> getPreferences(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/preferences";
            ResponseEntity<List<Preferences>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Preferences>>() {
                    }
            );
            return  response.getBody();
        } catch (RestClientException e) {
            System.out.println("Something was wrong!!! Preference can't be reading");
        }
        return null;

    }

    public static void postPreferences(Preferences pref) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/preferences";

            HttpEntity<Preferences> request = new HttpEntity<>(pref);

            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Preferences successfully posted.");
            } else {
                System.out.println("Failed to post Preference. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Preference couldn't be posted.");
        }
    }
    public static void deletePreference(Preferences pref) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/preferences/delete";

            HttpEntity<Preferences> request = new HttpEntity<>(pref);

            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);


            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Preferences successfully deleted.");
            } else {
                System.out.println("Failed to delete Preference. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Preference couldn't be posted.");
        }
    }

    public static List<Appointment> getAppointment(){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/appointment";
            ResponseEntity<List<Appointment>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Appointment>>() {
                    }
            );
            return  response.getBody();
        } catch (RestClientException e) {
            System.out.println("Something was wrong!!! Appointment can't be reading");
        }
        return null;

    }

    public static void postAppointment(Appointment ap) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = BASE_URL + "/appointment";

            HttpEntity<Appointment> request = new HttpEntity<>(ap);

            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Appointment successfully posted.");
            } else {
                System.out.println("Failed to post Appointment. Status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Appointment couldn't be posted.");
        }
    }



}





