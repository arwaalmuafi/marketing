package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.BookingPackageDTO;
import com.example.marketing.Model.BookingPackage;
import com.example.marketing.Model.Company;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Repostiroy.BookingPackageRepository;
import com.example.marketing.Repostiroy.CompanyRepository;
import com.example.marketing.Repostiroy.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingPackageService {

    private final BookingPackageRepository bookingPackageRepository;
    private final InfluencerRepository influencerRepository;
    private final CompanyRepository companyRepository;



    public void addBookingPackage(Integer influencer_id,Integer company_id , BookingPackage bookingPackage) {

        Company company = companyRepository.findCompanyById(company_id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        Influencer influencer = influencerRepository.findInfluencerById(influencer_id);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        bookingPackage.setCompany(company);
        bookingPackage.setInfluencer(influencer);
        companyRepository.save(company);
        influencerRepository.save(influencer);
        bookingPackageRepository.save(bookingPackage);
    }



    public void updateBookingPackage(Integer id, BookingPackage bookingPackage) {
        BookingPackage existingBookingPackage = bookingPackageRepository.findBookingPackageById(id);
        if (existingBookingPackage == null) {
            throw new ApiException("BookingPackage not found");
        }

        existingBookingPackage.setBookingDate(bookingPackage.getBookingDate());
        existingBookingPackage.setBookingTotalPrice(bookingPackage.getBookingTotalPrice());
        existingBookingPackage.setBookingStatus(bookingPackage.getBookingStatus());
        existingBookingPackage.setCompany(bookingPackage.getCompany());
        existingBookingPackage.setInfluencer(bookingPackage.getInfluencer());
        existingBookingPackage.setPackages(bookingPackage.getPackages());

        bookingPackageRepository.save(existingBookingPackage);
    }

    public void deleteBookingPackage(Integer id) {
        BookingPackage bookingPackage = bookingPackageRepository.findById(id).orElse(null);
        if (bookingPackage == null) {
            throw new ApiException("BookingPackage not found");
        }
        bookingPackageRepository.delete(bookingPackage);
    }

    public BookingPackageDTO getBookingPackageById(Integer bookingPackage_id) {
        BookingPackage bookingPackage = bookingPackageRepository.findBookingPackageById(bookingPackage_id);
        if(bookingPackage==null){
            throw  new ApiException("BookingPackage not found");
        }
        return new BookingPackageDTO(
                bookingPackage.getId(),
                bookingPackage.getBookingDate(),
                bookingPackage.getBookingTotalPrice(),
                bookingPackage.getBookingStatus(),
                bookingPackage.getCompany().getId(),
                bookingPackage.getInfluencer().getId(),
                bookingPackage.getPackages().getId());
    }


    //3 fetching all booking packages for a specific influencer. This could be useful for an influencer to track their collaborations.
    public List<BookingPackageDTO> getAllBookingPackagesByInfluencerId(Integer influencerId) {
        Influencer influencer = influencerRepository.findInfluencerById(influencerId);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        List<BookingPackage> bookingPackages = bookingPackageRepository.findAllByInfluencerId(influencerId);
        if (bookingPackages.isEmpty()) {
            throw new ApiException("No booking packages found for this influencer");
        }

        List<BookingPackageDTO> bookingPackageDTOS = new ArrayList<>();
        for (BookingPackage bookingPackage : bookingPackages) {
            bookingPackageDTOS.add(new BookingPackageDTO(
                    bookingPackage.getId(),
                    bookingPackage.getBookingDate(),
                    bookingPackage.getBookingTotalPrice(),
                    bookingPackage.getBookingStatus(),
                    bookingPackage.getCompany().getId(),
                    bookingPackage.getInfluencer().getId(),
                    bookingPackage.getPackages().getId()));
        }

        return bookingPackageDTOS;
    }

    public List<BookingPackageDTO> getBookingPackagesByStatus(Integer companyId, String status) {
        Company company = companyRepository.findCompanyById(companyId);
        if (company == null) {
            throw new ApiException("Company not found");
        }

        //4 Get Booking Packages by Status
        List<BookingPackage> bookingPackages = bookingPackageRepository.findAllByCompanyIdAndBookingStatus(companyId, status);
        if (bookingPackages.isEmpty()) {
            throw new ApiException("No booking packages found with the given status");
        }

        List<BookingPackageDTO> bookingPackageDTOS = new ArrayList<>();
        for (BookingPackage bookingPackage : bookingPackages) {
            bookingPackageDTOS.add(new BookingPackageDTO(
                    bookingPackage.getId(),
                    bookingPackage.getBookingDate(),
                    bookingPackage.getBookingTotalPrice(),
                    bookingPackage.getBookingStatus(),
                    bookingPackage.getCompany().getId(),
                    bookingPackage.getInfluencer().getId(),
                    bookingPackage.getPackages().getId()));
        }

        return bookingPackageDTOS;
    }
}
