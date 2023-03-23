package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.entities.BuyLine;
import com.PreLab.ApiAlmacen.entities.Product;
import com.PreLab.ApiAlmacen.models.services.IBuyLineService;
import com.PreLab.ApiAlmacen.models.services.IProductService;
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
public class BuyLineController {
    @Autowired
    private IBuyLineService buyLineService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        if(buyLineService.findById(id) != null ){
            try{
                buyLineService.deleteById(id);

            }catch(DataAccessException e){
                response.put("msg","There was an error when attempting to delete the buyLine");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("msg","buyLine successfully removed");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

        }else{
            response.put("msg","Error trying to delete the buyLine");
            response.put("error", "There is no buyLine with id: " + id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
