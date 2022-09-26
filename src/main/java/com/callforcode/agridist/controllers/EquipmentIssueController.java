package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.EquipmentIssue;
import com.callforcode.agridist.services.EquipmentIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipmentIssueController {

    @Autowired
    EquipmentIssueService equipmentIssueService;

    @GetMapping("/equipmentissue")
    public List<EquipmentIssue> getAllEquipmentIssue( ) throws InterruptedException, ExecutionException{
        return equipmentIssueService.getEquipmentIssues();
    }

    @GetMapping("/equipmentissue/{id}")
    public EquipmentIssue getEquipmentIssue(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return equipmentIssueService.getEquipmentIssueDetails(id);
    }

    @PostMapping("/equipmentissue")
    public String createEquipmentIssue(@RequestBody EquipmentIssue equipmentIssue ) throws InterruptedException, ExecutionException {
        return equipmentIssueService.saveEquipmentIssueDetails(equipmentIssue);
    }

    @PutMapping("/equipmentissue/{id}")
    public String updateEquipmentIssue(@RequestBody EquipmentIssue equipmentIssue , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return equipmentIssueService.updateEquipmentIssueDetails(equipmentIssue, id);
    }

    @DeleteMapping("/equipmentissue/{id}")
    public String deleteEquipmentIssue(@PathVariable String id){
        return equipmentIssueService.deleteEquipmentIssue(id);
    }
}