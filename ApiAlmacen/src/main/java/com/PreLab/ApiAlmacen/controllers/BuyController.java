package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.entities.Buy;
import com.PreLab.ApiAlmacen.models.services.IBuyService;
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
    public class BuyController {

        @Autowired
        private IBuyService iBuyService;

        @GetMapping("/listBuys")
        public List<Buy> findAll(){return iBuyService.findAll();}

        @GetMapping("/{id}")
        public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){

            Buy buy = null;
            Map<String,Object> response = new HashMap<>();
            try {
                buy = iBuyService.findById(id);

            } catch( DataAccessException e) {
                response.put("msg","Error with access to database");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }

            if(buy == null) {
                response.put("msg","There is no announcement with id = ".concat(id.toString()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

            }

            return new ResponseEntity<Buy>( buy, HttpStatus.OK);
        }

        @PostMapping("")
        public ResponseEntity<?> create(@RequestBody Buy buy, BindingResult result) {

            Buy newBuy = null;
            Map<String,Object> response = new HashMap<>();

            if(result.hasErrors()){
                List<String> errors = new ArrayList<>();

                for(FieldError err: result.getFieldErrors()){
                    errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
                }
                response.put("errors", errors);
                response.put("msg", "Error in validation announcement");
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

            }

            try {
                newBuy = iBuyService.save(buy);
            }catch(DataAccessException e) {
                response.put("msg","Error when trying to insert an annoucement");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put("msg", "Annoucement created succesfully");
            response.put("announcement", newBuy);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
        }
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@RequestBody Buy buy,@PathVariable(value="id")Long id ) {

            Buy currentBuy = iBuyService.findById(id);

            Map<String,Object> response = new HashMap<>();

            if(currentBuy == null) {
                response.put("msg","There is no announcement with id = ".concat(id.toString()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }

            currentBuy.setComment(buy.getComment());
            currentBuy.setSuplier(buy.getSuplier());
            currentBuy.setDate(buy.getDate());
            currentBuy.setTotal(buy.getTotal());


            try {
                currentBuy = iBuyService.save(currentBuy);
            }catch(DataAccessException e) {
                response.put("msg","Error trying to modify announcement");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put("msg", "Announcement updated successfully");
            response.put("offer", currentBuy);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

            Map<String,Object> response = new HashMap<>();

            if(iBuyService.findById(id) != null ){
                try{
                    iBuyService.deleteById(id);

                }catch(DataAccessException e){
                    response.put("msg","There was an error when attempting to delete the announcement");
                    response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
                }

                response.put("msg","Announcement successfully removed");
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

            }else{
                response.put("msg","Error trying to delete the announcement");
                response.put("error", "There is no announcement with id: " + id);
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
            }

        }
}
