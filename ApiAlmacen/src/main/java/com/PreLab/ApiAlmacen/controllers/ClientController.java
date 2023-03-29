package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import com.PreLab.ApiAlmacen.entities.Client;
import com.PreLab.ApiAlmacen.models.services.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("")
    @VerifyToken
    public List<Client> findAll(){return clientService.findAll();}

    @GetMapping("/debt")
    @VerifyToken
    public List<Client> findwithDebt(){
        return clientService.findWithDebt();
    }

    @GetMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){

        Client client = null;
        Map<String,Object> response = new HashMap<>();
        try {
            client = clientService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(client == null) {
            response.put("msg","There is no client with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Client>( client, HttpStatus.OK);
    }

    @PostMapping("")
    @VerifyToken
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {

        Client newClient = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation client");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            newClient = clientService.save(client);
        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert an client");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Client created succesfully");
        response.put("client", newClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result, @PathVariable(value="id")Long id) {

        Client currentClient = clientService.findById(id);

        Map<String,Object> response = new HashMap<>();

        if(currentClient == null) {
            response.put("msg","There is no client with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation client");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        currentClient.setName(client.getName());
        currentClient.setPhoneNumber(client.getPhoneNumber());
        currentClient.setDebt(client.getDebt());

        try {
            currentClient = clientService.save(currentClient);
        }catch(DataAccessException e) {
            response.put("msg","Error trying to modify client");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Client updated successfully");
        response.put("client", currentClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        if(clientService.findById(id) != null ){
            try{
                clientService.deleteById(id);

            }catch(DataAccessException e){
                response.put("msg","There was an error when attempting to delete the client");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("msg","Client successfully removed");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

        }else{
            response.put("msg","Error trying to delete the client");
            response.put("error", "There is no client with id: " + id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
