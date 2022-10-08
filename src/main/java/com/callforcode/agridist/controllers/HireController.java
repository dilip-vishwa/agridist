package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Hire;
import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.HireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class HireController {

    @Autowired
    HireService hireService;

    @GetMapping("/hire")
    public ResponseEntity<List<Hire>> getAllHire( ) throws InterruptedException, ExecutionException{
        List<Hire> hires = hireService.getHires();
        if(hires.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(hires));
    }

    @GetMapping("/hire/{id}")
    public ResponseEntity<Optional<Hire>> getHire(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<Hire> hire = hireService.getHireDetails(id);
        if (hire.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(hire));
    }

    @PostMapping("/hire")
    public ResponseEntity<Hire> createHire(@RequestBody Hire hire ) throws InterruptedException, ExecutionException {
        try{
            Hire user1 = hireService.saveHireDetails(hire);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/hire/{id}")
    public ResponseEntity<Hire> updateHire(@RequestBody Hire hire , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            hireService.updateHireDetails(hire, id);
            return ResponseEntity.ok().body(hire);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/hire/{id}")
    public ResponseEntity<String> deleteHire(@PathVariable String id){
        try {
            hireService.deleteHire(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}