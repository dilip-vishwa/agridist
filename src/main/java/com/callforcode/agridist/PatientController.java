package com.callforcode.agridist;

import com.callforcode.agridist.entities.Patient;
import com.callforcode.agridist.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/getPatients")
    public List<Patient> getAllPatient( ) throws InterruptedException, ExecutionException{
        return patientService.getPatients();
    }

    @GetMapping("/getPatientDetails")
    public Patient getPatient(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return patientService.getPatientDetails(name);
    }

    @PostMapping("/createPatient")
    public String createPatient(@RequestBody Patient patient ) throws InterruptedException, ExecutionException {
        return patientService.savePatientDetails(patient);
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient patient  ) throws InterruptedException, ExecutionException {
        return patientService.updatePatientDetails(patient);
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam String name){
        return patientService.deletePatient(name);
    }
}