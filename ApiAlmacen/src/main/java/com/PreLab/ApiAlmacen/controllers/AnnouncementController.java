package com.PreLab.ApiAlmacen.controllers;

import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import com.PreLab.ApiAlmacen.entities.Announcement;
import com.PreLab.ApiAlmacen.models.services.IAnnouncementService;
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
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    IAnnouncementService iAnnouncementService;

    @GetMapping("   ")
    public List<Announcement> findAll(){return iAnnouncementService.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){

        Announcement announcement = null;
        Map<String,Object> response = new HashMap<>();
        try {
            announcement = iAnnouncementService.findById(id);

        } catch( DataAccessException e) {
            response.put("msg","Error with access to database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(announcement == null) {
            response.put("msg","There is no announcement with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<Announcement>( announcement, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Announcement announcement, BindingResult result) {

        Announcement newAnnouncement = null;
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
            newAnnouncement = iAnnouncementService.save(announcement);
        }catch(DataAccessException e) {
            response.put("msg","Error when trying to insert an annoucement");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Annoucement created succesfully");
        response.put("announcement", newAnnouncement);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Announcement announcement,@PathVariable(value="id")Long id ) {

        Announcement currentAnnoucement = iAnnouncementService.findById(id);

        Map<String,Object> response = new HashMap<>();

        if(currentAnnoucement == null) {
            response.put("msg","There is no announcement with id = ".concat(id.toString()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        currentAnnoucement.setTitle(announcement.getTitle());
        currentAnnoucement.setDescription(announcement.getDescription());

        try {
            currentAnnoucement = iAnnouncementService.save(currentAnnoucement);
        }catch(DataAccessException e) {
            response.put("msg","Error trying to modify announcement");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("msg", "Announcement updated successfully");
        response.put("announcement", currentAnnoucement);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Long id) {

        Map<String,Object> response = new HashMap<>();

        if(iAnnouncementService.findById(id) != null ){
            try{
                iAnnouncementService.deleteById(id);

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
