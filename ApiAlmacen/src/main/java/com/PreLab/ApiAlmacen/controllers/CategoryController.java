package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import com.PreLab.ApiAlmacen.entities.Category;
import com.PreLab.ApiAlmacen.entities.Suplier;
import com.PreLab.ApiAlmacen.models.services.ICategoryService;
import com.PreLab.ApiAlmacen.models.services.ISuplierService;
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
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public List<Category> findAll(){return categoryService.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){

        Category category = null;
        Map<String,Object> response = new HashMap<>();
        try {
            category = categoryService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(category == null) {
            response.put("msg","There is no category with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Category>( category, HttpStatus.OK);
    }

    @PostMapping("")
    @VerifyToken
    public ResponseEntity<?> create(@Valid @RequestBody Category category, BindingResult result) {

        Category newCategory = null;
        Map<String,Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation category");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            newCategory = categoryService.save(category);
        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert a new category");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Category created succesfully");
        response.put("category", newCategory);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> update(@Valid @RequestBody Category category, BindingResult result, @PathVariable(value="id")Long id  ) {

        Category currentCategory = categoryService.findById(id);

        Map<String,Object> response = new HashMap<>();

        if(currentCategory == null) {
            response.put("msg","There is no category with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();

            for(FieldError err: result.getFieldErrors()){
                errors.add("In the field: " + err.getField() + " - " +err.getDefaultMessage());
            }
            response.put("errors", errors);
            response.put("msg", "Error in validation category");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);

        }


        currentCategory.setName(category.getName());

        try {
            currentCategory = categoryService.save(currentCategory);
        }catch(DataAccessException e) {
            response.put("msg","Error trying to modify the category");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Category updated successfully");
        response.put("category", currentCategory);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @VerifyToken
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        if(categoryService.findById(id) != null ){
            try{
                categoryService.deleteById(id);

            }catch(DataAccessException e){
                response.put("msg","There was an error when attempting to delete the category");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            response.put("msg","Category successfully removed");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

        }else{
            response.put("msg","Error trying to delete the category");
            response.put("error", "There is no category with id: " + id);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
