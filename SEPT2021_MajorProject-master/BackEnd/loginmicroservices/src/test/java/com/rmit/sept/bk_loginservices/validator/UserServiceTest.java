package com.rmit.sept.bk_loginservices.validator;

import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class UserServiceTest {

    @Test
    @DisplayName("All user info should be validated successfully")
    public void addAdminUser(){
        Random random = new Random();
        int rand = random.nextInt() % 1000;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "hello" + rand + "@test.com");
        jsonObject.put("firstName", "test");
        jsonObject.put("lastName", "user");
        jsonObject.put("password", "password");
        jsonObject.put("confirmPassword", "password");
        jsonObject.put("address", "somewhere");
        jsonObject.put("country", "country");
        jsonObject.put("phone", "213213");
        jsonObject.put("postcode", "3000");
        jsonObject.put("userRole", "customer");
        jsonObject.put("adminRole", "admin");
        jsonObject.put("businessApproval", false);
        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
        restTemplate.postForObject("http://localhost:8080/api/users/admin/add",request,String.class);

    }
}
