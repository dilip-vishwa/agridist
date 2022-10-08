package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Land;
import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class LandController {

    @Autowired
    LandService landService;

    @GetMapping("/land")
    public ResponseEntity<List<Land>> getAllLand( ) throws InterruptedException, ExecutionException{
        List<Land> lands = landService.getLands();
        if(lands.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(lands));
    }

    @GetMapping("/land/{id}")
    public ResponseEntity<Optional<Land>> getLand(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<Land> land = landService.getLandDetails(id);
        if (land.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(land));
    }

    @PostMapping("/land")
    public ResponseEntity<Land> createLand(@RequestBody Land land ) throws InterruptedException, ExecutionException {
        try{
            Land user1 = landService.saveLandDetails(land);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/land/{id}")
    public ResponseEntity<Land> updateLand(@RequestBody Land land , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            landService.updateLandDetails(land, id);
            return ResponseEntity.ok().body(land);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/land/{id}")
    public ResponseEntity<String> deleteLand(@PathVariable String id){
        try {
            landService.deleteLand(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}