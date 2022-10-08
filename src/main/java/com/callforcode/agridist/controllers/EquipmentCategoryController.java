package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.EquipmentCategory;
import com.callforcode.agridist.entities.User;
import com.callforcode.agridist.services.EquipmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipmentCategoryController {

    @Autowired
    EquipmentCategoryService equipmentCategoryService;

    @GetMapping("/equipmentcategory")
    public ResponseEntity<List<EquipmentCategory>> getAllEquipmentCategory( ) throws InterruptedException, ExecutionException{
        List<EquipmentCategory> equipmentCategory = equipmentCategoryService.getEquipmentCategorys();
        if(equipmentCategory.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(equipmentCategory));
    }


    @GetMapping("/equipmentcategory/{id}")
    public ResponseEntity<Optional<EquipmentCategory>> getEquipmentCategory(@PathVariable String id ) throws InterruptedException, ExecutionException{
        Optional<EquipmentCategory> equipmentCategory = equipmentCategoryService.getEquipmentCategoryDetails(id);
        if (equipmentCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(equipmentCategory));
    }

    @PostMapping("/equipmentcategory")
    public ResponseEntity<EquipmentCategory> createEquipmentCategory(@RequestBody EquipmentCategory equipmentCategory ) throws InterruptedException, ExecutionException {
        try{
            EquipmentCategory user1 = equipmentCategoryService.saveEquipmentCategoryDetails(equipmentCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/equipmentcategory/{id}")
    public ResponseEntity<EquipmentCategory> updateEquipmentCategory(@RequestBody EquipmentCategory equipmentCategory , @PathVariable String id ) throws InterruptedException, ExecutionException {
        try {
            equipmentCategoryService.updateEquipmentCategoryDetails(equipmentCategory, id);
            return ResponseEntity.ok().body(equipmentCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/equipmentcategory/{id}")
    public ResponseEntity<String> deleteEquipmentCategory(@PathVariable String id){
        try {
            equipmentCategoryService.deleteEquipmentCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}