package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Equipment;
import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/equipment")
    public ResponseEntity<List<Equipment>> getAllEquipment( ) throws InterruptedException, ExecutionException{
        List<Equipment> equipments = equipmentService.getEquipments();
        if(equipments.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(equipments));
    }
    @GetMapping("/equipment/search/{name}")
    public ResponseEntity<List<Equipment>> getEquipmentByName(@PathVariable String name ) throws InterruptedException, ExecutionException{
        List<Equipment> equipments = equipmentService.getEquipmentDetailsByName(name);
        if(equipments.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(equipments));
    }

    @GetMapping("/equipment/{id}")
    public ResponseEntity<Optional<Equipment>> getEquipment(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<Equipment> equipment = equipmentService.getEquipmentDetails(id);
        if (equipment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(equipment));
    }

    @PostMapping("/equipment")
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment ) throws InterruptedException, ExecutionException {
        try{
            Equipment user1 = equipmentService.saveEquipmentDetails(equipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/equipment/{id}")
    public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment equipment , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            equipmentService.updateEquipmentDetails(equipment, id);
            return ResponseEntity.ok().body(equipment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable String id){
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}