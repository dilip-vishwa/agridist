package com.callforcode.agridist.controllers;

import com.callforcode.agridist.entities.Hire;
import com.callforcode.agridist.services.HireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class HireController {

    @Autowired
    HireService hireService;

    @GetMapping("/hire")
    public List<Hire> getAllHire( ) throws InterruptedException, ExecutionException{
        return hireService.getHires();
    }

    @GetMapping("/hire/{id}")
    public Hire getHire(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return hireService.getHireDetails(id);
    }

    @PostMapping("/hire")
    public String createHire(@RequestBody Hire hire ) throws InterruptedException, ExecutionException {
        return hireService.saveHireDetails(hire);
    }

    @PutMapping("/hire/{id}")
    public String updateHire(@RequestBody Hire hire , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return hireService.updateHireDetails(hire, id);
    }

    @DeleteMapping("/hire/{id}")
    public String deleteHire(@PathVariable String id){
        return hireService.deleteHire(id);
    }
}