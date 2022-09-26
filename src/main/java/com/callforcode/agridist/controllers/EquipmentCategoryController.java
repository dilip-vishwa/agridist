package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.EquipmentCategory;
import com.callforcode.agridist.services.EquipmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipmentCategoryController {

    @Autowired
    EquipmentCategoryService equipmentCategoryService;

    @GetMapping("/equipmentcategory")
    public List<EquipmentCategory> getAllEquipmentCategory( ) throws InterruptedException, ExecutionException{
        return equipmentCategoryService.getEquipmentCategorys();
    }

    @GetMapping("/equipmentcategory/{id}")
    public EquipmentCategory getEquipmentCategory(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return equipmentCategoryService.getEquipmentCategoryDetails(id);
    }

    @PostMapping("/equipmentcategory")
    public String createEquipmentCategory(@RequestBody EquipmentCategory equipmentCategory ) throws InterruptedException, ExecutionException {
        return equipmentCategoryService.saveEquipmentCategoryDetails(equipmentCategory);
    }

    @PutMapping("/equipmentcategory/{id}")
    public String updateEquipmentCategory(@RequestBody EquipmentCategory equipmentCategory , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return equipmentCategoryService.updateEquipmentCategoryDetails(equipmentCategory, id);
    }

    @DeleteMapping("/equipmentcategory/{id}")
    public String deleteEquipmentCategory(@PathVariable String id){
        return equipmentCategoryService.deleteEquipmentCategory(id);
    }
}