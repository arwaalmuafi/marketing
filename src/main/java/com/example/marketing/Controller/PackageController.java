package com.example.marketing.Controller;


import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.Model.Package;
import com.example.marketing.Service.PackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/package")
@RequiredArgsConstructor
public class PackageController {

    @Autowired
    private final PackageService packageService;

    @GetMapping("/get")
    public ResponseEntity<List<Package>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable int id) {

        return ResponseEntity.ok(packageService.getPackageById(id));

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPackage(@RequestBody @Valid Package pack) {
        packageService.addPackage(pack);
        return ResponseEntity.status(200).body(new ApiResponse("successfully added Package"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updatePackage(@RequestBody @Valid Package pack) {
        packageService.updatePackage(pack);
        return ResponseEntity.status(200).body(new ApiResponse("successfully updated Package"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePackage(@PathVariable Integer id) {
        packageService.deletePackage(id);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted Package"));
    }
}
