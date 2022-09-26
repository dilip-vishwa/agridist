package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Land;
import com.callforcode.agridist.services.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class LandController {

    @Autowired
    LandService landService;

    @GetMapping("/land")
    public List<Land> getAllLand( ) throws InterruptedException, ExecutionException{
        return landService.getLands();
    }

    @GetMapping("/land/{id}")
    public Land getLand(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return landService.getLandDetails(id);
    }

    @PostMapping("/land")
    public String createLand(@RequestBody Land land ) throws InterruptedException, ExecutionException {
        return landService.saveLandDetails(land);
    }

    @PutMapping("/land/{id}")
    public String updateLand(@RequestBody Land land , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return landService.updateLandDetails(land, id);
    }

    @DeleteMapping("/land/{id}")
    public String deleteLand(@PathVariable String id){
        return landService.deleteLand(id);
    }
}