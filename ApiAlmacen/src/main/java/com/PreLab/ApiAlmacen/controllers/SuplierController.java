package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import com.PreLab.ApiAlmacen.entities.Announcement;
import com.PreLab.ApiAlmacen.entities.Suplier;
import com.PreLab.ApiAlmacen.models.services.ISuplierService;
import io.swagger.v3.oas.annotations.headers.Header;
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
@RequestMapping("/api/suplier")
public class SuplierController {

    @Autowired
    private ISuplierService suplierService;

    @GetMapping("")
    @VerifyToken
    public List<Suplier> findAll(){return suplierService.findAll();}

    @GetMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id, @RequestHeader(value = "Authorization", required = false) String token){

        Suplier suplier = null;
        Map<String,Object> response = new HashMap<>();
        try {
            suplier = suplierService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(suplier == null) {
            response.put("msg","There is no supplier with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Suplier>( suplier, HttpStatus.OK);
    }

    @PostMapping("")
    @VerifyToken
    public ResponseEntity<?> create(@RequestBody Suplier suplier, @RequestHeader(value = "Authorization", required = false) String token, BindingResult result) {

        Suplier newSuplier = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation supplier");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            newSuplier = suplierService.save(suplier);
        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert a new suplier");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Suplier created succesfully");
        response.put("suplier", newSuplier);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> update(@RequestBody Suplier suplier,@PathVariable(value="id")Long id, @RequestHeader(value = "Authorization", required = false) String token ) {

        Suplier currentSuplier = suplierService.findById(id);

        Map<String,Object> response = new HashMap<>();

        if(currentSuplier == null) {
            response.put("msg","There is no supplier with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        currentSuplier.setName(suplier.getName());
        currentSuplier.setAddress(suplier.getAddress());
        currentSuplier.setPhoneNumber(suplier.getPhoneNumber());
        currentSuplier.setImage(suplier.getImage());

        try {
            currentSuplier = suplierService.save(currentSuplier);
        }catch(DataAccessException e) {
            response.put("msg","Error trying to modify the supplier");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Supplier updated successfully");
        response.put("supplier", currentSuplier);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id, @RequestHeader(value = "Authorization", required = false) String token) {

        Map<String,Object> response = new HashMap<>();

        if(suplierService.findById(id) != null ){
            try{
                suplierService.deleteById(id);

            }catch(DataAccessException e){
                response.put("msg","There was an error when attempting to delete the supplier");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("msg","Supplier successfully removed");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

        }else{
            response.put("msg","Error trying to delete the supplier");
            response.put("error", "There is no supplier with id: " + id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }



}
