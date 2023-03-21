package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.entities.Offer;
import com.PreLab.ApiAlmacen.models.services.IOfferService;
import lombok.Data;
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
@RequestMapping("/api")
 public class OfferControllers {

    @Autowired
    private IOfferService offerService;

    @GetMapping("/offers")
    public List<Offer> findAll(){
        return offerService.findAll();
    }


    @GetMapping("/offers/{id}")
    public ResponseEntity<?> findById(@PathVariable(value="id") Long id) {

        Offer offer = null;
        Map<String,Object> response = new HashMap<>();
        try {
            offer = offerService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(offer == null) {
            response.put("msg","There is no offer with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Offer>(offer, HttpStatus.OK);

    }

    @PostMapping("/offer")
    public ResponseEntity<?> create(@RequestBody Offer cliente, BindingResult result) {

        Offer newOffer = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation offer");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            newOffer = offerService.save(cliente);
        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert an offer");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Offer created succesfully");
        response.put("offer", newOffer);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<?> update(@RequestBody Offer offer,@PathVariable(value="id")Long id ) {

        Offer currentOffer = offerService.findById(id);

        Map<String,Object> response = new HashMap<>();

        if(currentOffer == null) {
            response.put("msg","There is no user with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        currentOffer.setTitle(offer.getTitle());
        currentOffer.setDescription(offer.getDescription());
        currentOffer.setVisible(offer.getVisible());

        try {
            currentOffer = offerService.save(currentOffer);
        }catch(DataAccessException e) {
            response.put("msg","Error trying to modify user");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Offer updated successfully");
        response.put("offer", currentOffer);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        try {
            offerService.deleteById(id);

        }catch(DataAccessException e) {
            response.put("msg","Error trying to delete offer");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Offer successfully removed");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

    }
}
