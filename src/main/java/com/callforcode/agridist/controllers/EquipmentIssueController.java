package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.EquipmentIssue;
import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.EquipmentIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipmentIssueController {

    @Autowired
    EquipmentIssueService equipmentIssueService;

    @GetMapping("/equipmentissue")
    public ResponseEntity<List<EquipmentIssue>> getAllEquipmentIssue( ) throws InterruptedException, ExecutionException{
        List<EquipmentIssue> equipmentIssues = equipmentIssueService.getEquipmentIssues();
        if(equipmentIssues.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(equipmentIssues));
    }

    @GetMapping("/equipmentissue/{id}")
    public ResponseEntity<Optional<EquipmentIssue>> getEquipmentIssue(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<EquipmentIssue> equipmentIssue = equipmentIssueService.getEquipmentIssueDetails(id);
        if (equipmentIssue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(equipmentIssue));
    }

    @PostMapping("/equipmentissue")
    public ResponseEntity<EquipmentIssue> createEquipmentIssue(@RequestBody EquipmentIssue equipmentIssue ) throws InterruptedException, ExecutionException {
        try{
            EquipmentIssue user1 = equipmentIssueService.saveEquipmentIssueDetails(equipmentIssue);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/equipmentissue/{id}")
    public ResponseEntity<EquipmentIssue> updateEquipmentIssue(@RequestBody EquipmentIssue equipmentIssue , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            equipmentIssueService.updateEquipmentIssueDetails(equipmentIssue, id);
            return ResponseEntity.ok().body(equipmentIssue);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/equipmentissue/{id}")
    public ResponseEntity<String> deleteEquipmentIssue(@PathVariable String id){
        try {
            equipmentIssueService.deleteEquipmentIssue(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}