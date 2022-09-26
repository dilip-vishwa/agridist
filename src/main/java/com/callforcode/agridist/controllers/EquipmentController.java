package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Equipment;
import com.callforcode.agridist.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/equipment")
    public List<Equipment> getAllEquipment( ) throws InterruptedException, ExecutionException{
        return equipmentService.getEquipments();
    }

    @GetMapping("/equipment/{id}")
    public Equipment getEquipment(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return equipmentService.getEquipmentDetails(id);
    }

    @PostMapping("/equipment")
    public String createEquipment(@RequestBody Equipment equipment ) throws InterruptedException, ExecutionException {
        return equipmentService.saveEquipmentDetails(equipment);
    }

    @PutMapping("/equipment/{id}")
    public String updateEquipment(@RequestBody Equipment equipment , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return equipmentService.updateEquipmentDetails(equipment, id);
    }

    @DeleteMapping("/equipment/{id}")
    public String deleteEquipment(@PathVariable String id){
        return equipmentService.deleteEquipment(id);
    }
}