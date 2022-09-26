package com.callforcode.agridist.entities.old;

import com.callforcode.agridist.entities.Vendor;
import com.callforcode.agridist.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class VendorController {

    @Autowired
    VendorService vendorService;

    @GetMapping("/vendor")
    public List<Vendor> getAllVendor( ) throws InterruptedException, ExecutionException{
        return vendorService.getVendors();
    }

    @GetMapping("/vendor/{id}")
    public Vendor getVendor(@PathVariable String id ) throws InterruptedException, ExecutionException{
        return vendorService.getVendorDetails(id);
    }

    @PostMapping("/vendor")
    public String createVendor(@RequestBody Vendor vendor ) throws InterruptedException, ExecutionException {
        return vendorService.saveVendorDetails(vendor);
    }

    @PutMapping("/vendor/{id}")
    public String updateVendor(@RequestBody Vendor vendor , @PathVariable String id ) throws InterruptedException, ExecutionException {
        return vendorService.updateVendorDetails(vendor, id);
    }

    @DeleteMapping("/vendor/{id}")
    public String deleteVendor(@PathVariable String id){
        return vendorService.deleteVendor(id);
    }
}