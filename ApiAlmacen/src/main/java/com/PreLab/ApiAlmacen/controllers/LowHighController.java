package com.PreLab.ApiAlmacen.controllers;


import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import com.PreLab.ApiAlmacen.entities.LowHigh;
import com.PreLab.ApiAlmacen.models.services.ILowHighService;
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
@RequestMapping("/api/lowhigh")
public class LowHighController {

    @Autowired
    private ILowHighService lowHighService;



    @GetMapping("")
    @VerifyToken
    public List<LowHigh> findAll(){return lowHighService.findAll();}

    @GetMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){

        LowHigh lowHigh = null;
        Map<String,Object> response = new HashMap<>();
        try {
            lowHigh = lowHighService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(lowHigh == null) {
            response.put("msg","There is no low/high register with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<LowHigh>( lowHigh, HttpStatus.OK);
    }



    @PostMapping("")
    @VerifyToken
    public ResponseEntity<?> create(@RequestBody LowHigh lowHigh, BindingResult result) {

        LowHigh newLowHigh = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validating low/high register");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            newLowHigh = lowHighService.save(lowHigh);

        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert a low/high register");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Low/High register created succesfully");
        response.put("lowHigh", newLowHigh);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@RequestBody LowHigh lowHigh,@PathVariable(value="id")Long id ) {
//
//        LowHigh currentLowHigh = lowHighService.findById(id);
//
//        Map<String,Object> response = new HashMap<>();
//
//        if(currentLowHigh == null) {
//            response.put("msg","There is no low/high register with id = ".concat(id.toString()));
//            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
//        }
//
//        currentLowHigh.setLowHigh(lowHigh.getLowHigh());
//        currentLowHigh.setDate(lowHigh.getDate());
//        currentLowHigh.setReason(lowHigh.getReason());
//
//        try {
//            currentLowHigh = lowHighService.save(currentLowHigh);
//        }catch(DataAccessException e) {
//            response.put("msg","Error trying to modify the low/high register");
//            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        response.put("msg", "Low/High register updated successfully");
//        response.put("client", currentLowHigh);
//        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        if(lowHighService.findById(id) != null ){
            try{
                lowHighService.deleteById(id);

            }catch(DataAccessException e){
                response.put("msg","There was an error when attempting to delete the low/high register");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("msg","Low/high register successfully removed");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

        }else{
            response.put("msg","Error trying to delete the low/high register");
            response.put("error", "There is no low/high register with id: " + id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
