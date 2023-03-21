package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.entities.Admin;
import com.PreLab.ApiAlmacen.models.services.AdminService;
import com.PreLab.ApiAlmacen.utils.JWTUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ObjectNode JSONObject){

        Map<String,Object> response = new HashMap<>();
        String username = null;
        String password = null;

        try{
            username = JSONObject.get("username").asText();
            password = JSONObject.get("password").asText();
        }catch (Exception e){
            response.put("msg", "An error occurred during registration");
            response.put("error", "You must write an username and a password");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(adminService.existsByUsername(username)){
            response.put("msg", "An error occurred during registration");
            response.put("error", "Username is already taken!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        adminService.save(admin);

        response.put("msg", "You have successfully registered.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ObjectNode JSONObject){

        Map<String,Object> response = new HashMap<>();
        String username = null;
        String password = null;

        try{
            username = JSONObject.get("username").asText();
            password = JSONObject.get("password").asText();
        }catch (Exception e){
            response.put("msg", "Error al iniciar sesion!");
            response.put("error", "Debes indicar el username y la password");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if( adminService.verifyLogin(username, password)){

            Admin u = adminService.findByUsername(username);
            String token = jwtUtil.create(String.valueOf(u.getId()), u.getUsername());

            response.put("token",token);
            response.put("id", u.getId());
            response.put("username", u.getUsername());
            response.put("msg", "Login correcto!");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

        }
        else{
            response.put("msg", "Error al iniciar sesion!");
            response.put("error", "Las credenciales no coinciden");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }




}
