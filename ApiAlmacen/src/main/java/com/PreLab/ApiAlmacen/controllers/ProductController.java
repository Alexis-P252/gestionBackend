package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import com.PreLab.ApiAlmacen.entities.Product;
import com.PreLab.ApiAlmacen.models.services.IProductService;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public List<Product> findAll(){return productService.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){

        Product product = null;
        Map<String,Object> response = new HashMap<>();
        try {
            product = productService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(product == null) {
            response.put("msg","There is no product with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Product>( product, HttpStatus.OK);
    }

    @PostMapping("")
    @VerifyToken
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {

        Product newProduct = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation product");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            newProduct = productService.save(product);
        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert a new product");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Product created succesfully");
        response.put("product", newProduct);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable(value="id")Long id ) {

        Product currentProduct = productService.findById(id);

        Map<String,Object> response = new HashMap<>();

        if(currentProduct == null) {
            response.put("msg","There is no product with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation product");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }


        currentProduct.setName(product.getName());
        currentProduct.setCategory(product.getCategory());
        currentProduct.setImage(product.getImage());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setBuy_price(product.getBuy_price());
        currentProduct.setSell_price(product.getSell_price());
        currentProduct.setStock(product.getStock());
        currentProduct.setVisible(product.getVisible());


        try {
            currentProduct = productService.save(currentProduct);
        }catch(DataAccessException e) {
            response.put("msg","Error trying to modify the product");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Product updated successfully");
        response.put("product", currentProduct);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        if(productService.findById(id) != null ){
            try{
                productService.deleteById(id);

            }catch(DataAccessException e){
                response.put("msg","There was an error when attempting to delete the product");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("msg","Product successfully removed");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

        }else{
            response.put("msg","Error trying to delete the product");
            response.put("error", "There is no product with id: " + id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
