package com.example.marketing.Controller;

import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.DTOs.BookingPackageDTO;
import com.example.marketing.Model.BookingPackage;
import com.example.marketing.Service.BookingPackageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking-package")

public class BookingPackageController   {

    private final BookingPackageService bookingPackageService;

    @PostMapping("/add/{influencer_id}/{company_id}")
    public ResponseEntity addBookingPackage(@PathVariable Integer influencer_id, @PathVariable Integer company_id , @RequestBody @Valid BookingPackage bookingPackage) {
        bookingPackageService.addBookingPackage(influencer_id, company_id, bookingPackage);
        return ResponseEntity.status(200).body(new ApiResponse("Booking package added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBookingPackage(@PathVariable Integer id, @RequestBody @Valid BookingPackage bookingPackage) {
        bookingPackageService.updateBookingPackage(id, bookingPackage);
        return ResponseEntity.status(200).body(new ApiResponse("BookingPackage updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookingPackage(@PathVariable Integer id) {
        bookingPackageService.deleteBookingPackage(id);
        return ResponseEntity.status(200).body(new ApiResponse("BookingPackage deleted successfully"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getBookingPackage(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(bookingPackageService.getBookingPackageById(id));
    }
    //3
    @GetMapping("/booking-packages/influencer")
    public ResponseEntity getAllBookingPackagesByInfluencer(@PathVariable Integer influencerId) {
            List<BookingPackageDTO> bookingPackages = bookingPackageService.getAllBookingPackagesByInfluencerId(influencerId);
        return ResponseEntity.status(200).body(bookingPackages);
    }
    //4
    @GetMapping("/booking-packages/{companyId}/{status}")
    public ResponseEntity getBookingPackagesByStatus(@PathVariable Integer companyId, @PathVariable String status) {
        List<BookingPackageDTO> bookingPackages = bookingPackageService.getBookingPackagesByStatus(companyId, status);
        return ResponseEntity.status(200).body(bookingPackages);
    }

}
